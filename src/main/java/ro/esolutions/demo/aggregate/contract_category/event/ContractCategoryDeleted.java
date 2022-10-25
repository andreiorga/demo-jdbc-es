package ro.esolutions.demo.aggregate.contract_category.event;

import lombok.Getter;
import lombok.Setter;
import ro.esolutions.demo.aggregate.contract_category.ContractCategory;
import ro.esolutions.es.AbstractEvent;

@Getter
@Setter
public class ContractCategoryDeleted extends AbstractEvent<ContractCategory> {

    public ContractCategoryDeleted() {
        super(ContractCategory.class);
    }

    @Override
    public ContractCategory apply(ContractCategory contractCategory) {
        return null;
    }
}
