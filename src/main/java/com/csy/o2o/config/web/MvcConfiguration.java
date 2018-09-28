package com.csy.o2o.config.web;

import com.csy.o2o.interceptor.shopadmin.ShopLoginInterceptor;
import com.csy.o2o.interceptor.shopadmin.ShopPermissionInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//相当于<mvc:annotation-DRIVER>
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer,ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 视图解析器
     * @return
     */
    @Bean
    public ViewResolver createViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setApplicationContext(this.applicationContext);
        viewResolver.setCache(false);
        viewResolver.setPrefix("/WEB-INF/html/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /**
     * 文件解析器
     * @return
     */
    @Bean
    public CommonsMultipartResolver createMultipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(20971520);
        return multipartResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String interceptPath = "/shopadmin/**";
        InterceptorRegistration logInterceptor = registry.addInterceptor(new ShopLoginInterceptor());
        logInterceptor.addPathPatterns(interceptPath);
        InterceptorRegistration shopInterceptor = registry.addInterceptor(new ShopPermissionInterceptor());
        shopInterceptor.addPathPatterns(interceptPath);
        shopInterceptor.excludePathPatterns("/shopadmin/shoplist");
        shopInterceptor.excludePathPatterns("/shopadmin/getshoplist");
        shopInterceptor.excludePathPatterns("/shopadmin/getshopinitinfo");
        shopInterceptor.excludePathPatterns("/shopadmin/addshop");
        shopInterceptor.excludePathPatterns("/shopadmin/modifyShop");
        shopInterceptor.excludePathPatterns("/shopadmin/shopmanagement");
        shopInterceptor.excludePathPatterns("/shopadmin/getshopmanageinfo");
    }
}