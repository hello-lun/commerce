package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName goods_brand
 */
@TableName(value ="goods_brand")
@Data
public class GoodsBrand implements Serializable {
    /**
     * 
     */
    @TableField(value = "id")
    private Integer id;

    /**
     * 
     */
    @TableField(value = "lable")
    private String lable;

    /**
     * 
     */
    @TableField(value = "value")
    private String value;

    /**
     * 
     */
    @TableField(value = "img")
    private String img;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}