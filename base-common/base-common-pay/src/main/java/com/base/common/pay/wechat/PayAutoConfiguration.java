package com.base.common.pay.wechat;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.base.common.pay.wechat.*")
public class PayAutoConfiguration implements WebMvcConfigurer {

}
