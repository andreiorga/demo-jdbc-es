package ro.esolutions.demo.aggregate.contract_category;

import lombok.Getter;
import lombok.Setter;
import ro.esolutions.es.AbstractAggregate;

@Getter
@Setter
public class ContractCategory extends AbstractAggregate {
    String code;
    String name;
}
