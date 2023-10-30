package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pdf_mark
 */
@TableName(value ="pdf_mark")
@Data
public class PdfMark implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "pageNum")
    private Integer pagenum;

    /**
     * 
     */
    @TableField(value = "data")
    private String data;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}