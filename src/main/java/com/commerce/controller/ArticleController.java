package com.commerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.commerce.entity.Articals;
import com.commerce.entity.PdfMark;
import com.commerce.entity.R;
import com.commerce.mapper.ArticalsMapper;
import com.commerce.mapper.PdfMarkMapper;
import com.commerce.service.impl.ArticalsServiceImpl;
import com.commerce.service.impl.PdfMarkServiceImpl;
import com.google.gson.Gson;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticalsMapper articalsMapper;

    @Autowired
    PdfMarkServiceImpl pdfMarkService;

    @Autowired
    PdfMarkMapper pdfMarkMapper;
    private static PDDocument cachedDocument;
    private static PDDocument cachedNovelDocument;

    @GetMapping("/getNovel")
    public R getNovel(int startNum, int endNum) {
        String pdfFilePath = "static/oldmanandsea.pdf"; // 替换为实际的 PDF 文件路径
        String text = "";
        String pageText = "";
        int totalPages = 0;
        try {
            if (cachedNovelDocument == null) {
                try (InputStream inputStream = new ClassPathResource(pdfFilePath).getInputStream()) {
                    cachedNovelDocument = PDDocument.load(inputStream);
                }
            }
            // 使用PDDocument加载PDF文件
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            totalPages = cachedNovelDocument.getNumberOfPages();
            text = pdfTextStripper.getText(cachedNovelDocument);
            PDFTextStripper textStripper = new PDFTextStripper();
            textStripper.setStartPage(startNum);
            textStripper.setEndPage(endNum);
            pageText = textStripper.getText(cachedNovelDocument);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        Map<String, Object> personMap = new HashMap<>();
        R responseResult = R.ok();
        personMap.put("text", text);
        personMap.put("pageText", pageText);
        personMap.put("totalPages", totalPages);
        responseResult.put("data", personMap);
        return responseResult;
    }

    @GetMapping("/getPDF")
    public R getPDF(int startNum, int endNum) {
        String pdfFilePath = "static/yasi.pdf"; // 替换为实际的 PDF 文件路径
        String text = "";
        String pageText = "";
        int totalPages = 0;
        try {
            if (cachedDocument == null) {
                try (InputStream inputStream = new ClassPathResource(pdfFilePath).getInputStream()) {
                    cachedDocument = PDDocument.load(inputStream);
                }
            }
            totalPages = cachedDocument.getNumberOfPages();
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            text = pdfTextStripper.getText(cachedDocument);
            PDFTextStripper textStripper = new PDFTextStripper();
            textStripper.setStartPage(startNum);
            textStripper.setEndPage(endNum);
            pageText = textStripper.getText(cachedDocument);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        Map<String, Object> personMap = new HashMap<>();
        R responseResult = R.ok();
        personMap.put("text", text);
        personMap.put("pageText", pageText);
        personMap.put("totalPages", totalPages);
        responseResult.put("data", personMap);
        return responseResult;
    }

    @PostMapping("/savePDFDetail")
    public R getPDF(@RequestBody PdfMark pdfMark) {
        Object data= pdfMarkMapper.selectById(pdfMark.getId());
        if (data == null) {
            pdfMarkMapper.insert(pdfMark);
        } else {
            pdfMarkMapper.updateById(pdfMark);
        }

        R responseResult = R.ok();
        return responseResult;
    }

    @GetMapping("/getPDFMarkDetail")
    public R getPDFMark() {
        R responseResult = R.ok(pdfMarkMapper.selectList(null));
        return responseResult;
    }


    @GetMapping("/getArtical")
    public R getArticalData(@RequestParam String type) {
        QueryWrapper<Articals> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);  // 设置 type 字段的查询条件
        R responseResult = R.ok(articalsMapper.selectList(queryWrapper));
        return responseResult;
    }

    @PostMapping("/saveArtical")
    public R saveArticalWord(@RequestBody Articals articals) {
        Object data= articalsMapper.selectById(articals.getId());
        if (data == null) {
            articalsMapper.insert(articals);
        } else {
            articalsMapper.updateById(articals);
        }
        R responseResult = R.ok();
        return responseResult;
    }
}
