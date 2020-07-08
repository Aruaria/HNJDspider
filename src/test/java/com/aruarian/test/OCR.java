package com.aruarian.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
/**
 * @author aruarian
 * 扫描图像转换为文字
 * 扫描验证码，转换为数字
 *
 */
public class OCR {
    public String Ocr() throws Exception {
    	String result = "";
        ITesseract instance = new Tesseract();
        instance.setDatapath("tessdata"); //相对目录，这个时候tessdata目录和src目录平级
//        instance.setDatapath("E:\\myProgram\\java\\ocrdemo\\tessdata");//支持绝对目录
        instance.setLanguage("eng");//选择字库文件（只需要文件名，不需要后缀名）
        try {
            File imageFile = new File("images/code.jpg");
            result = instance.doOCR(imageFile);//开始识别
//            System.out.println(result);//打印图片内容
        } catch (Exception e) {
//            System.out.println(e.toString());//打印图片内容
        }
        result=result.trim();
        return result;
    }
    @Test
    public void theTest() throws Exception {
    	String a= this.Ocr();
    	a=a.trim();
    	int b = Integer.parseInt(a);
    	System.out.println(b);
    }
}
