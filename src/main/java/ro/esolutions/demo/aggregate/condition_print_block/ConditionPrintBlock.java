package ro.esolutions.demo.aggregate.condition_print_block;

import lombok.Getter;
import lombok.Setter;
import ro.esolutions.es.AbstractAggregate;

import java.util.List;

@Getter
@Setter
public class ConditionPrintBlock extends AbstractAggregate {
    String name;
    String nameTranslation;
    String text;
    String textLanguage;
    Boolean alwaysPrintConditionModels;
    Boolean printCurrencyForAmmounts;
    List<String> printingGroups;
    Boolean printSettlementSchedules;

}
