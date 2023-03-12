package com.lti.function;

import com.lti.dao.OrderDAO;
import com.lti.dao.ProductDAO;
import com.lti.dto.OrderDTO;
import com.lti.dto.QuantityDTO;
import com.lti.dto.status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Service
public class OrderSubmissionFunction implements Function<Integer,String> {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public String apply(Integer orderId) {
        log.info("orderId {}",orderId);

        OrderDTO orderDTO = orderDAO.fetchOrder(orderId);
        QuantityDTO quantityDTO = productDAO.fetchQuantity(orderDTO.getProdId());

        if(quantityDTO.getQuantity()>= quantityDTO.getOrderedQuantity() + orderDTO.getSelectedQuantity()){
            productDAO.orderQuantityUpdate(orderDTO.getProdId(),quantityDTO.getOrderedQuantity() + orderDTO.getSelectedQuantity());
            orderDAO.orderStatusUpdate(orderId, status.ACCEPTED.getValue());
            return "Order successfully processed";
        }
        else {
            orderDAO.orderStatusUpdate(orderId,status.LIMIT_EXCEEDED.getValue());
            return "Order failed";
        }
    }
}
