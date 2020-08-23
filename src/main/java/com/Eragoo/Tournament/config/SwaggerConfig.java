package com.Eragoo.Tournament.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import springfox.documentation.builders.AlternateTypeBuilder;
import springfox.documentation.builders.AlternateTypePropertyBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.schema.AlternateTypeRules.newRule;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final TypeResolver typeResolver;

    public SwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    Docket swaggerDocket() {
        return new Docket(SWAGGER_2)
                .alternateTypeRules(newRule(typeResolver.resolve(Pageable.class), pageableMixin()))
                .alternateTypeRules(newRule(typeResolver.resolve(Sort.class),
                        typeResolver.resolve(List.class, String.class)))
                .select()
                .apis(basePackage("com.Eragoo.Tournament"))
                .build();
    }

    private Type pageableMixin() {
        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(
                        String.format("%s.generated.%s",
                                Pageable.class.getPackage().getName(),
                                Pageable.class.getSimpleName()))
                .withProperties(Arrays.asList(
                        property(Integer.class, "page"),
                        property(Integer.class, "size"),
                        property(Sort.class, "sort")
                ))
                .build();
    }

    private AlternateTypePropertyBuilder property(Class<?> type, String name) {
        return new AlternateTypePropertyBuilder()
                .withName(name)
                .withType(type)
                .withCanRead(true)
                .withCanWrite(true);
    }
}