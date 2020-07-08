package com.aruarian.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.junit.Test;
/**
 * 将拷贝的代码中大量的非法字符  "聽" 给去掉
 */
public class textDelete {
	public static void main(String[] args) {
		String path = "C:\\Users\\26047\\eclipse-workspace\\Jsoup\\src\\main\\java\\com\\aruarian\\test\\Des.java";
	    String line = null;
	    String content = ""; // 文件内容
	    BufferedReader br = null;
	    BufferedWriter bw = null;
	    try {
	        // br = new BufferedReader(new FileReader(path));
	        br = new BufferedReader(new FileReader(new File(path))); // 与上等效
	        while (null != (line = br.readLine())) {
	        	line = line.replace("聽", "");
	            System.out.println(line);
	            content += line + "\n";
	        }
	        br.close();
	        System.out.println("--------------风骚分隔线----------------------------------------------------------------------------------------------");
	        File newFile = new File("C:\\\\Users\\\\26047\\\\Desktop\\\\demo.java");
			if(!newFile.exists()) {
				newFile.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(newFile));
			bw.write(content);
			bw.close();
			
			
			//查看新文件内容
//			String txt = "";
//			try {
//				br = new BufferedReader(new FileReader(new File("C:\\Users\\26047\\Desktop\\demo.java")));
//				while(null !=(line = br.readLine())) {
//					txt += line+"\n";
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println(txt);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
