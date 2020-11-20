package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 注解方式实现AOP ，切面需要注入到Spring容器中
 * 
 * 无需关注通知的执行先后
 * 无需关注切面的执行先后
 *
 */
@Component  
@Aspect		//表示当前对象就是一个切面
public class Aspect_anno {
	
	//定义方法 指定切入点
	@Pointcut(value="execution(* service..*.*(..))")
	public void pointcut(){}
	
	
	//@Before(value="execution(* service..*.*(..))")  //前置通知
	public void before(){
		System.out.println("我是一个注解前置通知");
	}
	
	//@Around(value="execution(* service..*.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("环绕通知开始");
		Object result = joinPoint.proceed();  //连接点
		System.out.println("环绕通知结束");
		
		return result;
	}
	
	
	//@AfterThrowing(value="pointcut()",throwing="throwable")
	public void afterThrow(Throwable throwable){
		System.out.println("我是一个异常通知");
		System.out.println("异常的信息为："+throwable.getMessage());
	}
	
	
	//后置通知
	@AfterReturning(value="pointcut()",returning="msg")
	public void afterReturn(Object msg){
		System.out.println("我是一个后置通知");
		System.out.println("获取返回值参数为："+msg);
	}
	
	
	
	
}
