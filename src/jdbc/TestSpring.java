package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import jdbc.test.User;
import jdbc.test.UserMapper;



import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;






public class TestSpring {
	
	private DataSource dataSource;
	
	
	@Before
	public void before(){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//获取数据源
		dataSource = (DataSource) context.getBean("dataSource");
	}
	
	//通过传统方法 查询数据
	@Test
	public void test01() throws SQLException{
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//获取数据源
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		
		//获取数据库链接
		Connection connection = dataSource.getConnection();
		
		//判断数据库链接是否正确
		System.out.println(connection.isClosed());  //true表示连接异常  如果false表示连接正确
		
	}
	
	//查全部数据信息
	@Test
	public void test02() throws SQLException{
		//获取数据库连接
		Connection conn = dataSource.getConnection();
		
		String sql = "select * from user";
		//获取statement
		PreparedStatement statement = conn.prepareStatement(sql);
		
		//获取结果集
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()){
			//手动封装
			User user = new User();
			user.setId(resultSet.getInt(1));
			user.setName(resultSet.getString(2));
			user.setAge(resultSet.getInt("age"));
			System.out.println(user);
		}
	}
	
	
	//新增用户
	@Test
	public void test03(){
		User user = new User();
		user.setName("刘备A");
		user.setAge(40);
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取数据源
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		String sql = "insert into user(id,name,age) values(null,?,?)";
		
		int rows = jdbcTemplate.update(sql,user.getName(),user.getAge());
		System.out.println("影响行数："+rows);
	}
	
	//修改操作
	@Test
	public void test04(){
		User user = new User();
		user.setId(117);
		user.setName("关羽");
		user.setAge(43);
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取数据源
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		String sql = "update user set name = ?,age = ? where id = ?";
		
		int rows = jdbcTemplate.update(sql,user.getName(),user.getAge(),user.getId());
		System.out.println("影响行数："+rows);	
	}
	
	//修改操作
	@Test
	public void test05(){
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取数据源
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		String sql = "delete from user where id = ?";
		
		int rows = jdbcTemplate.update(sql, 118);
		System.out.println("影响行数："+rows);	
	}
	
	//数据的查询
	@Test
	public void test06(){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取数据源
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		String sql ="select * from user";
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		 
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	//需求：将Map自动转化为User对象
	@Test
	public void test07(){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取数据源
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		String sql ="select * from user";
		
		UserMapper userMapper = new UserMapper();  //将结果集转化为User对象
		List<User> userList = jdbcTemplate.query(sql, userMapper);
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	
	//通过反射机制，自动完成映射
	@Test
	public void test08(){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取数据源
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		String sql ="select * from user";
		
		//UserMapper userMapper = new UserMapper();  //将结果集转化为User对象
		
		BeanPropertyRowMapper<User> userMapper = 
				new BeanPropertyRowMapper<User>(User.class);
		//说明：通过反射机制，为对象的属性赋值，
		
		List<User> userList = jdbcTemplate.query(sql, userMapper);
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	
	//查询单个数据
	@Test
	public void test09(){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取数据源
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		String sql ="select * from user where id = ?";
		BeanPropertyRowMapper<User> userMapper = 
				new BeanPropertyRowMapper<User>(User.class);
		
		User user = jdbcTemplate.queryForObject(sql, userMapper,51);
		System.out.println(user);
		
		
	}
	
	
	
	

}
