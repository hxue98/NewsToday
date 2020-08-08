package team.YongAndJoe.NewsTodayBackend.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import team.YongAndJoe.NewsTodayBackend.util.JwtTokenUtil;
import team.YongAndJoe.NewsTodayBackend.util.AjaxResponseBody;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "config.filter.jwt-filter")
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ErrorMessageConfig errorMessageConfig;

    private List<String> ignorePaths;

    public List<String> getIgnorePaths() {
        return ignorePaths;
    }

    public void setIgnorePaths(List<String> ignorePaths) {
        this.ignorePaths = ignorePaths;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // Ignore Login and register page
        String uri = httpServletRequest.getRequestURI();
        if (ignorePaths.contains(uri)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = jwtTokenUtil.getTokenFromRequest(httpServletRequest);

        // Redirect to login page for invalid token.
        if (!jwtTokenUtil.validJwt(token)) {
            httpServletResponse.setStatus(401);
            AjaxResponseBody body = AjaxResponseBody.FAIL(errorMessageConfig.getRequireAuthentication(), null);
            httpServletResponse.getWriter().write(JSON.toJSONString(body));
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
