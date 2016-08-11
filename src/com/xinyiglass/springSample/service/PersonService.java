package com.xinyiglass.springSample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.xinyiglass.springSample.entity.Person;


@Service
public class PersonService {
	public List<Person> getPersonInfo(){
		List<Person> personData = new ArrayList<Person>();
		Person p = null;
		p = new Person(1,"С01","25","人员01");
		personData.add(p);
		p = new Person(2,"С02","21","人员02");
		personData.add(p);
		p = new Person(3,"С03","13","人员03");
		personData.add(p);
		return personData;
	}
}
