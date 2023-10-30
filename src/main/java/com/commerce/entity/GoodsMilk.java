package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName goods_milk
 */
@TableName(value ="goods_milk")
@Data
public class GoodsMilk implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "milk")
    private String milk;

    /**
     * 
     */
    @TableField(value = "stage")
    private Integer stage;

    /**
     * 
     */
    @TableField(value = "realPrice")
    private Double realprice;

    /**
     * 
     */
    @TableField(value = "salePrice")
    private Double saleprice;

    /**
     * 
     */
    @TableField(value = "courierFee")
    private Double courierfee;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}