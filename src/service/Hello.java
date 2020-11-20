package service;

import org.springframework.stereotype.Component;

import anno.Control;

@Component  
public class Hello {
	@Control(i=0)
	public void say(){
		System.out.println("cglib动态代理 不需要接口！！！");
	}
}	
