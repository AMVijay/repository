package vijay.poc.java.spring.beanCreationOrder;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component("TestBean1")
@DependsOn("TestBean2")
public class TestBean1 {

	public TestBean1() {
		System.out.println("TestBean1");
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
