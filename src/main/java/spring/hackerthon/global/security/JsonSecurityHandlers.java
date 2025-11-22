package spring.hackerthon.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.global.response.status.ErrorStatus;

@Component
@RequiredArgsConstructor
public class JsonSecurityHandlers {

    private final ObjectMapper om = new ObjectMapper();

    /* Unauthorized exception */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, ex) -> writeJson(response, HttpStatus.UNAUTHORIZED, ApiResponse.onFailure(ErrorStatus.UNAUTHORIZED.getCode(), ErrorStatus.UNAUTHORIZED.getMessage(), ErrorStatus.UNAUTHORIZED.getReason()));
    }

    /* Forbidden exception */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, ex) -> writeJson(response, HttpStatus.FORBIDDEN, ApiResponse.onFailure(ErrorStatus.FORBIDDEN.getCode(), ErrorStatus.FORBIDDEN.getMessage(), ErrorStatus.FORBIDDEN.getReason()));
    }

    private void writeJson(HttpServletResponse res, HttpStatus status, Object body) throws java.io.IOException {
        res.setStatus(status.value());
        res.setContentType("application/json;charset=UTF-8");
        om.writeValue(res.getWriter(), body);
    }
}
