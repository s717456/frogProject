package com.tim.document.web1.config;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//管理跨來源請求
@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {

        // 設定允許的跨來源請求
        CorsConfiguration config = new CorsConfiguration();

        // 只允許 React 開發伺服器
        config.setAllowedOrigins(
                List.of("http://localhost:5173")
        );

        // 允許的 HTTP 方法
        config.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        // 允許的請求標頭
        config.setAllowedHeaders(
                List.of("Authorization", "Content-Type", "Accept")
        );

        // 保留原本 UserController 的跨來源憑證設定
        config.setAllowCredentials(true);

        // 套用到所有 /api/ 開頭的路徑
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/api/**", config);

        CorsFilter corsFilter = new CorsFilter(source);

        // 註冊 Filter，並讓 CORS 優先於 JWT 執行
        FilterRegistrationBean<CorsFilter> registration =
                new FilterRegistrationBean<>();

        registration.setFilter(corsFilter);
        registration.addUrlPatterns("/api/*");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return registration;
    }
}