package com.lti.dao;

import com.lti.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class FetchOrderDetails {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OrderDTO fetchOrder(int orderId){
        log.info("orderId {}",orderId);
        OrderDTO[] result = new OrderDTO[1];

        String SQL="select productid,selected_quantity from [order] where id=:orderId";
        Map<String,Integer> sqlparams=new HashMap<>();
        sqlparams.put("orderId",orderId);
        namedParameterJdbcTemplate.query(SQL,sqlparams,rs -> {
            OrderDTO orderDTO= OrderDTO.builder()
                    .prodId(rs.getLong("productid"))
                    .selectedQuantity(rs.getInt("selected_quantity"))
                    .build();
            result[0]=orderDTO;
        });
        log.info("result orderDTO {}",result);
        return result[0];
    }
}
