package com.example.ShipperService.Configuation;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsConfig {

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
//                        .allowedOrigins("http://localhost:8081", "exp://192.168.1.4:8081") // Các nguồn gốc được phép
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các HTTP method được phép
//                        .allowedHeaders("*") // Cho phép tất cả các header
//                        .allowCredentials(true); // Cho phép gửi cookie (nếu cần)
//            }
//        };
//    }
}
