package moe.imtop1.gdb.config;

import moe.imtop1.gdb.interceptor.LoginAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author anoixa
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加路径规则
        registry.addMapping("/**")
                // 是否允许在跨域的情况下传递Cookie
                .allowCredentials(true)
                // 允许请求来源的域规则
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*") ;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePatterns = new String[]{"/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
                "/api", "/api-docs", "/api-docs/**", "/doc.html/**"};

        registry.addInterceptor(loginAuthInterceptor)
                .excludePathPatterns("/admin/system/index/login", "/admin/system/index/getValidateCode")
                .excludePathPatterns(excludePatterns)
                .addPathPatterns("/**");

    }
}
