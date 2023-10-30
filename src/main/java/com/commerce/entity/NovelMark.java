package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName novel_mark
 */
@TableName(value ="novel_mark")
@Data
public class NovelMark implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "userId")
    private Long userid;

    /**
     * 
     */
    @TableField(value = "words")
    private String words;

    /**
     * 
     */
    @TableField(value = "notes")
    private String notes;

    /**
     * 
     */
    @TableField(value = "novelId")
    private String novelid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}