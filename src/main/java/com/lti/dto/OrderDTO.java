package com.lti.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(access = AccessLevel.PUBLIC)
public class OrderDTO {
    long prodId;
    int selectedQuantity;
}
