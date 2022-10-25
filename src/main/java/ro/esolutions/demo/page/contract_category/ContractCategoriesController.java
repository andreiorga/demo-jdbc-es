package ro.esolutions.demo.page.contract_category;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.demo.aggregate.contract_category.ContractCategory;
import ro.esolutions.demo.aggregate.contract_category.event.ContractCategoryCreated;
import ro.esolutions.demo.aggregate.contract_category.event.ContractCategoryDeleted;
import ro.esolutions.demo.aggregate.contract_category.event.ContractCategoryUpdated;
import ro.esolutions.es.CommandService;
import ro.esolutions.query.FilterRepository;
import ro.esolutions.query.GenericList;

@RestController
@RequestMapping("/contract-category")
@RequiredArgsConstructor
@Transactional
public class ContractCategoriesController {

    final CommandService commandService;
    final FilterRepository filterRepository;

    @PostMapping
    public GenericList<ContractCategoryDto> find(@RequestBody ContractCategoryDtoFilter filter) {
        return filterRepository.filter(filter);
    }

    @PostMapping("/createContractCategory")
    public ContractCategoryDto createContractCategory(@RequestBody CreateContractCategory dto) {
        //SecurityUtil.getLoggedUserId()
        //TODO: validation
        //TODO: authorization
        //TODO: optional - some queries if there is not enough data in dto

        ContractCategoryCreated event = new ContractCategoryCreated();
        event.copyPropertiesFrom(dto);

        ContractCategory cc = commandService.applyNew(event);
        return new ContractCategoryDto(cc);
    }

    @PostMapping("/updateContractCategory/{contractCategoryId}")
    public ContractCategoryDto updateContractCategory(@PathVariable String contractCategoryId, @RequestBody UpdateContractCategory dto) {
        ContractCategory c = commandService.apply(contractCategoryId, new ContractCategoryUpdated().copyPropertiesFrom(dto));
        return new ContractCategoryDto(c);
    }

    @PostMapping("/deleteContractCategory/{contractCategoryId}")
    public void deleteContractCategory(@PathVariable String contractCategoryId) {
        commandService.apply(contractCategoryId, new ContractCategoryDeleted());
    }
}
