package ro.esolutions.demo.page.contract_category_role;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.demo.aggregate.contract_category_role.ContractCategoryRole;
import ro.esolutions.demo.aggregate.contract_category_role.event.ContractCategoryRoleCreated;
import ro.esolutions.demo.aggregate.contract_category_role.event.ContractCategoryRoleDeleted;
import ro.esolutions.es.CommandService;
import ro.esolutions.query.FilterRepository;
import ro.esolutions.query.GenericList;

@RestController
@RequestMapping("/contract-category-role")
@RequiredArgsConstructor
@Transactional
public class ContractCategoryRoleController {

    final CommandService commandService;
    final FilterRepository filterRepository;

    @PostMapping
    public GenericList<ContractCategoryRoleDto> find(@RequestBody ContractCategoryRoleDtoFilter filter) {
        return filterRepository.filter(filter);
    }

    @PostMapping("/count")
    public GenericList<ContractCategoryRoleCountDto> count(@RequestBody ContractCategoryRoleCountDtoFilter filter) {
        return filterRepository.filter(filter);
    }

    @PostMapping("/createContractCategoryRole")
    public ContractCategoryRoleDto createCustomer(@RequestBody CreateContractCategoryRole dto) {
        ContractCategoryRole c = commandService.applyNew(new ContractCategoryRoleCreated().copyPropertiesFrom(dto));
        return new ContractCategoryRoleDto().setContractCategoryId(c.getContractCategoryId()).setRole(c.getRole());
    }

    @PostMapping("/deleteContractCategoryRole/{contractCategoryRoleId}")
    public void deleteContractCategoryRole(@PathVariable String contractCategoryRoleId) {
        commandService.apply(contractCategoryRoleId, new ContractCategoryRoleDeleted());
    }
}
