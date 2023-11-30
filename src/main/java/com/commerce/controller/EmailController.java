package com.commerce.controller;

import com.commerce.annotations.RateLimit;
import com.commerce.entity.EmailForm;
import com.commerce.entity.R;
import com.commerce.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/message")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    @RateLimit(limit = 10, timeout = 60)
    public R sendMessage(@RequestBody EmailForm emailRequest) throws MessagingException {
        emailService.sendSimpleMessage(emailRequest.getEmail(), emailRequest.getSubject(), emailRequest.getText(), emailRequest.getHtml());
        return R.ok("邮件发送成功");
    }
}
