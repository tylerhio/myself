package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anno.Control;


import dao.UserDao;



@Service("target")  //业务处理类     被代理者
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	//@Override	
	@Control(i=1)
	public void addUser(){	
		userDao.addUser();
		System.out.println("UserServiceImpl的addUser方法");
		
	}


	
	
	//事务控制
	/*@Autowired
	private TransactionManager tx;  //单独的模块开发
	@Override
	public void addUser() {
		userDao.addUser();
		try {
			tx.begin();
			userDao.addUser();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			
		}		
	}
	
	public void updateUser(){
		try {
			tx.begin();
			System.out.println("修改用户");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			
		}
		
	}*/

}
