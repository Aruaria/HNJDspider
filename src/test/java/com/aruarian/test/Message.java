package com.aruarian.test;

public class Message {
	/*
	 * 处理返回的Message
	 */
	public String doDessage(String message) {
		int index = message.indexOf("小时");
		String times = message.substring(index+5, index+8);
		String backMessage = "!!!警告!!!   24小时内连续输错5次会被教务系统锁号，你已输"+times+",提示：初始密码和帐号相同。";  
		return backMessage;
	}
}
