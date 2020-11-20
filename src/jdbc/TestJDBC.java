package jdbc;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class TestJDBC{
	
	@Test
	public void test0() throws SQLException{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext_jdbc.xml");
	    DataSource dataSource= (DataSource) context.getBean("dataSource"); 
	        
	}

}
