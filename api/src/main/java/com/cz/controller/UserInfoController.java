package com.cz.controller;

import com.cz.service.UserInfoService;
import com.cz.vo.UserInfoVo;
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
@RequestMapping("/api/user/info")
@CrossOrigin
@Tag(name = "健康用户信息管理", description = "提供健康用户信息操作的接口")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @Operation(summary = "新建用户信息")
    @Parameters({
            @Parameter(name = "user_id",description = "用户信息ID", required = true),
            @Parameter(name = "real_name",description = "真实名称"),
            @Parameter(name = "gender",description = "性别(0: 男; 1: 女)", required = true),
            @Parameter(name = "birth",description = "出生日期"),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "创建用户信息成功"),
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultVo CreateHealthArchive(@RequestBody UserInfoVo userInfo) {
        return userInfoService.createUserInfo(userInfo);
    }

    @Operation(summary = "更新用户信息")
    @Parameters({
            @Parameter(name = "user_id",description = "用户信息ID", required = true),
            @Parameter(name = "real_name",description = "真实名称", required = true),
            @Parameter(name = "gender",description = "性别(0: 男; 1: 女)", required = true),
            @Parameter(name = "birth",description = "出生日期"),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "更新用户信息成功"),
    })
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResultVo UpdateHealthArchive(@RequestBody UserInfoVo userInfo) {
        return userInfoService.updateUserInfo(userInfo);
    }

    @Operation(summary = "删除用户信息")
    @Parameters({
            @Parameter(name = "id",description = "用户信息ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "删除成功"),
    })
    @RequestMapping(value = "/{user_id}", method = RequestMethod.DELETE)
    public ResultVo DeleteHealthArchive(@PathVariable(value = "user_id") String userId) {
        return userInfoService.deleteUserInfo(userId);
    }

    @Operation(summary = "获取用户信息")
    @Parameters({
            @Parameter(name = "user_id",description = "用户ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "获取用户信息成功"),
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultVo GetHealthArchives(@RequestParam(value = "user_id", defaultValue = "") String userId) {
        return userInfoService.getUserInfo(userId);
    }


}