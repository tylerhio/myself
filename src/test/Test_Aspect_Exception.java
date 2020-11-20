package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.UserDao;
import service.PersonService;
import service.UserService;
import service.pet.Dog;


public class Test_Aspect_Exception {
	
	@Test
	public void  test01(){
		
		ApplicationContext context =new ClassPathXmlApplicationContext("./applicationContext_Exception1.xml");
		UserService us =  (UserService) context.getBean("userServlet");
		us.addUser();
			
	}
	@Test
	public void  test02(){
		
		ApplicationContext context =new ClassPathXmlApplicationContext("./applicationContext_afterReturn.xml");
		UserService us =  (UserService) context.getBean("userServlet");
		us.addUser();
			
	}


	
}
