/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.web;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import skogemann.service.AddressService;
import skogemann.service.CityInfoService;
import skogemann.service.HobbyService;
import skogemann.service.PersonService;
import skogemann.service.ServiceConfig;

/**
 *
 * @author thomas
 */
@Configuration
@ComponentScan(basePackages = {"skogemann.web.controllers"})
@Import(ServiceConfig.class)
public class WebConfig extends WebMvcConfigurationSupport {

@Override
public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
converters.add(new MappingJackson2HttpMessageConverter());
}

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("/**");
    }


    
}
