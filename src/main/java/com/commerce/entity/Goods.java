package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName goods
 */
@TableName(value ="goods")
@Data
public class Goods extends BaseEntity implements Serializable {
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
    @TableField(value = "images")
    private String images;
    /**
     * 
     */
    @TableField(value = "category")
    private String category;

    @TableField(value = "brand")
    private String brand;

    /**
     * 
     */
    @TableField(value = "top")
    private Integer top;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}