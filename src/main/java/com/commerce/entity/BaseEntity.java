package com.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共基础实体类
 * @author commerce_小锋 （公众号：commerce）
 * @site www.commerce.vip
 * @company 南通小锋网络科技有限公司
 */
@Data
public class BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建日期
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新日期
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;




}
