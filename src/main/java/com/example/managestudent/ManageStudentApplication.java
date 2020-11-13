package com.example.managestudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ManageStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageStudentApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.managestudent"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "FUni Management",
                "Manage student and course.",
                "Beta 0.0.1",
                "Terms of service",
                new Contact("Nguyen Ngoc Phuoc", "www.phuoc308.com", "phuoc875@gmail0.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
