package com.commerce.entity;

import lombok.Data;

@Data
public class EmailForm {
    private String subject;
    private String text;
    private String html;
    private String email;
}
