package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import tx.TranManager;


//切面类   具体的功能模块的集合  事务操作、日志处理、异常处理
public class Aspect_tx {
	
	
	@Autowired
	private TranManager tx;
	
	//通知： 就是具体功能模块的实现   事务处理
	
	//环绕通知          连接点    
	//环绕通知中 必须添加ProceedingJoinPoint
	public void around(ProceedingJoinPoint joinPoint) throws Throwable{
		
		//获取目标对象
		Class targetClass = joinPoint.getTarget().getClass();
		System.out.println("我是目标对象"+targetClass);
		
		//1、执行下一个通知，2、获取目标对象的方法  ；
		String methodName = joinPoint.getSignature().getName();
		System.out.println(methodName);
		
		tx.begin();  //事务开始
		joinPoint.proceed();//目标方法执行
		tx.commit(); //事务提交
	}
	
	
	//其他通知类型 添加JoinPoint   只有环绕通知添加ProceedingJoinPoint
	public void before(JoinPoint joinPoint){
		
		joinPoint.getSignature();
		System.out.println("我是一个通知方法");
	}
	
	public void afterReturn(){
		System.out.println("我是一个后置通知");
	}
	
	public void afterThrow(){
		System.out.println("我是一个异常通知");
	}
	
	public void after(){
		System.out.println("我是一个最终通知");
	}
	
	
	
	
	


}
