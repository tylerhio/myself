package test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import proxy.DynamicProxy_cglib;


import service.Hello;
import service.UserService;

import tx.TranManager;



public class Test_Proxy_cglib {	
	
	@Test
	public void test01(){
		
		ApplicationContext context =new ClassPathXmlApplicationContext("./applicationContext_cglib.xml");
		//获取目标对象
		UserService target = (UserService) context.getBean("target");
		System.out.println(target.getClass());
		
		//获取事务处理类
		TranManager tx = (TranManager) context.getBean("_transactionManager");
		
		//通过动态代理获取代理对象
		UserService userService = (UserService) DynamicProxy_cglib.getProxy(target, tx);
		
		System.out.println(userService.getClass());
		//通过代理对象执行操作
		userService.addUser();
		
	}
	
	@Test
	public void test02(){
		ApplicationContext context = new ClassPathXmlApplicationContext("./applicationContext_cglib.xml");
		
		//获取目标对象
		Hello  hello = (Hello) context.getBean("hello");
		
		//获取事务处理类
		TranManager _tx = (TranManager) context.getBean("_transactionManager");
				
		Hello proxy = (Hello) DynamicProxy_cglib.getProxy(hello, _tx);
		
		//输出的是对象的类型
		System.out.println(proxy.getClass());
		proxy.say();
	}
	
}
