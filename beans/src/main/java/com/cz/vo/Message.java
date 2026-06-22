package com.cz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "手机消息数据的封装")
public class Message {
    private String user;
    private String name;
    private String time;
}
