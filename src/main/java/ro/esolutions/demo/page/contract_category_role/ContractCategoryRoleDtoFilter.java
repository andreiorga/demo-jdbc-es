package ro.esolutions.demo.page.contract_category_role;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ro.esolutions.query.AbstractFilter;
import ro.esolutions.query.Filter;
import ro.esolutions.query.Where;

@Getter
@Setter
@Filter(select = "cc.name as contract_category_name, ccr.*", from = "contract_category_role ccr join contract_category cc on ccr.contract_category_id = cc.id")
public class ContractCategoryRoleDtoFilter extends AbstractFilter<ContractCategoryRoleDto> {

    @Where("cc.id = :contractCategoryId")
    String contractCategoryId;

    @Where("ccr.role = :role")
    String role;

    public ContractCategoryRoleDtoFilter() {
        super(ContractCategoryRoleDto.class, new BeanPropertyRowMapper<>(ContractCategoryRoleDto.class), "cc.name, ccr.role");
    }

}
