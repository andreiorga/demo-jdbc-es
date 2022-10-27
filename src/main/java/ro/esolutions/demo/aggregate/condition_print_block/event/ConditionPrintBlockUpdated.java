package ro.esolutions.demo.aggregate.condition_print_block.event;

import org.springframework.beans.BeanUtils;
import ro.esolutions.demo.aggregate.condition_print_block.ConditionPrintBlock;
import ro.esolutions.es.AbstractEvent;

public class ConditionPrintBlockUpdated extends AbstractEvent<ConditionPrintBlock> {

    public ConditionPrintBlockUpdated() {
        super(ConditionPrintBlock.class);
    }

    @Override
    public ConditionPrintBlock apply(ConditionPrintBlock conditionPrintBlock) {
        BeanUtils.copyProperties(this, conditionPrintBlock);
        return conditionPrintBlock;
    }
}
