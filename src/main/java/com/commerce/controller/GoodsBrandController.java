package com.commerce.controller;

import com.commerce.entity.GoodsBrand;
import com.commerce.entity.GoodsCategory;
import com.commerce.entity.R;
import com.commerce.mapper.GoodsBrandMapper;
import com.commerce.mapper.GoodsCategoryMapper;
import com.commerce.service.impl.GoodsBrandServiceImpl;
import com.commerce.service.impl.GoodsCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/brand")
public class GoodsBrandController {
    @Autowired
    GoodsBrandMapper goodsBrandMapper;

    @Autowired
    GoodsBrandServiceImpl goodsBrandService;

    @GetMapping("/get")
    public R getCategory() {
        return R.ok(goodsBrandService.list());
    }

    @PostMapping("/add")
    public R addCategory(@RequestBody GoodsBrand goodsBrand) {
        return R.ok(goodsBrandService.save(goodsBrand));
    }

    @PostMapping("/edit")
    public R editCategory(@RequestBody GoodsBrand goodsBrand) {
        return R.ok(goodsBrandMapper.updateById(goodsBrand));
    }

    @GetMapping("/delete")
    public R deleteCategory(@RequestParam String id) {
        return R.ok(goodsBrandService.removeById(id));
    }
}
