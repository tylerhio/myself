package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import proxy.DynamicProxy_jdk;
import service.UserService;
import tx.TranManager;

public class TestProxy_jdk {
	
	
	@Test
	public void test01(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_jdk.xml");
		
		//获取目标对象
		UserService target = (UserService) context.getBean("target");
		System.out.println(target.getClass());
		
		//获取事务处理类
		TranManager tx = (TranManager) context.getBean("tx");
		
		//通过动态代理获取代理对象
		UserService userService = (UserService) DynamicProxy_jdk.getProxy(target, tx);
		
		System.out.println(userService.getClass());
		//通过代理对象执行操作
		userService.addUser();
		
	}
	
	

	
}
