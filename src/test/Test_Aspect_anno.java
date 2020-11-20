package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;


public class Test_Aspect_anno {
	@Test
	public void test(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("./applicationContext_anno.xml");
		UserService us=(UserService) ac.getBean("target");
		us.addUser();		
	}	
}
