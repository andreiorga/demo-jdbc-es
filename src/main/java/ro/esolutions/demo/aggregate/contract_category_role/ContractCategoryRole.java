package ro.esolutions.demo.aggregate.contract_category_role;

import lombok.Getter;
import lombok.Setter;
import ro.esolutions.es.AbstractAggregate;

@Getter
@Setter
public class ContractCategoryRole extends AbstractAggregate {
    String contractCategoryId;
    String role;
}
