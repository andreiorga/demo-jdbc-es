package ro.esolutions.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class EventshotLog {

    @Id
    Long id;

    String createdBy;

    java.time.LocalDateTime createdDate;

    String aggregateId;

    String aggregateClass;

    String aggregateJson;

    String eventClass;

    String eventJson;
}
