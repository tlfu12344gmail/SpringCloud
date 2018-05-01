package com.springboot.oauth2.config;

import com.springboot.oauth2.config.custom.CustomFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    CustomFilterSecurityInterceptor customFilterSecurityInterceptor;

    @Override
    public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) throws Exception {
        resourceServerSecurityConfigurer
                .resourceId("WEBS");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(customFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}