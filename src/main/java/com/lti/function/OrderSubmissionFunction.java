package com.lti.function;

import com.lti.dao.FetchOrderDetails;
import com.lti.dao.FetchOrderQuantity;
import com.lti.dao.UpdateOrder;
import com.lti.dto.OrderDTO;
import com.lti.dto.QuantityDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Service
public class OrderSubmissionFunction implements Function<Integer,String> {

    @Autowired
    private FetchOrderDetails fetchOrderDetails;

    @Autowired
    private FetchOrderQuantity fetchOrderQuantity;

    @Autowired
    private UpdateOrder updateOrder;

    @Override
    public String apply(Integer orderId) {
        log.info("orderId {}",orderId);

        OrderDTO orderDTO = fetchOrderDetails.fetchOrder(orderId);
        QuantityDTO quantityDTO = fetchOrderQuantity.fetchQuantity(orderDTO.getProdId());

        if(quantityDTO.getQuantity()>= quantityDTO.getOrderedQuantity() + orderDTO.getSelectedQuantity()){
            updateOrder.orderQuantityUpdate(orderDTO.getProdId(),quantityDTO.getOrderedQuantity() + orderDTO.getSelectedQuantity());
            updateOrder.orderStatusUpdate(orderId,"ACCEPTED");
            return "Order successfully processed";
        }
        else {
            updateOrder.orderStatusUpdate(orderId,"LIMIT_EXCEEDED");
            return "Order failed";
        }
    }
}
