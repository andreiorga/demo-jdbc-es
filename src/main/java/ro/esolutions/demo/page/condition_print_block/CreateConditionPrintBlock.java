package ro.esolutions.demo.page.condition_print_block;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateConditionPrintBlock {
    String name;
    String nameTranslation;
    String text;
    String textLanguage;
    Boolean alwaysPrintConditionModels;
    Boolean printCurrencyForAmmounts;
    List<String> printingGroups;
    Boolean printSettlementSchedules;
}
