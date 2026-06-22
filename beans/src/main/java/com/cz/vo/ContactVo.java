package com.cz.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "事件数据的封装")
public class ContactVo {
    private Integer id;
    @JsonProperty(value = "user_id")
    private String userId;
    private String mobile;
    private String name;
}