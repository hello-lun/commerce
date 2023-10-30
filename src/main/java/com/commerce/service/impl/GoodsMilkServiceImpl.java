package com.commerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.commerce.common.ExchangeHelper;
import com.commerce.entity.GoodsMilk;
import com.commerce.entity.GoodsOrder;
import com.commerce.entity.GoodsOrderExtraItem;
import com.commerce.mapper.GoodsOrderMapper;
import com.commerce.service.GoodsMilkService;
import com.commerce.mapper.GoodsMilkMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author dinglun
* @description 针对表【goods_milk】的数据库操作Service实现
* @createDate 2023-10-29 03:05:50
*/
@Service
public class GoodsMilkServiceImpl extends ServiceImpl<GoodsMilkMapper, GoodsMilk>
    implements GoodsMilkService{

    @Autowired
    private ExchangeHelper exchangeHelper;

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    private GoodsMilkMapper goodsMilkMapper;

    @Autowired
    GoodsMilkServiceImpl goodsMilkService;

    public void addMilk(GoodsMilk goodsMilk) {
        double cnyRate = exchangeHelper.getExchange();
        Double fee = goodsMilk.getCourierfee();
        goodsMilk.setCourierfee(exchangeHelper.formatdouble(fee * cnyRate));
        goodsMilkService.save(goodsMilk);
    }

    public List<GoodsOrder> getAllGoodsOrder(String startDateStr, String endDateStr) {
        QueryWrapper<GoodsOrder> queryWrapper = new QueryWrapper<>();
        List<GoodsOrder> list;
        if(startDateStr.isEmpty() || endDateStr.isEmpty()) {
            list = goodsOrderMapper.selectList(queryWrapper);
        } else {
            LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
            LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE);
            queryWrapper.ge("date", startDate).le("date", endDate);
            list = goodsOrderMapper.selectList(queryWrapper);
        }

        return list;
    }
}




