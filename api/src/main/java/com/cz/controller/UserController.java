package com.cz.controller;


import com.cz.entity.User;
import com.cz.service.UserService;
import com.cz.utils.IpUtil;
import com.cz.vo.ResultVo;
import com.cz.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Tag(name = "用户管理", description = "提供用户操作的接口")
public class UserController {
    @Resource
    private UserService userService;

    @Operation(summary = "用户登录接口")
    @Parameters({
            @Parameter(name = "email",description = "用户邮箱", required = true),
            @Parameter(name = "password",description = "用户密码", required = true)
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "用户登录成功"),
            @ApiResponse(responseCode = "1001", description = "用户名不存在"),
            @ApiResponse(responseCode = "1002", description = "密码错误")
    })
    @RequestMapping(value = "/login/email", method = RequestMethod.POST)
    public ResultVo login(@RequestBody User user, HttpServletRequest request) {
        String ipAddress = IpUtil.getIpAddr(request);
        return userService.loginByEmail(user.getEmail(), user.getPassword(), ipAddress);
    }

    @Operation(summary = "用户注册接口")
    @Parameters({
            @Parameter(name = "email",description = "用户邮箱", required = true),
            @Parameter(name = "password",description = "用户密码", required = true),
            @Parameter(name = "code",description = "用户验证码", required = true)
    })
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "用户注册成功"),
            @ApiResponse(responseCode = "1003", description = "用户名注册失败"),
            @ApiResponse(responseCode = "1004", description = "用户名已被注册")
    })
    @RequestMapping(value = "/register/email", method = RequestMethod.POST)
    public ResultVo registerByEmail(@RequestBody UserVo user) {
        return userService.registerByEmail(user.getEmail(), user.getPassword(), user.getCode());
    }

    @Operation(summary = "发送验证码")
    @Parameters({
            @Parameter(name = "email",description = "用户邮箱", required = true),
    })
    @RequestMapping(value = "/send/email", method = RequestMethod.GET)
    public ResultVo sendEmailCode(@RequestParam("email") String email, HttpServletRequest request) {
        String ipAddress = IpUtil.getIpAddr(request);
        return userService.sendEmailCode(email);
    }

    @Operation(summary = "用户验证接口")
    @ApiResponses({
            @ApiResponse(responseCode = "0", description = "用户验证成功"),
            @ApiResponse(responseCode = "1005", description = "用户未登录"),
    })
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public ResultVo session(HttpServletRequest request, HttpServletResponse response) {
        return userService.session(request);
    }
}
