package com.cz.controller;

import com.cz.service.HealthArchiveService;
import com.cz.vo.HealthArchiveVo;
import com.cz.vo.ResultVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/archive")
@CrossOrigin
@Tag(name = "健康档案管理", description = "提供健康档案操作的接口")
public class HealthArchiveController {

    @Resource
    private HealthArchiveService healthArchiveService;

    @Operation(summary = "新建档案")
    @Parameters({
            @Parameter(name = "userId",description = "用户UUID", required = true),
            @Parameter(name = "type",description = "档案类型(1: 疾病史; 2: 手术记录; 3: 体检异常项)", required = true),
            @Parameter(name = "title",description = "标题", required = true),
            @Parameter(name = "content",description = "内容"),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "创建档案成功"),
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultVo CreateHealthArchive(@RequestBody HealthArchiveVo healthArchive) {
        return healthArchiveService.createHealthArchive(healthArchive);
    }

    @Operation(summary = "更新档案")
    @Parameters({
            @Parameter(name = "id",description = "档案ID", required = true),
            @Parameter(name = "title",description = "标题", required = true),
            @Parameter(name = "content",description = "内容"),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "更新档案成功"),
    })
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResultVo UpdateHealthArchive(@RequestBody HealthArchiveVo healthArchive) {
        return healthArchiveService.updateHealthArchive(healthArchive);
    }

    @Operation(summary = "删除档案")
    @Parameters({
            @Parameter(name = "id",description = "档案ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "删除成功"),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultVo DeleteHealthArchive(@PathVariable Integer id) {
        return healthArchiveService.deleteHealthArchive(id);
    }

    @Operation(summary = "获取档案")
    @Parameters({
            @Parameter(name = "user_id",description = "用户ID", required = true),
            @Parameter(name = "archive_id",description = "档案ID"),
            @Parameter(name = "type",description = "档案类型"),
            @Parameter(name = "updated",description = "true: 显示所有档案; false: 只显示最近更新的档案"),
            @Parameter(name = "deleted",description = "true: 显示所有档案; false: 只显示未删除的档案"),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "获取档案成功"),
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultVo GetHealthArchives(@RequestParam(value = "user_id", required = false, defaultValue = "") String uuid,
                                      @RequestParam(value = "archive_id", required = false, defaultValue = "") String archiveId,
                                      @RequestParam(value = "type", required = false, defaultValue = "0") Integer type,
                                      @RequestParam(value = "updated", required = false, defaultValue = "false") Boolean updated,
                                      @RequestParam(value = "deleted", required = false, defaultValue = "false") Boolean deleted) {
        return healthArchiveService.getHealthArchive(uuid, archiveId, type, updated, deleted);
    }


}