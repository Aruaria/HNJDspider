package com.aruarian.test;

public class Message {
	/*
	 * �����ص�Message
	 */
	public String doDessage(String message) {
		int index = message.indexOf("Сʱ");
		String times = message.substring(index+5, index+8);
		String backMessage = "!!!����!!!   24Сʱ���������5�λᱻ����ϵͳ���ţ�������"+times+",��ʾ����ʼ������ʺ���ͬ��";  
		return backMessage;
	}
}
