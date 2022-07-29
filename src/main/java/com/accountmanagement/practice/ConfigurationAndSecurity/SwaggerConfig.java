package com.accountmanagement.practice.ConfigurationAndSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTH_HEADER_PREFIX = "Authorization";

    private ApiKey apiKey()
    {
        return new ApiKey("JWT" , AUTH_HEADER_PREFIX, "header");
    }

    private List<SecurityContext> securityContexts()
    {
       return List.of(SecurityContext.builder().securityReferences(sf()).build());
    }

    private List<SecurityReference> sf()
    {
        AuthorizationScope scope  = new AuthorizationScope("global","access everything");
        return List.of(new SecurityReference("JWT",new AuthorizationScope[] {scope}));
    }

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(List.of(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).
                build();
    }

    private ApiInfo getInfo() {
       return new ApiInfo("Demo App : Bank-Account Management","The project has been developed by janak kapadiya",
               "1.0","Terms and Service",
                 new Contact("janak","https://BankAppdemo.com","janak@123gmail.com"),
               "License of Api","Api License url", Collections.emptyList());
    }
}
