package ro.esolutions.demo.aggregate.contract_category_role.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ro.esolutions.demo.aggregate.contract_category_role.ContractCategoryRole;
import ro.esolutions.es.AbstractEvent;

@Getter
@Setter
public class ContractCategoryRoleCreated extends AbstractEvent<ContractCategoryRole> {

    String contractCategoryId;
    String role;

    public ContractCategoryRoleCreated() {
        super(ContractCategoryRole.class);
    }

    @Override
    public ContractCategoryRole apply(ContractCategoryRole contractCategoryRole) {
        BeanUtils.copyProperties(this, contractCategoryRole);
        return contractCategoryRole;
    }
}
