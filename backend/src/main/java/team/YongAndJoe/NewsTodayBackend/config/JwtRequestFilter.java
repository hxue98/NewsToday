package team.YongAndJoe.NewsTodayBackend.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import team.YongAndJoe.NewsTodayBackend.util.JwtTokenUtil;
import team.YongAndJoe.NewsTodayBackend.util.AjaxResponseBody;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // TODO: inject ignore uris
        // Ignore Login and register page
        String uri = httpServletRequest.getRequestURI();
        if (uri.equals("/acc/login") || uri.equals("/acc/register")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = jwtTokenUtil.getTokenFromRequest(httpServletRequest);

        // Redirect to login page for invalid token.
        if (!jwtTokenUtil.validJwt(token)) {
            httpServletResponse.setStatus(401);
            // TODO: inject response
            AjaxResponseBody body = new AjaxResponseBody(false, "Require Authentication", null);
            httpServletResponse.getWriter().write(JSON.toJSONString(body));
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
