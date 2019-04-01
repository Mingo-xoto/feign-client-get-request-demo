package com.xiaoxuan.actuator.config;

import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
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
 * SwaggerConfig     api文档地址：/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.host:}")
    private String swaggerHost;

    @Bean
    public Docket createRestApi() {
        //构造token给测试的时候填写
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("token").description("用户令牌(不需用户鉴权的不需要传)").modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("所有API")
                .apiInfo(defaultTitleInfo())  //可设置默认分组api顶部说明文字
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //只允许生成ApiOperation注解的方法，其他系统内部用的api可隐藏
                .build()
                .globalOperationParameters(pars);
        // k8s环境特殊配置
        if (StringUtils.isNotBlank(swaggerHost)){
            docket.host(swaggerHost);
        }


        return docket;

    }

    /**
     * 默认标题信息
     * @return
     */
    private ApiInfo defaultTitleInfo() {
        return new ApiInfoBuilder()
                .title("接口地址域名和当前swagger地址域名一致")//大标题
                .description("")//详细描述
                .version("1.0")//版本
                .build();
    }

    //========================以下是api模块分组 可在api文档头部下拉框选择不同模块文档的展示


    /*@Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户API")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/user"), regex("/user/.*")))//过滤的接口
                .build()
                .apiInfo(defaultTitleInfo());
    }

   */


}