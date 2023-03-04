package com.lti.dao;

import com.lti.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FetchOrderDetails {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OrderDTO fetchOrder(int orderId){
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
        return result[0];
    }
}
