package vijay.poc.java.spring.batch.simplebatch.service;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import vijay.poc.java.spring.batch.simplebatch.dto.Person;

@Component(value="PersonItemProcessor")
public class PersonItemProcessor implements ItemProcessor<Person, Person>{

	@Override
	public Person process(final Person item) throws Exception {
		final String firstName = item.getFirstName().toUpperCase();
        final String lastName = item.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        System.out.println("Converting (" + item + ") into (" + transformedPerson + ")");

        return transformedPerson;
	}
	
}
