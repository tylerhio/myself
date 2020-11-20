package aspect;


import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import anno.PrivilegeInfo;

import Utils.PriList;


@Component
@Aspect
public class Aspect_anno_reflect {
	
	
	@Around(value="execution(* service..*.*(..))")
	//只有添加了Transaction注解的方法 才会执行通知
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		Object result = null;
		
		//1.获取用户的权限列表
		List<String> priList = PriList.getPriList();  //会有线程安全问题   改造 为threadLocal
		
		String methodName = joinPoint.getSignature().getName(); //获取方法名称
		Object[] argsObj = joinPoint.getArgs();
		Class[] argsClass = new Class[argsObj.length];  //准备class[] 数组
		
		//表示将object数组转化为  class[]
		for (int i = 0; i < argsObj.length; i++) {
			argsClass[i] = argsObj[i].getClass();
		}
		
		//获取目标对象的类型
		Class targetClass = joinPoint.getTarget().getClass();	
		Method method = targetClass.getMethod(methodName, argsClass);
		
		//判断是否还有该注解
		if(method.isAnnotationPresent(PrivilegeInfo.class)){
			//证明当前操作需要进行权限判断
			//获取注解
			PrivilegeInfo info = method.getAnnotation(PrivilegeInfo.class);
			
			//需要的权限
			String priName = info.name();
			
			if(priList.contains(priName)){
				System.out.println("恭喜你 拥有权限");
				result = joinPoint.proceed(); //方法放行
				return result;
			}else{
				System.out.println("用户没有改权限");
				return null;
			}
		}

		result = joinPoint.proceed(); //执行目标方法   放行	
		
		return result;
	}
	
}
