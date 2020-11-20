package proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import tx.TranManager;

/**
 * 
 * CGLIB动态代理
 * cglib创建的代理对象都是被代理对象的子类。
 * 
 * 
 * 一般情况下，使用JDK居多，特定环境下才使用cglib
 * 如果目标对象有接口则采用JDK动态代理，如果目标对象没有接口采用cglib动态代理。
 * JDK创建代理对象的速度较快，
 * 代理对象的运行速度cgLib快。
 * 
 * 
 *    
 */
public class DynamicProxy_cglib {
	
	//为方法添加事务操作
	public static Object getProxy(final Object target,final TranManager tx){
		
		//1.创建增强器  操作的是二进制码 
		Enhancer enhancer = new Enhancer();
		
		//2.设置父类
		enhancer.setSuperclass(target.getClass());
		
		//3.如果有接口也可以赋值接口，满足数据的完整性，非必要
		enhancer.setInterfaces(target.getClass().getInterfaces());
		
		//4.设定回调接口方法    jdk new InvocationHandler()
		enhancer.setCallback(new MethodInterceptor() {
			
			//代理对象执行方法时，会被intercept方法所拦截
			public Object intercept(
					Object proxy, 
					Method method, 
					Object[] args,
					MethodProxy methodProxy) throws Throwable {
				System.out.println("cglib动态代理事务开始");
				tx.begin(); //事务开始
				
				//目标方法执行
				Object result = method.invoke(target, args);
				
				tx.commit();//事务提交
				System.out.println("cglib动态代理事务提交");
				
				return result;
			}
		});		
		//返回代理对象
		return enhancer.create();
	}
	
}
