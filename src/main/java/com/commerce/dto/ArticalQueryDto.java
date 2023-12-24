package com.commerce.dto;

import com.commerce.entity.Articals;
import lombok.Data;

@Data
public class ArticalQueryDto extends Articals {
    private Integer articalId;
    private Long userId;
    private Integer userWordRecordsId;
}
