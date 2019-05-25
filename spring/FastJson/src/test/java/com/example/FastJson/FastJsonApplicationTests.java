package com.example.FastJson;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastJsonApplicationTests {

	private List<Person> listOfPersons = new ArrayList<Person>();

	@Before
	public void setUp() {
		listOfPersons.add(new Person(15, "John Doe", new Date()));
		listOfPersons.add(new Person(20, "Janette Doe", new Date()));
	}

	@Test
	public void  whenJavaList_thenConvertToJsonCorrect() {
        String jsonOutput= JSON.toJSONString(listOfPersons);
        System.out.println("Json string: ");
        System.out.println(jsonOutput);
	}

}
