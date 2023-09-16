package com.zyao.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Field;
import java.util.*;

@Configuration
public class SpringdocOpenapiConfiguration implements WebMvcConfigurer {

    private final SwaggerProperties swaggerProperties;

    public SpringdocOpenapiConfiguration(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public OpenAPI springDocOpenAPI() {
        //配置认证、请求头参数
        Components components = new Components();
//        Map<String, Object> myHeader2extensions = new HashMap<>(2);
//        myHeader2extensions.put("name", "myHeader2");
        components
                .addSecuritySchemes("Authorization", // key值，
                        new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY) //请求认证类型
                                .name("ZYAO_BOOT_Authorization") //密钥名称
                                .description("验证当前登陆人") //描述
                                .in(SecurityScheme.In.HEADER))//API密钥的位置。有效值"query","header"或"cookie"
//                .addSecuritySchemes("Authorization2", // key值
//                        new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP) //请求认证类型
//                                .name("Authorization2")
//                                .description("token令牌2") //描述
//                                .scheme("bearer") // 在RFC7235中定义的Authorization标头中使用的HTTP Authorization方案的名称
//                                .bearerFormat("JWT")) //向客户端提示以标识承载令牌的格式。承载令牌通常由授权服务器生成，因此此信息主要用于文档目的。
//                .addSecuritySchemes("Authorization3", // key值
//                        new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP) //请求认证类型
//                                .name("Authorization3") //标题
//                                .description("token令牌3") //描述
//                                .scheme("basic")) // 在RFC7235中定义的Authorization标头中使用的HTTP Authorization方案的名称
                .addParameters("myHeader1", new Parameter().in("header").schema(new StringSchema()).name("myHeader1"))
                .addParameters("tenantCode", new HeaderParameter().required(true).name("tenant_code").description("多租户标识").schema(new StringSchema()).required(false))
        ;

        // 接口调试路径
        Server tryServer = new Server();
        tryServer.setUrl(swaggerProperties.getTryHost());

        // 名字和创建的SecuritySchemes一致
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization");
        List<SecurityRequirement> list = new ArrayList<>();
        list.add(securityRequirement);


        return new OpenAPI()
                .components(components)
                .security(list)
                .servers(Collections.singletonList(tryServer))
                .info(new Info()
                        .title(swaggerProperties.getApplicationName() + " Api Doc")
                        .description(swaggerProperties.getApplicationDescription())
                        .version("boot-zyao Version: " + swaggerProperties.getApplicationVersion() + "\n Spring Boot Version: " + SpringBootVersion.getVersion())
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc Full Documentation")
                        .url("https://springdoc.org/")
                );
    }

    /**
     * 添加全局的请求头参数
     */
    @Bean
    public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> {
                    operation.addParametersItem(new HeaderParameter().$ref("#/components/parameters/tenantCode")); // 添加到接口文档中配置
                    operation.addParametersItem(new HeaderParameter().$ref("#/components/parameters/myHeader1")); // 添加到接口文档中配置
                });
    }

    /**
     * 通用拦截器排除设置，所有拦截器都会自动加springdoc-opapi相关的资源排除信息，不用在应用程序自身拦截器定义的地方去添加，算是良心解耦实现。
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils.getField(registrationsField, registry);
            if (registrations != null) {
                for (InterceptorRegistration interceptorRegistration : registrations) {
                    interceptorRegistration.excludePathPatterns("/springdoc**/**");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}