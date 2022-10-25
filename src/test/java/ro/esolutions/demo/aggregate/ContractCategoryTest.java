package ro.esolutions.demo.aggregate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ro.esolutions.demo.aggregate.contract_category.ContractCategory;
import ro.esolutions.demo.aggregate.contract_category.event.ContractCategoryCreated;
import ro.esolutions.demo.aggregate.contract_category.event.ContractCategoryDeleted;

public class ContractCategoryTest {

    @Test
    public void testContractCategoryCreated() {

        ContractCategory given = new ContractCategory();

        ContractCategoryCreated when = new ContractCategoryCreated().setCode("code").setName("name");

        ContractCategory then = when.apply(given);

        Assertions.assertEquals("code", then.getCode());
        Assertions.assertEquals("name", then.getName());
    }

    @Test
    public void testContractCategoryDeleted() {

        ContractCategory given = new ContractCategory();

        ContractCategoryDeleted when = new ContractCategoryDeleted();

        ContractCategory then = when.apply(given);

        Assertions.assertNull(then);
    }
}
