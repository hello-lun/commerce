package com.commerce.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commerce.annotations.RateLimit;
import com.commerce.entity.Goods;
import com.commerce.entity.R;
import com.commerce.mapper.GoodsMapper;
import com.commerce.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsServiceImpl goodsService;

    @PostMapping("/delete")
    public R deleteGoods(@RequestBody Goods goods) {
        goodsService.removeById(goods);
        return R.ok();
    }

    @PostMapping("/add")
    public R addGoods(@RequestBody Goods goods) {
        goodsService.save(goods);
        return R.ok();
    }

    @PostMapping("/batch-add")
    public R batchAddGoods(@RequestBody List<Goods> goodsList) {
        goodsService.saveBatch(goodsList);
        return R.ok();
    }

    @GetMapping("/get")
    public R getGoods(@RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer size,
                      String category,
                      String brand,
                      String title) {
        IPage<Goods> goods = goodsService.getGoodsPagation(new Page<>(page, size), category, brand, title);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("list", goods.getRecords());
        responseMap.put("total", goods.getTotal());
        R r = R.ok();
        r.put("data", responseMap);
        return r;
    }

    @PostMapping("/edit")
    public R editGoods(@RequestBody Goods goods) {
        goodsMapper.updateById(goods);
        return R.ok();
    }
}
