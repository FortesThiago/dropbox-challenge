package com.example.architecture.contract.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket dropbox() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .build()
                .apiInfo(metadata());
    }
    private ApiInfo metadata() {
        return new ApiInfoBuilder().title("DropBox")
                .description("Dropbox Documentation")
                .version("1.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
