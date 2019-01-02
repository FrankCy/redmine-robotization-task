package com.redmine.rz.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.config、
 * @email: cy880708@163.com
 * @date: 2019/1/2 下午3:27
 * @mofified By:
 */
public class UploadFileProperties extends WebMvcConfigurerAdapter {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
        // 这样在文件上传的地方就需要进行异常信息的处理了;
        // KB,MB
        factory.setMaxFileSize("128MB");
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("256MB");
        //设置文件路径
        //factory.setLocation("");
        return factory.createMultipartConfig();
    }

}
