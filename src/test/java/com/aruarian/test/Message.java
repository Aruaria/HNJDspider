package com.aruarian.test;

import org.junit.Test;

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
	public String showList(String a) {
		String [] message = a.split("<table");
//		String temp = "";
//		int index =message[0].indexOf("Ժ(");
//		temp=message[0].substring(index);
//		int t = temp.indexOf("<");
		/*ͷ����Ϣ*/
		String backMessage = this.str("Ժ(", message[0]);
		backMessage += "\n"+this.str("����", message[0]);
		backMessage += "\n"+this.str("ѧ��", message[0]);
		backMessage += "\n"+this.str("����", message[0]);
		backMessage += "\n������ȡ"+this.str("ʱ��", message[0]);
		String theMessage="";
		for(int i=1;i<message.length-1;i++) {
			if(i%2!=0) {
				theMessage += "\n"+this.str("ѧ��ѧ��", message[i]);
			}else {
				theMessage += "\n"+"���\t�γ�/����\tѧ��\t���\t�޶�����\t���˷�ʽ\t�ɼ�\t���ѧ��\t����\tѧ�ּ���\t��ע\n";
				String classes [] = message[i].split("<tbody");//��񻮷�
				String temp [] =classes[1].split("<tr");//�л���
				String tr = "",td="";
				for(int j=1;j<temp.length;j++) {
					String temp2 [] = temp[j].split("</td");//�л���
					td="";
					for(int q =0;q<temp2.length;q++) {
						int b =temp2[q].lastIndexOf(">");
						td+=(temp2[q].substring(b+1, temp2[q].length())+"\t");
					}
					tr+=(td+"\n");
				}
				theMessage+=tr;
				
			}
			
		}
		backMessage+=theMessage;
		return backMessage;
	}
	public String str(String t,String m) {
		int index =m.indexOf(t);
		String temp = m.substring(index);
		int n = temp.indexOf("<");
		String backMessage = m.substring(index, index+n);
		return backMessage;
	}
//	@Test
//	public void test2() {
//		String a = "<td style=\"text-align: center;\">���´�ҵ������ְҵ�����е�Ӧ��";
//		int b =a.lastIndexOf(">");
//		System.out.println(a.substring(b+1, a.length()));
//	}
}
