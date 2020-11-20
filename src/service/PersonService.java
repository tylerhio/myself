package service;

import org.springframework.stereotype.Service;

@Service 
public class PersonService {
	
	public void addPerson(){
		System.out.println("添加一个Person");
	}
}
