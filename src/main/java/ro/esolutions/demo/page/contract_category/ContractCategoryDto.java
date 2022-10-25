package ro.esolutions.demo.page.contract_category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ro.esolutions.demo.aggregate.contract_category.ContractCategory;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ContractCategoryDto {
    String id;
    String code;
    String name;
    LocalDateTime createdDate;

    public ContractCategoryDto(ContractCategory cc) {
        BeanUtils.copyProperties(cc, this);
    }
}
