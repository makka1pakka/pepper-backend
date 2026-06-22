package com.cz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "事件数据的封装")
public class EventVo {
    private Integer id;
    private Integer userId;
    private String eventStatus;
    private Integer eventTypeId;
    private String eventTypeIcon;
    private String eventTypeContent;
    private String title;
    private Date startTime;
    private Date endTime;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private String description;
}