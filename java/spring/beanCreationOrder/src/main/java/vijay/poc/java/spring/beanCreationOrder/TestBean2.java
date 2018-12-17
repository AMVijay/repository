package vijay.poc.java.spring.beanCreationOrder;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component("TestBean2")
@DependsOn("TestBean3")
public class TestBean2 {

	public TestBean2() {
		System.out.println("TestBean2");
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
