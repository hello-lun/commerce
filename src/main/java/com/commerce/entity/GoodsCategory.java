package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName goods_category
 */
@TableName(value ="goods_category")
@Data
public class GoodsCategory implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 
     */
    @TableField(value = "img")
    private String img;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}