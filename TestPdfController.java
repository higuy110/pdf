package com.baec.antiviral.server.pdf;

import com.baec.antiviral.server.pdf.model.InsuranceInfo;
import com.baec.antiviral.server.pdf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author dingjy
 * @Date 2021/12/29 16:39
 */
@RestController
public class TestPdfController {
    @Autowired
    private TemplateEngine engine;

    @GetMapping("/test/111")
    public void test() throws Exception {
        // 创建model，需要指定模版引擎和具体的模版，“报告模版”指的是资源目录下/html2pdfTemplate/报告模版.html文件。如果是springboot项目，那么就是在resources文件夹下面
        Model model = new Model(engine,"111");
        model.setName("测试生成pdf");
        List<InsuranceInfo> insuranceInfos = new ArrayList<>();
        InsuranceInfo record1 = new InsuranceInfo();
        record1.setExpirationDate("2021-01-19");
        record1.setDescription("轮胎损坏");
        insuranceInfos.add(record1);
        InsuranceInfo record2 = new InsuranceInfo();
        record2.setExpirationDate("2021-03-06");
        record2.setDescription("后视镜损坏");
        insuranceInfos.add(record2);
        model.setInsuranceInfos(insuranceInfos);
        //生成pdf，指定目标文件路径D:\antiviral
        model.parse2Pdf("D:\\antiviral\\templates\\test.pdf");
    }

}
