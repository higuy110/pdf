package com.baec.antiviral.server.pdf;

import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

@Slf4j
public final class HtmlUtil {

    private HtmlUtil() {
    }
    // 字体路径，放在资源目录下
    private static final String FONT_PATH = "simsun.ttc";

    public static void file2Pdf(File htmlFile, String pdfFile) {
        try (OutputStream os = new FileOutputStream(pdfFile)) {
            String url = htmlFile.toURI().toURL().toString();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(url);
            // 解决中文支持
            ITextFontResolver fontResolver = renderer.getFontResolver();
            renderer.layout();
            renderer.createPDF(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void html2Pdf(String html, String pdfFile) {
        String dir = StringUtils.substringBeforeLast(pdfFile, File.separator);
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        try (OutputStream os = new FileOutputStream(pdfFile)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            // 解决中文支持
            ITextFontResolver fontResolver = renderer.getFontResolver();
            //获取字体的路径
            String path = HtmlUtil.class.getClassLoader().getResource(FONT_PATH).getPath();
            fontResolver.addFont(path, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.layout();
            renderer.createPDF(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String path = HtmlUtil.class.getClassLoader().getResource("simsun.ttc").getPath();
        System.out.println(path);
    }
}