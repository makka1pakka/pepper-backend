package com.cz.interceptor;

import com.alibaba.fastjson2.JSON;
import com.cz.utils.SessionUtil;
import com.cz.utils.TokenUtil;
import com.cz.utils.ResStatus;
import com.cz.vo.ResultVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTION".equals(method)) {
            return true;
        }
        String token = request.getHeader(SessionUtil.UserAuthHeader);
        // 未携带验证信息
        if (token == null) {
            doResponse(response, new ResultVo(ResStatus.ERROR_USER_WITHOUT_TOKEN, "请先登录！", null));
            return false;
        }
        Jws<Claims> jws = TokenUtil.parseClaim(token);
        // Token 错误或超时
        if (jws == null) {
            doResponse(response, new ResultVo(ResStatus.ERROR_USER_TOKEN_EXPIRED, "登录过期，请重新登录", null));
            return false;
        }
        HttpSession session = request.getSession();
        try {
            session.setAttribute(SessionUtil.LoginUserID, Integer.parseInt(jws.getPayload().getId()));
        } catch (NumberFormatException e) {
            doResponse(response, new ResultVo(ResStatus.ERROR_USER_TOKEN_EXPIRED, "登录信息错误，请重试", null));
            return false;
        }

        return true;
    }

    private void doResponse(HttpServletResponse response, ResultVo resultVo) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Object json = JSON.toJSON(resultVo);
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
