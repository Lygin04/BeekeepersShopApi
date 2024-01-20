package com.lygin.beekeepersshopapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods(HttpMethod.GET.name(),
                                        HttpMethod.POST.name(),
                                        HttpMethod.DELETE.name(),
                                        HttpMethod.PUT.name())
                        .allowedHeaders(HttpHeaders.AUTHORIZATION,
                                        HttpHeaders.CONTENT_TYPE,
                                        HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN);
            }
        };
    }
}
