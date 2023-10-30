package com.commerce.controller;

import com.commerce.entity.GoodsMilk;
import com.commerce.entity.GoodsOrder;
import com.commerce.entity.R;
import com.commerce.service.impl.GoodsMilkServiceImpl;
import com.commerce.service.impl.GoodsOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/milk")
public class MilkController {
    @Autowired
    GoodsMilkServiceImpl goodsMilkService;

    @Autowired
    GoodsOrderServiceImpl goodsOrderService;

    @PostMapping("/add")
    public R saveNovelMark(@RequestBody GoodsMilk goodsMilk) {
        goodsMilkService.addMilk(goodsMilk);
        return R.ok();
    }

    @GetMapping("/deleteMilk")
    public R deleteMilk(@RequestParam Long id) {
        goodsMilkService.removeById(id);
        return R.ok();
    }

    @GetMapping("/getAllMilk")
    public R getAllMilk() {
        List<GoodsMilk> data = goodsMilkService.list();
        return R.ok(data);
    }

    @GetMapping("/getAllGoodsOrder")
    public R getAllGoodsOrder(@RequestParam("startDate") String startDateStr, @RequestParam("endDate") String endDateStr) {
        List<GoodsOrder> list = goodsMilkService.getAllGoodsOrder(startDateStr, endDateStr);
        return R.ok(list);
    }

    @PostMapping("/addGoodsOrder")
    public R saveNovelMark(@RequestBody GoodsOrder goodsOrder) {
        goodsOrderService.insertGoodsOrder(goodsOrder);
        return R.ok();
    }


    @PostMapping("/editGoodsOrder")
    public R editNovelMark(@RequestBody GoodsOrder goodsOrder) {
        goodsOrderService.insertGoodsOrder(goodsOrder);
        return R.ok();
    }

    @GetMapping("/removeGoodsOrder")
    public R removeGoodsOrder(Long id) {
        goodsOrderService.removeById(id);
        return R.ok();
    }
}
