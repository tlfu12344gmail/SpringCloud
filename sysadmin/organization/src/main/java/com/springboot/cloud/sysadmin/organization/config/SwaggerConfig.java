package com.springboot.cloud.sysadmin.organization.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.host}")
    private String host;
    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<>();
        pars.add(headerParam("Authorization", "令牌", ""));
        pars.add(headerParam("X-Accept-Locale", "语言", "zh_CN"));
        return new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.cloud.sysadmin.organization"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false);

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("系统管理API")
                .description("系统管理，组织人员管理、角色权限管理、岗位管理")
                .termsOfServiceUrl("https://github.com/zhoutaoo/SpringCloud")
                .version("2.0")
                .build();
    }
    private Parameter headerParam(String name, String description, String defaultVal) {
        return new ParameterBuilder()
            .name(name)
            .description(description)
            .modelRef(new ModelRef("string"))
            .defaultValue(defaultVal)
            .parameterType("header")
            .required(false).build();
    }
}
