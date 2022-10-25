package ro.esolutions.es;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.function.UnaryOperator;

@RequiredArgsConstructor
@Getter
public abstract class AbstractEvent<A extends AbstractAggregate> implements UnaryOperator<A> {

    @JsonIgnore
    final Class<A> eventAggregateClass;

    public AbstractEvent<A> copyPropertiesFrom(Object source) {
        BeanUtils.copyProperties(source, this);
        return this;
    }
}
