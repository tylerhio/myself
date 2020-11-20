package proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.UserService;
import tx.TranManager;

/**
 * 静态代理
 * 代理对象与被代理者实现相同的接口.
 * 
 * 第三方代理类针对特定的类进行操作
 * 
 * 
 * 静态代理只能代理一类业务，如果需要处理其他业务，需要重新定义代理对象。
 *
 */
@Component("userService")
public class StaticProxy implements UserService{

	@Autowired  
	private UserService target;  //id:target 对应  userServiceImpl类
	
	@Autowired
	private TranManager tx;
	

	public void addUser(){
		tx.begin();       //额外的操作
		target.addUser(); //本职工作
		tx.commit();
		
	}
	
}
