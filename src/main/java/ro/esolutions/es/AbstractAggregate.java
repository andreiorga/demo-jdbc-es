package ro.esolutions.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import ro.esolutions.util.SecurityUtil;

import java.time.LocalDateTime;

public class AbstractAggregate {

    @Id
    @Getter
    @Setter
    String id;

    @Version
    @Getter
    Long version;

    @Getter
    LocalDateTime createdDate;
    @Getter
    String createdBy;

    @Getter
    LocalDateTime updatedDate;
    @Getter
    String updatedBy;

    public void prePersist() {
        createdDate = updatedDate = LocalDateTime.now();
        createdBy = updatedBy = SecurityUtil.getLoggedUserId();
    }

    public void preUpdate() {
        updatedDate = LocalDateTime.now();
        updatedBy = SecurityUtil.getLoggedUserId();
    }
}
