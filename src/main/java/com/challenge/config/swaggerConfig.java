package com.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Collections;

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

}
