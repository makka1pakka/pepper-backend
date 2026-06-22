package com.cz.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "健康档案数据的封装")
public class HealthArchiveVo {
    private Integer id;
    @JsonProperty(value = "archive_id")
    private String archiveId;
    @JsonProperty(value = "user_id")
    private String userId;
    private Integer type;
    private String title;
    private String content;
}