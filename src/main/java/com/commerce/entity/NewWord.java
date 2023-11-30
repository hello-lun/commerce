package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName new_word
 */
@TableName(value ="new_word")
@Data
public class NewWord implements Serializable {
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
    @TableField(value = "translation")
    private String translation;

    /**
     * 
     */
    @TableField(value = "isRead")
    private String isRead;

    @TableField(value = "extra")
    private String extra;

    @TableField(value = "others_translation")
    private String othersTranslation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}