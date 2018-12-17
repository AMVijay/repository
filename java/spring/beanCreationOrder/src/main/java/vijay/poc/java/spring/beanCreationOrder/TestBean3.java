package vijay.poc.java.spring.beanCreationOrder;

import org.springframework.stereotype.Component;

@Component("TestBean3")
public class TestBean3 {
	
	public TestBean3() {
		System.out.println("TestBean3");
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
