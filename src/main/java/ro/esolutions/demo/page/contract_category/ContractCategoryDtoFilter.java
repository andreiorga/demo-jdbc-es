package ro.esolutions.demo.page.contract_category;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ro.esolutions.query.AbstractFilter;
import ro.esolutions.query.Filter;
import ro.esolutions.query.Where;

@Getter
@Setter
@Filter(select = "cc.*", from = "contract_category cc")
public class ContractCategoryDtoFilter extends AbstractFilter<ContractCategoryDto> {

    @Where("lower(cc.name) like concat('%',lower(:nameLike),'%')")
    String nameLike;

    public ContractCategoryDtoFilter() {
        super(ContractCategoryDto.class, new BeanPropertyRowMapper<>(ContractCategoryDto.class), "name");
    }
}
