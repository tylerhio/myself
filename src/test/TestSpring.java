package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.UserDao;
import service.PersonService;
import service.UserService;
import service.pet.Dog;



public class TestSpring {
	
	@Test
	public void test01(){
		
		ApplicationContext context =new ClassPathXmlApplicationContext("applicationContext_aop.xml");
		//目标对象
		UserService userService = (UserService) context.getBean("target");
		System.out.println(userService.getClass());
		//连接点
		userService.addUser();
	
		
	}
	
	
	@Test
	public void test02(){
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext_aop.xml");
		//目标对象
		UserDao userDao = (UserDao) context.getBean("userDaoImpl");
		System.out.println(userDao.getClass());
		userDao.addUser();
		
	}
	
	@Test
	public void test03(){
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext_aop.xml");
		//目标对象
		PersonService  personService  =(PersonService) context.getBean("personService");
		
		System.out.println(personService.getClass());
		personService.addPerson();  //连接点
	}
	
	
	@Test
	public void test04(){
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext_aop.xml");
		//目标对象
		Dog  dog = (Dog) context.getBean("dog");
		System.out.println(dog.getClass());
		dog.happy();
		
	}
}
