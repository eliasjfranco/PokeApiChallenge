package com.challenge.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;
import java.util.function.Predicate;

@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class swaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(info())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.challenge.controller"))
                .build();

    }

    private ApiInfo info(){
        ApiInfo info = new ApiInfo(
                "JamerSoft Challenge",
                "Api REST que integra la API de PokeApi",
                "1.0",
                "",
                new Contact("Elías Joel Franco", "https://www.linkedin.com/in/eliaassf/", "francoeliasjoel@gmail.com"),
                "Free",
                "http://",
                Collections.emptyList()
        );
        return info;
    }

    /*@Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
            }
        };
    }*/

}
