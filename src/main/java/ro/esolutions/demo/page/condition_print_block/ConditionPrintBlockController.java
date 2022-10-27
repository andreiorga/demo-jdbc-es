package ro.esolutions.demo.page.condition_print_block;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.esolutions.demo.aggregate.condition_print_block.ConditionPrintBlock;
import ro.esolutions.demo.aggregate.condition_print_block.event.ConditionPrintBlockCreated;
import ro.esolutions.demo.aggregate.contract_category.ContractCategory;
import ro.esolutions.demo.aggregate.contract_category.event.ContractCategoryCreated;
import ro.esolutions.demo.page.contract_category.ContractCategoryDto;
import ro.esolutions.demo.page.contract_category.ContractCategoryDtoFilter;
import ro.esolutions.demo.page.contract_category.CreateContractCategory;
import ro.esolutions.es.CommandService;
import ro.esolutions.query.FilterRepository;
import ro.esolutions.query.GenericList;

@RestController
@RequestMapping("/condition-print-block")
@RequiredArgsConstructor
@Transactional
public class ConditionPrintBlockController {

    final CommandService commandService;
    final FilterRepository filterRepository;

    @PostMapping("/create")
    public ConditionPrintBlockDto create(@RequestBody CreateConditionPrintBlock dto) {
        ConditionPrintBlockCreated event = new ConditionPrintBlockCreated();
        event.copyPropertiesFrom(dto);

        ConditionPrintBlock entity = commandService.applyNew(event);
        return new ConditionPrintBlockDto(entity);
    }
}
