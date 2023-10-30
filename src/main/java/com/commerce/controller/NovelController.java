package com.commerce.controller;

import com.commerce.entity.NovelMark;
import com.commerce.entity.R;
import com.commerce.mapper.NovelMarkMapper;
import com.commerce.service.impl.NovelMarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/novel")
public class NovelController {
    @Autowired
    NovelMarkServiceImpl novelMarkService;

    @Autowired
    NovelMarkMapper novelMarkMapper;

    @PostMapping("/saveNovelMark")
    public R saveNovelMark(@RequestBody NovelMark novelMark) {
        if(novelMark.getId() != null) {
            novelMarkMapper.updateById(novelMark);
        } else {
            novelMarkMapper.insert(novelMark);
        }
        return R.ok();
    }

    @PostMapping("/getNovelMark")
    public R getNovelMark(@RequestBody NovelMark novelMark) {
        Integer id = novelMark.getId();
        NovelMark data = novelMarkMapper.selectById(id);
        R responseResult = R.ok();
        responseResult.put("data", data);
        return responseResult;
    }
}
