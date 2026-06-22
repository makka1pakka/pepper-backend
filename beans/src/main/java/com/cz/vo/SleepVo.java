package com.cz.vo;

import com.cz.entity.Posture;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "姿势数据的封装")
public class SleepVo {
    private Integer id;
    private String userId;
    private Integer dataType;
    private Integer exist;
    private String IMEI;
    private Integer sleepStatus;
    private Integer sleepScore;
    private Integer sleepQuality;
    private Integer totalSleepDuration;
    private Integer meanSleepRespiration;
    private Integer sleepMeanHeartbeat;
    @JsonProperty("numberdEparturesBed")
    private Integer numberDeparturesBed;
    private Integer numberTurns;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}