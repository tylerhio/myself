package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspect_anno_Exception {
	
	@AfterThrowing(value="execution(* servlet..*.*(..))",throwing="throwable")
	public void afterThrow(JoinPoint joinPoint,Throwable ex){
		
		Class targetClass = joinPoint.getTarget().getClass();
		System.out.println("目标对象的类型："+targetClass);
		
		String methodName = joinPoint.getSignature().getName();
		System.out.println("目标方法："+methodName);
		
		Class exClass = ex.getClass();
		System.out.println("异常的类型："+exClass);
		
		String msg = ex.getMessage();
		System.out.println("异常信息："+msg);						
	}
	
}
