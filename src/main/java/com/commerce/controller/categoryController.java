package com.commerce.controller;

import com.commerce.entity.GoodsCategory;
import com.commerce.entity.R;
import com.commerce.mapper.GoodsCategoryMapper;
import com.commerce.service.impl.GoodsCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class categoryController {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    GoodsCategoryServiceImpl goodsCategoryService;

    @GetMapping("/get")
    public R getCategory() {
        return R.ok(goodsCategoryService.list());
    }

    @PostMapping("/add")
    public R addCategory(@RequestBody GoodsCategory goodsCategory) {
        return R.ok(goodsCategoryService.save(goodsCategory));
    }

    @PostMapping("/edit")
    public R editCategory(@RequestBody GoodsCategory goodsCategory) {
        return R.ok(goodsCategoryMapper.updateById(goodsCategory));
    }

    @GetMapping("/delete")
    public R deleteCategory(@RequestParam String id) {
        return R.ok(goodsCategoryService.removeById(id));
    }
}
