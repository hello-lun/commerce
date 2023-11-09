package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName staff
 */
@TableName(value ="staff")
@Data
public class Staff implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "title")
    private String title;

    /**
     * 
     */
    @TableField(value = "details")
    private String details;

    /**
     * 
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 
     */
    @TableField(value = "wechat")
    private String wechat;

    /**
     * 
     */
    @TableField(value = "whatsapp")
    private String whatsapp;

    /**
     * 
     */
    @TableField(value = "email")
    private String email;

    /**
     *
     */
    @TableField(value = "img")
    private String img;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}