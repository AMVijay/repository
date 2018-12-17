package vijay.poc.java.spring.beanCreationOrder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanCreationOrderApplicationTests {

	@Autowired
	private ApplicationContext context;
	
	@Test
	public void contextLoads() {
		Assert.assertNotNull(context);
				
		for (String name : context.getBeanDefinitionNames()) {
	        System.out.println(name);
	    }

	}

}
