package com.commerce.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commerce.entity.Goods;
import com.commerce.entity.NewWord;
import com.commerce.entity.NovelMark;
import com.commerce.entity.R;
import com.commerce.mapper.NewWordMapper;
import com.commerce.mapper.NovelMarkMapper;
import com.commerce.service.NewWordService;
import com.commerce.service.impl.NovelMarkServiceImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/novel")
public class NovelController {

    @Autowired
    NewWordService newWordService;

    @Autowired
    NovelMarkServiceImpl novelMarkService;

    @Autowired
    NovelMarkMapper novelMarkMapper;
    private static PDDocument cachedDDFDocument;

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

    @PostMapping("/sava-word")
    public R savaNewWord(@RequestBody NewWord word) {
        newWordService.save(word);
        return R.ok();
    }

    @PostMapping("/edit-word")
    public R editNewWord(@RequestBody NewWord word) {
        newWordService.updateById(word);
        return R.ok();
    }


    @GetMapping("/get-word")
    public R getGoods(@RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(defaultValue = "10") Integer size,
                      @RequestParam(defaultValue = "") int isRead) {
        IPage<NewWord> words = newWordService.getNewWordPagation(new Page<>(page, size), isRead);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("list", words.getRecords());
        responseMap.put("total", words.getTotal());
        R r = R.ok();
        r.put("data", responseMap);
        return r;
    }
}
