package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspect_anno_around_RunTime {
	
	@Around("execution(* servlet..*.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		
		Long beginTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();   //有通知执行通知  没有执行目标方法
		Long endTime = System.currentTimeMillis();
		
		Class targetClass = joinPoint.getTarget().getClass();
		String methodName = joinPoint.getSignature().getName();
		System.out.println("类型名称："+targetClass);
		System.out.println("方法名称："+methodName);
		System.out.println("方法执行时间："+(endTime - beginTime) +"毫秒");
		
		return result;
	}
	
}
