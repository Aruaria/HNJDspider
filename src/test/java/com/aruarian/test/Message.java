package com.aruarian.test;

import org.junit.Test;

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
	public String showList(String a) {
		String [] message = a.split("<table");
//		String temp = "";
//		int index =message[0].indexOf("院(");
//		temp=message[0].substring(index);
//		int t = temp.indexOf("<");
		/*头部信息*/
		String backMessage = this.str("院(", message[0]);
		backMessage += "\n"+this.str("行政", message[0]);
		backMessage += "\n"+this.str("学号", message[0]);
		backMessage += "\n"+this.str("姓名", message[0]);
		backMessage += "\n本次爬取"+this.str("时间", message[0]);
		String theMessage="";
		for(int i=1;i<message.length-1;i++) {
			if(i%2!=0) {
				theMessage += "\n"+this.str("学年学期", message[i]);
			}else {
				theMessage += "\n"+"序号\t课程/环节\t学分\t类别\t修读性质\t考核方式\t成绩\t获得学分\t绩点\t学分绩点\t备注\n";
				String classes [] = message[i].split("<tbody");//表格划分
				String temp [] =classes[1].split("<tr");//行划分
				String tr = "",td="";
				for(int j=1;j<temp.length;j++) {
					String temp2 [] = temp[j].split("</td");//列划分
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
//		String a = "<td style=\"text-align: center;\">创新创业方法在职业教育中的应用";
//		int b =a.lastIndexOf(">");
//		System.out.println(a.substring(b+1, a.length()));
//	}
}
