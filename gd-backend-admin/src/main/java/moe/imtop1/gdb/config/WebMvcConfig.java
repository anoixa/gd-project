package moe.imtop1.gdb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author anoixa
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {
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
}
