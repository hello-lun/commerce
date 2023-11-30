package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName banners
 */
@TableName(value ="banners")
@Data
public class Banners implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
    @TableField(value = "img")
    private String img;

    /**
     * 
     */
    @TableField(value = "category")
    private String category;

    /**
     * 
     */
    @TableField(value = "brand")
    private String brand;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}