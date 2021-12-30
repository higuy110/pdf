package com.baec.antiviral.server.pdf.model;

import lombok.Data;
import org.thymeleaf.TemplateEngine;

import java.util.List;

@Data
public class Model extends AbstractTemplate {
    // 构造函数
    public Model(TemplateEngine engine, String templateName) {
        super(engine, templateName);
    }

    private String name;
    private List<InsuranceInfo> insuranceInfos;

}