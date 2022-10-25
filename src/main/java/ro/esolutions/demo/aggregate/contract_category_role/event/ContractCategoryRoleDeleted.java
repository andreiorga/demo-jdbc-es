package ro.esolutions.demo.aggregate.contract_category_role.event;

import lombok.Getter;
import lombok.Setter;
import ro.esolutions.demo.aggregate.contract_category_role.ContractCategoryRole;
import ro.esolutions.es.AbstractEvent;

@Getter
@Setter
public class ContractCategoryRoleDeleted extends AbstractEvent<ContractCategoryRole> {

    public ContractCategoryRoleDeleted() {
        super(ContractCategoryRole.class);
    }

    @Override
    public ContractCategoryRole apply(ContractCategoryRole contractCategoryRole) {
        return null;
    }
}
