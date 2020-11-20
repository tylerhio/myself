package aspect;

import org.aspectj.lang.JoinPoint;


/**
 * 
 *
 */


public class  Aspect_afterThrow_Exception {
	

	public void afterThrow(JoinPoint jp,Throwable pri){
		System.out.println("异常信息"+pri.getMessage());
		System.out.println("异常类型"+pri.getClass());
		String jsname=jp.getSignature().getName();
		System.out.println(jsname);

				
	}
		

}
