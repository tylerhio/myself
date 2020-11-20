package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class Aspect_afterReturn {
	
	public Object around(ProceedingJoinPoint jP) throws Throwable{
		System.out.println("around开始");
		Object result  = jP.proceed();   
		System.out.println("around返回值："+result);
		System.out.println("around结束");
		
		return result;
	}
	
	public void afterReturn(JoinPoint jp,String msg){
		System.out.println("afterReturn后置方法");
		
		System.out.println("afterRetur返回值："+msg);
	}

}
