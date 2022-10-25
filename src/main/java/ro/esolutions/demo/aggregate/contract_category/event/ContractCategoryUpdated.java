package ro.esolutions.demo.aggregate.contract_category.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ro.esolutions.demo.aggregate.contract_category.ContractCategory;
import ro.esolutions.es.AbstractEvent;

@Getter
@Setter
public class ContractCategoryUpdated extends AbstractEvent<ContractCategory> {

    String name;

    public ContractCategoryUpdated() {
        super(ContractCategory.class);
    }

    @Override
    public ContractCategory apply(ContractCategory contractCategory) {
        BeanUtils.copyProperties(this, contractCategory);
        return contractCategory;
    }
}
