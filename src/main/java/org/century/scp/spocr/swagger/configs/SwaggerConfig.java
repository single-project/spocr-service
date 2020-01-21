package org.century.scp.spocr.swagger.configs;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("org.century.scp.spocr"))
        .apis(RequestHandlerSelectors.withClassAnnotation(RequestMapping.class))
        .paths(PathSelectors.regex("/.*"))
        .build()
        .apiInfo(apiEndPointsInfo());
  }


  private ApiInfo apiEndPointsInfo() {
    return new ApiInfoBuilder()
        .title("Single Point of Counterparty Reference API V1")
        .description("SPOCR References REST API")
        .contact(
            new Contact(
                "Alex Kurutin",
                "https://github.com/single-project/spocr-service/wiki",
                "akurutin@gmail.com"))
        .license("GNU General Public License v3.0")
        .licenseUrl("https://github.com/single-project/spocr-service/blob/master/LICENSE")
        .version("1.0.0-alpha")
        .build();
  }
}
