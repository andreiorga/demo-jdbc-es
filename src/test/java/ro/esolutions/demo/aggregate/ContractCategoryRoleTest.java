package ro.esolutions.demo.aggregate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ro.esolutions.demo.aggregate.contract_category_role.ContractCategoryRole;
import ro.esolutions.demo.aggregate.contract_category_role.event.ContractCategoryRoleCreated;
import ro.esolutions.demo.aggregate.contract_category_role.event.ContractCategoryRoleDeleted;

public class ContractCategoryRoleTest {

    @Test
    public void testContractCategoryRoleCreated() {

        ContractCategoryRole given = new ContractCategoryRole();

        ContractCategoryRoleCreated when = new ContractCategoryRoleCreated().setContractCategoryId("ccid").setRole("role");

        ContractCategoryRole then = when.apply(given);

        Assertions.assertEquals("ccid", then.getContractCategoryId());
        Assertions.assertEquals("role", then.getRole());
    }

    @Test
    public void testContractCategoryRoleDeleted() {

        ContractCategoryRole given = new ContractCategoryRole();

        ContractCategoryRoleDeleted when = new ContractCategoryRoleDeleted();

        ContractCategoryRole then = when.apply(given);

        Assertions.assertNull(then);
    }
}
