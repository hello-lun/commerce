package com.commerce.controller;

import com.commerce.entity.Banners;
import com.commerce.entity.R;
import com.commerce.mapper.BannersMapper;
import com.commerce.service.impl.BannersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/banners")
public class BannersController {
    @Autowired
    BannersMapper bannersMapper;

    @Autowired
    BannersServiceImpl bannersService;

    @GetMapping("/get")
    public R getCategory() {
        return R.ok(bannersService.list());
    }

    @PostMapping("/add")
    public R addCategory(@RequestBody Banners banners) {
        return R.ok(bannersService.save(banners));
    }

    @PostMapping("/edit")
    public R editCategory(@RequestBody Banners banners) {
        return R.ok(bannersMapper.updateById(banners));
    }

    @GetMapping("/delete")
    public R deleteCategory(@RequestParam String id) {
        return R.ok(bannersService.removeById(id));
    }
}
