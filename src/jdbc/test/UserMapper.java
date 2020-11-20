package jdbc.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


//自己手动编辑
public class UserMapper implements RowMapper<User>{
		
	public User mapRow(ResultSet resultSet, int index) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt("id"));
		user.setName(resultSet.getString("name"));
		user.setAge(resultSet.getInt("age"));
		
		System.out.println("当前正在复制第"+index+"数据");
		
		return user;
	}

}
