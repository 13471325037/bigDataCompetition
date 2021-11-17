package com.sgmw.bigDataCompetition.conf;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 *
 * @author Louis
 * @date Oct 29, 2018
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.host}")
    private String host;

    @Bean
    public Docket createRestApi(){
        ApiInfo apiInfo = new ApiInfoBuilder().title("大数据应用竞赛 API ").version("1.0").build();
        return new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

}