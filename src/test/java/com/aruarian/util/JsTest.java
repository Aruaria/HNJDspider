package com.aruarian.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.List;
 
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;
 
public class JsTest {
	
  // 定义变量,存储到引擎上下文中
  private static void test1(ScriptEngine engine) {
 
    engine.put("msg", "张三是个好学生");
    System.out.println(engine.get("msg")); // 获取变量的值
 
    try {
     engine.eval("msg='java是门好用的语言';"); // 修改变量的值
    } catch (ScriptException e1) {
     e1.printStackTrace();
    }
    System.out.println(engine.get("msg"));
  }
 
  // 定义一个对象
  private static void test2(ScriptEngine engine) {
    String str = "var user = {name:'张三',age:18,subjects:['计算机','英语']};";
    str += "println(user.name);";
    try {
     engine.eval(str);
    } catch (ScriptException e) {
     e.printStackTrace();
    }
  }
 
  // 定义一个函数
  @Test
  private static void test3(ScriptEngine engine) {
 
    try {
     engine.eval("function add(a,b){var sum = a+b;return sum;}");
    } catch (ScriptException e) {
     e.printStackTrace();
    }
 
    try {
     // 取得调用接口
     Invocable jsInvoke = (Invocable) engine;
     // 执行脚本中定义的方法
     Object result = jsInvoke.invokeFunction("add", new Object[] { 13, 20 });
     System.out.println(result);
    } catch (NoSuchMethodException | ScriptException e) {
     e.printStackTrace();
    }
  }
 
  // 导入其他java包,使用其他包中的java类
  private static void test4(ScriptEngine engine) {
 
    String jsCode = "importPackage(java.util); var list = Arrays.asList([\"浙江大学\",\"清华大学\",\"北京大学\"]); ";
    try {
     engine.eval(jsCode);
    } catch (ScriptException e) {
     e.printStackTrace();
    }
    List<String> list2 = (List<String>) engine.get("list");
    for (String temp : list2) {
     System.out.println(temp);
    }
 
  }
 
  public static void GetJs(String js) {
	 URL des = JsTest.class.getClassLoader().getResource(js);
	// 获得脚本引擎
    ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine engine = sem.getEngineByName("javascript");
    Reader r= null;
	try {
		 r = new FileReader(des.getPath());
		 engine.eval(r);
	} catch (FileNotFoundException | ScriptException e) {
		e.printStackTrace();	
	}finally {
	 try {
		 r.close();
	 } catch (IOException e) {
		 e.printStackTrace();
	 }  
	}
	// 取得调用接口
    Invocable jsInvoke = (Invocable) engine;
    // 执行脚本中定义的方法
    try {
		Object des_str = jsInvoke.invokeFunction("strEnc", new Object[] { "_u0114=MTgyMzIzMjEyNTY7OzQwQzU1ODgzNTk5OEY2M0QyODc4QTRCMzE0RDU5OTBC&_p0114=78193f308ce0c10c815567b322eb2c62&randnumber=0114&isPasswordPolicy=0&txt_mm_expression=8&txt_mm_length=11&txt_mm_userzh=1", "5051593517298738783",null,null });
		System.out.println(des_str);
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	} catch (ScriptException e) {
		e.printStackTrace();
	}
  }
  public static void main(String[] args) {
 
    // 获得脚本引擎
    ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine engine = sem.getEngineByName("javascript");
//    test5(engine);
//    System.out.println(System.getProperty("user.dir"));
//    test1(engine);
//    test2(engine);  // java 8 出现异常：Caused by: <eval>:1 ReferenceError: "println" is not defined
//    test3(engine);
//    test4(engine);  // java 8出现异常：Caused by: <eval>:1 ReferenceError: "importPackage" is not defined
    
    URL des = JsTest.class.getClassLoader().getResource("Js/jkingo.des.js");
////    URL base64 = JsTest.class.getClassLoader().getResource("base64.js");
////    URL utf = JsTest.class.getClassLoader().getResource("utf.js");
    Reader r= null;
	try {
		 r = new FileReader(des.getPath());
		 engine.eval(r);
	} catch (FileNotFoundException | ScriptException e) {
		e.printStackTrace();	
	}finally {
	 try {
		 r.close();
	 } catch (IOException e) {
		 e.printStackTrace();
	 }  
	}
    
    // 取得调用接口
    Invocable jsInvoke = (Invocable) engine;
    // 执行脚本中定义的方法
    try {
		Object des_str = jsInvoke.invokeFunction("strEnc", new Object[] { "_u0114=MTgyMzIzMjEyNTY7OzQwQzU1ODgzNTk5OEY2M0QyODc4QTRCMzE0RDU5OTBC&_p0114=78193f308ce0c10c815567b322eb2c62&randnumber=0114&isPasswordPolicy=0&txt_mm_expression=8&txt_mm_length=11&txt_mm_userzh=1", "5051593517298738783",null,null });
		System.out.println(des_str);
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ScriptException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }
 
  //执行一个js文件(我们将a.js至于项目的src下即可)
  private static void test5(ScriptEngine engine) {
	  URL is = JsTest.class.getClassLoader().getResource("Js/jkingo.des.js");
	  Reader r= null;
	  try {
		  r = new FileReader(is.getPath());
		  engine.eval(r);
	  } catch (FileNotFoundException | ScriptException e) {
		  e.printStackTrace();
	  }finally {
		  try {
			  r.close();
		  } catch (IOException e) {
			  e.printStackTrace();
		  }  
	  }
  }
} 
