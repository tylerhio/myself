package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import anno.Control;
import tx.TranManager;

@Component
@Aspect
public class Aspect_anno_tx {
	
	@Autowired
	private TranManager tx;
		
	@Around(value="execution(* service..*.*(..)) && @annotation(transaction)")
	//只有添加了Transaction注解的方法 才会执行通知
	public Object around(ProceedingJoinPoint joinPoint,Control c) throws Throwable{
		Object result = null;		
		try {
			tx.begin();			
			result = joinPoint.proceed();			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

		return result;
	}
	
}
