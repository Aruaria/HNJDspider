package com.aruarian.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
	public static void main(String[] args) {
		/*md5º”√‹*/
		String t = DigestUtils.md5Hex(DigestUtils.md5Hex("_u8647=MTgyMzIzMjEyNDM7O0VERTA3MDRBRDYxRjI5NUExODY5QUFBMjlGN0ExNTg0&_p8647=b4a217c365df060b8e66e192d462466d&randnumber=8647&isPasswordPolicy=0&txt_mm_expression=8&txt_mm_length=11&txt_mm_userzh=1")+DigestUtils.md5Hex("2020-06-29 20:29:05"));
		System.out.println(t);
		
		/*base64±‡¬ÎΩ‚¬Î*/
//		Base64.Decoder decoder = Base64.getDecoder();
//		Base64.Encoder encoder = Base64.getEncoder();
//		
//		String text = "1823232****"+";;"+"EDE0704AD61F295A1869AAA29F7A1584";
//		byte[] textByte = null;
//		try {
//			textByte = text.getBytes("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String encodedText = encoder.encodeToString(textByte);
//		System.out.println(encodedText);
	}
	
	
	
}
