package com.lti.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UpdateOrder {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int orderQuantityUpdate(long prodid, int orderedQuantity){
        String SQL = "update [product] set ordered_quantity=:orderedQuantity where id=:prodid";

        Map<String,Object> sqlparams = new HashMap<>();
        sqlparams.put("prodid",prodid);
        sqlparams.put("orderedQuantity",orderedQuantity);

        return namedParameterJdbcTemplate.update(SQL,sqlparams);
    }

    public int orderStatusUpdate(int orderId, String status){
        String SQL = "update [order] set status=:status where id=:orderId";

        Map<String,Object> sqlparams = new HashMap<>();
        sqlparams.put("orderId",orderId);
        sqlparams.put("status",status);

        return namedParameterJdbcTemplate.update(SQL,sqlparams);
    }
}
