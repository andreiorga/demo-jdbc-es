package ro.esolutions.demo.policy;

import org.springframework.stereotype.Component;
import ro.esolutions.es.AbstractAggregate;
import ro.esolutions.es.AbstractEvent;
import ro.esolutions.es.Policy;

@Component
public class OnCustomerCreatedCreateDefaultAddress implements Policy {

    @Override
    public void onEvent(String aggregateId, AbstractAggregate aggregate, AbstractEvent event) {
//        if (!(event instanceof CustomerCreated)) {
//            return;
//        }
//        System.out.println("OnCustomerCreatedCreateDefaultAddress");
    }
}
