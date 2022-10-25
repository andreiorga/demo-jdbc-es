package ro.esolutions.es;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.util.JsonUtil;
import ro.esolutions.util.SecurityUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.UnaryOperator;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandService {

    final JdbcAggregateTemplate template;

    @Autowired
    List<Policy> policies;

    @SneakyThrows
    public <A extends AbstractAggregate> A apply(String aggregateId, AbstractEvent<A> event) {
        Class<A> aggregateClass = event.getEventAggregateClass();
        A oldAggregate = template.findById(aggregateId, aggregateClass);
        boolean isNew = false;
        if (oldAggregate == null) {
            oldAggregate = aggregateClass.getConstructor().newInstance();
            oldAggregate.setId(aggregateId);
            isNew = true;
        }
        A newAggregate = event.apply(oldAggregate);
        saveAggregate(aggregateId, aggregateClass, isNew, newAggregate);
        logEvent(aggregateId, event, newAggregate, aggregateClass);
        policies.forEach(p -> p.onEvent(aggregateId, newAggregate, event));
        return newAggregate;
    }

    @SneakyThrows
    public <A extends AbstractAggregate> A applyNew(AbstractEvent<A> event) {
        String aggregateId = UUID.randomUUID().toString();
        Class<A> aggregateClass = event.getEventAggregateClass();
        A oldAggregate = aggregateClass.getConstructor().newInstance();
        oldAggregate.setId(aggregateId);
        A newAggregate = event.apply(oldAggregate);
        saveNewAggregate(aggregateId, newAggregate);
        logEvent(aggregateId, event, newAggregate, aggregateClass);
        policies.forEach(p -> p.onEvent(aggregateId, newAggregate, event));
        return newAggregate;
    }

    private <A extends AbstractAggregate> void saveNewAggregate(String aggregateId, A newAggregate) {
        if (newAggregate != null) {
            newAggregate.setId(aggregateId);
            newAggregate.prePersist();
            template.insert(newAggregate);
        }
    }

    private <A extends AbstractAggregate> void saveAggregate(String aggregateId, Class<A> aggregateClass, boolean isNew, A newAggregate) {
        if (newAggregate == null) {
            if (!isNew) {
                template.deleteById(aggregateId, aggregateClass);
            }
        } else {
            newAggregate.setId(aggregateId);
            if (isNew) {
                newAggregate.prePersist();
                template.insert(newAggregate);
            } else {
                newAggregate.preUpdate();
                template.update(newAggregate);
            }
        }
    }

    private <A> void logEvent(String id, UnaryOperator<A> event, A newAggregate, Class<A> aggregateClass) {
        String loggedUserId = SecurityUtil.getLoggedUserId();

        EventshotLog eventLog = new EventshotLog();
        eventLog.setAggregateId(id).setCreatedBy(loggedUserId).setCreatedDate(LocalDateTime.now()).setEventClass(event.getClass().getName()).setEventJson(JsonUtil.writeValue(event)).setAggregateJson(JsonUtil.writeValue(newAggregate)).setAggregateClass(aggregateClass.getName());
        template.insert(eventLog);
    }
}
