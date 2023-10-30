package com.commerce.entity;

import lombok.Data;

@Data
public class GoodsOrderExtraItem {
    String product;
    double salePrice;
    double realPrice;
    double courierFee;
    int count;
}
