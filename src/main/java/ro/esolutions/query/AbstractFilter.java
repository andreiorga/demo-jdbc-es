package ro.esolutions.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.RowMapper;

@Getter
@Setter
public class AbstractFilter<D> {

    @JsonIgnore
    final Class<D> projectionClass;

    @JsonIgnore
    final RowMapper<D> rowMapper;

    @JsonIgnore
    final String defaultOrderBy;

    @JsonIgnore
    final Sort.Direction defaultOrderDir;

    String orderBy;
    Sort.Direction orderDir = Sort.Direction.ASC;

    public AbstractFilter(Class<D> projectionClass, RowMapper<D> rowMapper, String defaultOrderBy) {
        this.projectionClass = projectionClass;
        this.rowMapper = rowMapper;
        this.defaultOrderBy = defaultOrderBy;
        defaultOrderDir = Sort.Direction.ASC;
    }

    public AbstractFilter(Class<D> projectionClass, RowMapper<D> rowMapper, String defaultOrderBy, Sort.Direction defaultOrderDir) {
        this.projectionClass = projectionClass;
        this.rowMapper = rowMapper;
        this.defaultOrderBy = defaultOrderBy;
        this.defaultOrderDir = defaultOrderDir;
    }

}
