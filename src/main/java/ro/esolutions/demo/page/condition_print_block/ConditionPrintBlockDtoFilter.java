package ro.esolutions.demo.page.condition_print_block;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ro.esolutions.query.AbstractFilter;
import ro.esolutions.query.Filter;
import ro.esolutions.query.Where;

@Getter
@Setter
@Filter(select = "cpb.*", from = "condition_print_block")
public class ConditionPrintBlockDtoFilter extends AbstractFilter<ConditionPrintBlockDto> {

    @Where("lower(cpb.name) like concat('%',lower(:nameLike),'%')")
    String nameLike;

    @Where("lower(cpb.name_translation) like concat('%',lower(:nameTranslation),'%')")
    String nameTranslation;

    @Where("cpb.always_print_condition_models is :alwaysPrintConditionModels")
    String alwaysPrintConditionModels;


    public ConditionPrintBlockDtoFilter() {
        super(ConditionPrintBlockDto.class, new BeanPropertyRowMapper<>(ConditionPrintBlockDto.class), "name");
    }
}
