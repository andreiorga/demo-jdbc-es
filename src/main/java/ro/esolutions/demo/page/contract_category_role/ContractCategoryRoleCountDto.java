package ro.esolutions.demo.page.contract_category_role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractCategoryRoleCountDto {
    String contractCategoryName;
    String contractCategoryId;
    Long rolesCount;
}
