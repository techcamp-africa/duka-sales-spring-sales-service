package com.duka.sales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.duka.sales.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Duka Sales Service")
				.description("Duka Private API reference for developers.")
				.termsOfServiceUrl("https://duka.com")
				.license("Duka License")
				.licenseUrl("https://duka.com").version("1.0").build();
	}
}
