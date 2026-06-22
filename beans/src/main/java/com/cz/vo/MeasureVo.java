package com.cz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "测量数据的封装")
public class MeasureVo {
    private Integer id;
    private String userId;
    private Integer systolicBp;
    private Integer diastolicBp;
    private Integer temperature;
    private Integer heartRate;
    private Integer oxygenSaturation;
    private Integer respiratoryRate;
    private Date measurementTime;
    private Integer sleep;
    private Integer steps;
    private Date sleepStartTime;
    private Date sleepEndTime;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}