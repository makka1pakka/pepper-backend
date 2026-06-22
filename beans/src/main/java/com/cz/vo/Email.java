package com.cz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 邮件信息
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "邮件数据的封装")
public class Email {

    /**
     * 发送邮箱列表
     */
    @NotEmpty
    private List<String> tos;

    /**
     * 主题
     */
    @NotBlank
    private String subject;

    /**
     * 内容
     */
    @NotBlank
    private String content;
}