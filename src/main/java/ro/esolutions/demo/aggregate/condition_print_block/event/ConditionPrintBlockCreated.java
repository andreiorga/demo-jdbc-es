package ro.esolutions.demo.aggregate.condition_print_block.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ro.esolutions.demo.aggregate.condition_print_block.ConditionPrintBlock;
import ro.esolutions.es.AbstractEvent;

import java.util.List;

@Getter
@Setter
public class ConditionPrintBlockCreated extends AbstractEvent<ConditionPrintBlock> {
    String name;
    String nameTranslation;
    String text;
    String textLanguage;
    Boolean alwaysPrintConditionModels;
    Boolean printCurrencyForAmmounts;
    List<String> printingGroups;
    Boolean printSettlementSchedules;

    public ConditionPrintBlockCreated() {
        super(ConditionPrintBlock.class);
    }

    @Override
    public ConditionPrintBlock apply(ConditionPrintBlock conditionPrintBlock) {
        BeanUtils.copyProperties(this, conditionPrintBlock);
        return conditionPrintBlock;
    }
}
