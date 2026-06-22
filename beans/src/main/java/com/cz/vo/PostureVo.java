package com.cz.vo;

import com.cz.entity.Posture;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "姿势数据的封装")
public class PostureVo {
    private Integer id;
    private String userId;
    private Integer fallDownStatus;
    private Date fallDownTime;
    private Integer fallDownReply;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}