package com.cz.controller;

import com.cz.service.MeasureService;
import com.cz.vo.MeasureVo;
import com.cz.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/measure")
@CrossOrigin
@Tag(name = "测量数据管理", description = "提供测量数据操作的接口")
public class MeasureController {

    @Autowired
    private MeasureService measureService;

    @Operation(summary = "获取所有测量数据")
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "获取成功"),
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultVo getMeasures() {
        return measureService.getAllMeasures();
    }

    @Operation(summary = "根据ID获取测量数据")
    @Parameters({
            @Parameter(name = "id",description = "测量数据ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "获取成功"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultVo getMeasureById(@PathVariable Integer id) {
        return measureService.getMeasureById(id);
    }

    @Operation(summary = "创建新测量数据")
    @Parameters({
            @Parameter(name = "measureVo",description = "测量数据", required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "创建成功"),
    })
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultVo createMeasure(@RequestBody MeasureVo measureVo) {
        return measureService.createMeasure(measureVo);
    }

    @Operation(summary = "更新测量数据")
    @Parameters({
            @Parameter(name = "id",description = "测量数据ID", required = true),
            @Parameter(name = "measureVo",description = "测量数据", required = true)
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "更新成功"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultVo updateMeasure(@PathVariable Integer id, @RequestBody MeasureVo measureVo) {
        return measureService.updateMeasure(id, measureVo);
    }

    @Operation(summary = "删除测量数据")
    @Parameters({
            @Parameter(name = "id",description = "测量数据ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "删除成功"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultVo deleteMeasure(@PathVariable Integer id) {
        return measureService.deleteMeasure(id);
    }

    @Operation(summary = "获取指定用户的最近测量数据")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "获取成功"),
    })
    @RequestMapping(value = "/recent/{userId}", method = RequestMethod.GET)
    public ResultVo getRecentMeasure(@PathVariable String userId) {
        return measureService.getRecentMeasure(userId);
    }
}