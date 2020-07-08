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
 * ɨ��ͼ��ת��Ϊ����
 * ɨ����֤�룬ת��Ϊ����
 *
 */
public class OCR {
    public String Ocr() throws Exception {
    	String result = "";
        ITesseract instance = new Tesseract();
        instance.setDatapath("tessdata"); //���Ŀ¼�����ʱ��tessdataĿ¼��srcĿ¼ƽ��
//        instance.setDatapath("E:\\myProgram\\java\\ocrdemo\\tessdata");//֧�־���Ŀ¼
        instance.setLanguage("eng");//ѡ���ֿ��ļ���ֻ��Ҫ�ļ���������Ҫ��׺����
        try {
            File imageFile = new File("images/code.jpg");
            result = instance.doOCR(imageFile);//��ʼʶ��
//            System.out.println(result);//��ӡͼƬ����
        } catch (Exception e) {
//            System.out.println(e.toString());//��ӡͼƬ����
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
