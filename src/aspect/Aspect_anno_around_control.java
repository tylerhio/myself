package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import anno.Control;

/**
 * 
 *  权限控制     
 *  获取方法上的注解	
 *  
 *  "execution(* service..*.*(..)) && @annotation(pri)"	
 *  中的 @annotation(c)为格式  
 *  c为注解的对象名称
 *  
 *  
 */


@Component  
@Aspect		
public class Aspect_anno_around_control {
	
	/**
	 * 
	 * 作用：匹配service包下的全部类的全部方法任意参数的方法  并且含有注解Control
	 * 
	 * 
	 */
	@Around("execution(* service..*.*(..)) && @annotation(c)")	
	public Object around(ProceedingJoinPoint joinPoint,Control c) throws Throwable{
		System.out.println("权限控制必须使用环绕通知");
		System.out.println("获取注解属性值为："+c.i());
		return joinPoint.proceed();
	}
	
	
	
}
