package com.fangcang.titanjr.web.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoshan on 2017/7/26.
 */
@Configuration
public class TitanFinancialSwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
    {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation()
    {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo())
                .includePatterns(".*?");
    }

    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "泰坦金融对外接口",
                "提供金融服务化接口，包括机构注册，用户管理，账户基础管理，提现退款能功能",
                "接口服务条款信息在这里",
                "请联系泰坦金融开发团队团队jinrong@fangcang.com",
                "接口访问许可类型：只允许内部访问",
                "应用程序接口访问许可文件地址");
        return apiInfo;
    }
}
