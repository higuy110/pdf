package com.baec.antiviral.server.pdf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class TemplateEngineConfig {
    // 注入TemplateEngine模版引擎
    @Bean
    public TemplateEngine templateEngine(){
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        // 设置模版前缀，相当于需要在资源文件夹中创建一个html2pdfTemplate文件夹，所有的模版都放在这个文件夹中
        resolver.setPrefix("/templates/");
        // 设置模版后缀
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        // 设置模版模型为HTML
        resolver.setTemplateMode("HTML");
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
    }
}