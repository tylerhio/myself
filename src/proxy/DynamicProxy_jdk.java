package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import tx.TranManager;

/**
 * jdk动态代理
 * 对实现相同接口的类进行解析class文件，反射对应方法，进行操作；
 * 
 * 代理对象与被代理者实现相同的接口.
 * 
 */
public class DynamicProxy_jdk {
	
	// 为方法添加事务操作
	public static Object getProxy(final Object target, final TranManager tx) {
		// 创建代理对象
		Object proxy = Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(),
				new InvocationHandler() {
					// 代理对象执行方法时，采用回调的机制运行
					public Object invoke(
							Object proxy,
							Method method,
							Object[] args) throws Throwable {
						Object result = null;
						try {
							System.out.println("jdk动态代理事务开始");
							tx.begin();
							
							// 让目标方法执行
							result = method.invoke(target, args);
							
							tx.commit();
							System.out.println("jdk动态代理执行事务提交");
						} catch (Exception e) {
							System.out.println("jdk动态代理事务回滚");
							tx.rollback();
						}
						return result;
					}
				});
		return proxy;
	}

}
