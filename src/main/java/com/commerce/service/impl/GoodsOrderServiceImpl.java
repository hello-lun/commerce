package com.commerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.commerce.common.ExchangeHelper;
import com.commerce.entity.GoodsMilk;
import com.commerce.entity.GoodsOrder;
import com.commerce.entity.GoodsOrderExtraItem;
import com.commerce.entity.GoodsOrderItem;
import com.commerce.mapper.GoodsMilkMapper;
import com.commerce.service.GoodsOrderService;
import com.commerce.mapper.GoodsOrderMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.commerce.util.MathUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author dinglun
* @description 针对表【goods_order】的数据库操作Service实现
* @createDate 2023-10-29 03:05:54
*/
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder>
    implements GoodsOrderService{

    @Autowired
    private ExchangeHelper exchangeHelper;

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    private GoodsMilkMapper goodsMilkMapper;

    @Autowired
    GoodsMilkServiceImpl goodsMilkService;

    MathUtils mathUtils = new MathUtils();

    public void insertGoodsOrder(GoodsOrder goodsOrder) {
        List<GoodsMilk> milkList = goodsMilkService.list();
        Map<String, Object> extraOrderData = dealGoodsOrder(milkList, goodsOrder);
        goodsOrder.setCost(mathUtils.formatdouble((double) extraOrderData.get("cost")));
        goodsOrder.setProfit(mathUtils.formatdouble((double) extraOrderData.get("profit")));
//        String milkOrderArrayJson = new Gson().toJson(goodsOrder.getMilkorderlist()); // 使用 Gson 或其他 JSON 库
//        String extraOrderListJson = new Gson().toJson(goodsOrder.getExtraorderlist()); // 使用 Gson 或其他 JSON 库
        if(goodsOrder.getId() == null) {
            goodsOrderMapper.insert(goodsOrder);
        } else {
            goodsOrderMapper.updateById(goodsOrder);
        }
    }


    public double dealGoodsExtraOrder(GoodsOrder goodsOrder, double cnyRate) {
        GoodsOrderExtraItem[] list = new Gson().fromJson(goodsOrder.getExtraorderlist(), GoodsOrderExtraItem[].class);
        if (list.length <= 0) {
            return 0;
        }
        double saleAllPrice = 0;
        for (GoodsOrderExtraItem element: list) {
            double fee = cnyRate * element.getCourierFee();
            double profit = element.getCount() * (element.getSalePrice() - fee);
            saleAllPrice += profit;
        }

        return saleAllPrice;
    }

    private Map<String, Object> dealGoodsOrder(List<GoodsMilk> milkList, GoodsOrder goodsOrder) {
        GoodsOrderItem[] list = new Gson().fromJson(goodsOrder.getMilkorderlist(), GoodsOrderItem[].class);

        double saleAllPrice = 0;
        double courierFee = 0;
        for (GoodsOrderItem element: list) {
            double salePrice = 0;
            for (GoodsMilk item: milkList) {
                String a = item.getMilk();
                String b = element.getMilk();
                int stage1 = item.getStage();
                int stage2 = element.getStage();
                if (a.equals(b) && stage1 == stage2) {
                    courierFee = item.getCourierfee();
                    salePrice = item.getSaleprice();
                }
            }
            saleAllPrice = saleAllPrice + (element.getCount() * (salePrice - courierFee));
        }
        double discount = goodsOrder.getDiscount();
        double total = goodsOrder.getTotal();
        double cnyRate = exchangeHelper.getExchange();
        double cost = total * cnyRate * (discount / 10);
        double milkProfit = saleAllPrice - cost;
        double extraItemProfit = dealGoodsExtraOrder(goodsOrder, cnyRate);
        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("cost", cost);
        orderMap.put("profit", milkProfit + extraItemProfit);
        return orderMap;
    }
}




