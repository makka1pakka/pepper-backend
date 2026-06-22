package com.cz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "封装接口返回给前端的数据")
public class ResultVo {

    @Schema(description = "状态码", type = "int")
    private int code;

    @Schema(description = "消息", type = "String")
    private String msg;

    @Schema(description = "数据", type = "Object")
    private Object data;

}
