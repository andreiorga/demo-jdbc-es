package ro.esolutions.demo.page.contract_category_role;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ro.esolutions.query.AbstractFilter;
import ro.esolutions.query.Filter;
import ro.esolutions.query.Having;

@Getter
@Setter
@Filter(select = "cc.id as contract_category_id, cc.name as contract_category_name, count(ccr.id) as rolesCount",
        from = "contract_category cc left outer join contract_category_role ccr on ccr.contract_category_id = cc.id",
        groupBy = "cc.id"
)
public class ContractCategoryRoleCountDtoFilter extends AbstractFilter<ContractCategoryRoleCountDto> {

    @Having("count(ccr.id) >= :minCount")
    Long minCount;

    public ContractCategoryRoleCountDtoFilter() {
        super(ContractCategoryRoleCountDto.class, new BeanPropertyRowMapper<>(ContractCategoryRoleCountDto.class), "cc.name");
    }

}
