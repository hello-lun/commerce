package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_word_records
 */
@TableName(value ="user_word_records")
@Data
public class UserWordRecords implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 
     */
    @TableField(value = "artical_id")
    private Integer articalId;

    /**
     * 
     */
    @TableField(value = "words")
    private String words;

    @TableField(value = "sentences")
    private String sentences;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}