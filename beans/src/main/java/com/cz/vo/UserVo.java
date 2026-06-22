package com.cz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户数据的封装")
public class UserVo {
    // 用户基础信息
    private Integer id;
    private Integer roleId;
    private String username;
    private String userId;
    private String email;
    private String mobile;
    private String password;
    private String avatar;
    private Long lastLoginAt;
    private String lastLoginIp;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    // 用户验证信息
    private String token;
    private String code;
}

