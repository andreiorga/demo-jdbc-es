package ro.esolutions.es;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface Policy {
    void onEvent(String aggregateId, AbstractAggregate aggregate, AbstractEvent event);
}
