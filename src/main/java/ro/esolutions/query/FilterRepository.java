package ro.esolutions.query;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ro.esolutions.util.JsonUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FilterRepository {

    final NamedParameterJdbcTemplate template;

    private static void addAnd(StringBuilder where) {
        if (where.length() > 0) {
            where.append(" and ");
        }
    }

    private static void checkMandatory(boolean hasValue, boolean isMandatory, String clazz, Field field) {
        if (!hasValue && isMandatory) {
            throw new RuntimeException("Mandatory query field: " + clazz + "." + field.getName());
        }
    }

    private static void addCondition(StringBuilder where, String condition, boolean addParam, Map<String, Object> params, Field field, Object value) {
        where.append(condition);
        if (addParam) {
            params.put(field.getName(), value);
        }
    }

    public <R> GenericList<R> filter(AbstractFilter<R> filter) {
        return filter(filter, null);
    }

    @SneakyThrows
    public <R> GenericList<R> filter(AbstractFilter<R> filter, Pageable pageable) {

        var clazz = filter.getClass();

        StringBuilder where = new StringBuilder();
        StringBuilder having = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        Filter f = clazz.getAnnotationsByType(Filter.class)[0];

        String listQuery = "select " + f.select() + " from " + f.from();
        String countQuery = "select count(1) from " + f.from();
        where.append(f.where());

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            var value = field.get(filter);
            var type = field.getType();
            boolean hasValue = hasValue(value, type);
            Where atWhere = field.getDeclaredAnnotation(Where.class);
            if (atWhere != null) {
                checkMandatory(hasValue, atWhere.mandatory(), clazz.getName(), field);
                if (hasValue) {
                    addAnd(where);
                    addCondition(where, atWhere.value(), atWhere.addParam(), params, field, value);
                }
            }
            Having atHaving = field.getDeclaredAnnotation(Having.class);
            if (atHaving != null) {
                checkMandatory(hasValue, atHaving.mandatory(), clazz.getName(), field);
                if (hasValue) {
                    addAnd(having);
                    addCondition(having, atHaving.value(), atHaving.addParam(), params, field, value);
                }
            }
        }
        if (where.length() > 0) {
            var whereString = where.toString();
            listQuery += " where " + whereString;
            countQuery += " where " + whereString;
        }

        if (!"".equals(f.groupBy())) {
            listQuery += " group by " + f.groupBy();
            countQuery += " group by " + f.groupBy();
        }

        if (having.length() > 0) {
            var havingString = having.toString();
            listQuery += " having " + havingString;
            countQuery += " having " + havingString;
        }

        if (!"".equals(f.groupBy())) {
            countQuery = "select count(1) from (" + countQuery + ") cnt";
        }
        
        var defaultDir = " " + filter.getDefaultOrderDir();
        if (StringUtils.hasLength(filter.getOrderBy())) {
            listQuery += " order by " + filter.getOrderBy() + " " + filter.getOrderDir();
        } else {
            listQuery += " order by " + filter.getDefaultOrderBy() + defaultDir;
        }
        if (pageable != null) {
            listQuery += " offset " + pageable.getOffset() + " limit " + pageable.getPageSize();
        }
        log.info(listQuery);
        log.info("Params: " + JsonUtil.writeValue(params));
        List<R> resultList = template.query(listQuery, params, filter.getRowMapper());
        Long resultCount = template.queryForObject(countQuery, params, Long.class);
        return GenericList.of(resultList, resultCount);
    }

    private boolean hasValue(Object value, Class type) {
        return Objects.nonNull(value) && (isNotListAndHasValue(value, type) || isListAndHasValue(value, type));
    }

    private boolean isNotListAndHasValue(Object value, Class type) {
        return !type.equals(List.class) && !"".equals(value);
    }

    private boolean isListAndHasValue(Object value, Class type) {
        return type.equals(List.class) && !((List) value).isEmpty();
    }

}
