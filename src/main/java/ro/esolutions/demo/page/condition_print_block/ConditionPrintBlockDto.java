package ro.esolutions.demo.page.condition_print_block;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ro.esolutions.demo.aggregate.condition_print_block.ConditionPrintBlock;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ConditionPrintBlockDto {
    String name;
    String nameTranslation;
    String text;
    String textLanguage;
    Boolean alwaysPrintConditionModels;
    Boolean printCurrencyForAmmounts;
    List<String> printingGroups;
    Boolean printSettlementSchedules;

    public ConditionPrintBlockDto(ConditionPrintBlock entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
