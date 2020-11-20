package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository  
public class UserDaoImpl implements UserDao{
	
	public void addUser() {
		System.out.println("UserDaoImpl的addUser方法");
	}
}
