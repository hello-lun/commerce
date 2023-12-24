package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 
 * @TableName articals
 */
@TableName(value ="articals")
@Data
public class Articals implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "text")
    private String text;

    /**
     * 
     */
    @TableField(value = "title")
    private String title;

    /**
     * 
     */
    @TableField(value = "type")
    private String type;

    /**
     * 
     */
    @TableField(value = "isRead")
    private Integer isread;

    /**
     * 
     */
    @TableField(value = "words")
    private String words;

    /**
     * 
     */
    @TableField(value = "userId")
    private Long userid;

    /**
     * 
     */
    @TableField(value = "firstType")
    private String firsttype;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}