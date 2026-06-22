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
@Schema(description = "用户信息数据的封装")
public class UserInfoVo {
    private Integer id;
    @JsonProperty(value = "user_id")
    private String userId;
    @JsonProperty(value = "real_name")
    private String realName;
    private Boolean gender;
    private Date birth;
}