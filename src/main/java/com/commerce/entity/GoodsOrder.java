package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Data;

/**
 * 
 * @TableName goods_order
 */
@TableName(value ="goods_order")
@Data
public class GoodsOrder implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "discount")
    private Double discount;

    /**
     * 
     */
    @TableField(value = "total")
    private Double total;

    /**
     * 
     */
    @TableField(value = "milkOrderList")
    private String milkorderlist;

    /**
     * 
     */
    @TableField(value = "cost")
    private Double cost;

    /**
     * 
     */
    @TableField(value = "profit")
    private Double profit;

    /**
     * 
     */
    @TableField(value = "date")
    private Date date;

    /**
     * 
     */
    @TableField(value = "extraOrderList")
    private String extraorderlist;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}