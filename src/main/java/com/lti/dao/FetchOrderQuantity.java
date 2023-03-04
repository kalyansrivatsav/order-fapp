package com.lti.dao;

import com.lti.dto.QuantityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FetchOrderQuantity {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public QuantityDTO fetchQuantity(long prodid){
        QuantityDTO[] result = new QuantityDTO[1];

        String SQL = "select quantity,ordered_quantity from [product] where id=:prodid";
        Map<String,Long> sqlparams = new HashMap<>();
        sqlparams.put("prodid",prodid);

        namedParameterJdbcTemplate.query(SQL,sqlparams,rs -> {
            QuantityDTO quantityDTO= QuantityDTO.builder()
                    .orderedQuantity(rs.getInt("ordered_quantity"))
                    .quantity(rs.getInt("quantity"))
                    .build();
            result[0]=quantityDTO;
        });
        return result[0];
    }
}
