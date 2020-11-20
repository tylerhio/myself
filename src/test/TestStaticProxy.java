package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.UserService;


public class TestStaticProxy {
	
	@Test
	public void test01(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_static.xml");
		UserService userServlet = (UserService) context.getBean("userServlet");
		
		userServlet.addUser();
		
	}
	
	

	
}
