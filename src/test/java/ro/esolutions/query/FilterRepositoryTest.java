package ro.esolutions.query;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.demo.aggregate.contract_category.event.ContractCategoryCreated;
import ro.esolutions.demo.aggregate.contract_category_role.event.ContractCategoryRoleCreated;
import ro.esolutions.demo.page.contract_category.ContractCategoryDto;
import ro.esolutions.demo.page.contract_category.ContractCategoryDtoFilter;
import ro.esolutions.demo.page.contract_category_role.ContractCategoryRoleCountDto;
import ro.esolutions.demo.page.contract_category_role.ContractCategoryRoleCountDtoFilter;
import ro.esolutions.demo.page.contract_category_role.ContractCategoryRoleDto;
import ro.esolutions.demo.page.contract_category_role.ContractCategoryRoleDtoFilter;
import ro.esolutions.es.CommandService;
import ro.esolutions.util.JsonUtil;

import java.util.stream.IntStream;

@SpringBootTest
@Transactional
public class FilterRepositoryTest {

    @Autowired
    CommandService commandService;

    @Autowired
    FilterRepository filterRepository;

    @Test
    public void testContractCategoryDtoFilter() {

        initTestData();

        ContractCategoryDtoFilter filter = new ContractCategoryDtoFilter();
        filter.setNameLike("aMe2");

        GenericList<ContractCategoryDto> result = filterRepository.filter(filter, PageRequest.of(1, 2));
        System.out.println("Total count: :" + result.getTotalCount());
        result.forEach(c -> System.out.println(c.getName()));
    }

    @Test
    public void testContractCategoryRoleDtoFilter() {

        initTestData();

        ContractCategoryRoleDtoFilter filter = new ContractCategoryRoleDtoFilter();
        filter.setOrderBy("ccr.role, cc.name");
        filter.setContractCategoryId("ccid6");
        filter.setRole("role3");

        GenericList<ContractCategoryRoleDto> result = filterRepository.filter(filter);
        System.out.println("Total count: :" + result.getTotalCount());
        result.forEach(c -> System.out.println(JsonUtil.writeValue(c)));
    }

    @Test
    public void testContractCategoryRoleCountDtoFilter() {

        initTestData();

        ContractCategoryRoleCountDtoFilter filter = new ContractCategoryRoleCountDtoFilter();
        filter.setMinCount(0L);

        GenericList<ContractCategoryRoleCountDto> result = filterRepository.filter(filter);
        System.out.println("Total count: :" + result.getTotalCount());
        result.forEach(c -> System.out.println(JsonUtil.writeValue(c)));
    }


    private void initTestData() {
        IntStream.range(0, 30)
                .mapToObj(i -> commandService.apply("ccid" + i, new ContractCategoryCreated().setCode("code" + i).setName("name" + i)))
                .forEach(cc -> IntStream.range(0, 10)
                        .forEach(j -> commandService.applyNew(new ContractCategoryRoleCreated().setContractCategoryId(cc.getId()).setRole("role" + j))));
    }
}
