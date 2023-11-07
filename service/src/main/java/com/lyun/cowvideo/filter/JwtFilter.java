package com.lyun.cowvideo.filter;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.interfaces.Claim;
import com.lyun.cowvideo.entity.Result;
import com.lyun.cowvideo.entity.po.AccountPO;
import com.lyun.cowvideo.util.AccountInfoContext;
import com.lyun.cowvideo.util.JwtUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Component
@WebFilter(filterName = "jwtFilter", urlPatterns = "/*")
public class JwtFilter implements Filter {

    private static final String[] passUrl = {"auth", "swagger", "api-docs", "webjars", "/video/swapper/next", "/reigster"};

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();

        //获取 header里的token
        final String token = request.getHeader("authorization");

        if (Arrays.stream(passUrl).anyMatch(uri::contains) && token == null) {
            chain.doFilter(req, res);
            return;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");




        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
        }
        // Except OPTIONS, other request should be checked by JWT
        else {

            if (token == null) {
                response.getWriter()
                        .write(JSONUtil.parseObj(Result.fail("请先登录")).toJSONString(0));
                return;
            }

            Map<String, Claim> userData = JwtUtil.verifyToken(token);
            if (userData == null) {
                response.getWriter().write(JSONUtil.parseObj(Result.fail("错误的token")).toJSONString(0));
                return;
            }
            Integer id = userData.get("id").asInt();
            String userName = userData.get("username").asString();
            String name = userData.get("name").asString();
            //拦截器 拿到用户信息，放到request中
            request.setAttribute("id", id);
            request.setAttribute("username", userName);
            AccountInfoContext.setInfo(AccountPO.builder().name(name).username(userName).id(id).build());
            chain.doFilter(req, res);
        }

    }

    @Override
    public void destroy() {
    }
}
