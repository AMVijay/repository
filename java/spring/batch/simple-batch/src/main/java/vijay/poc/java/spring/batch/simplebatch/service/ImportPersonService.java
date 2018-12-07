package vijay.poc.java.spring.batch.simplebatch.service;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import vijay.poc.java.spring.batch.simplebatch.dto.Person;

@Service
public class ImportPersonService {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Bean(name = "importUserStep")
	public Step step(@Qualifier("csvReader") ItemReader<Person> reader,
			@Qualifier("PersonItemProcessor") ItemProcessor<Person, Person> processor, 
			@Qualifier("jdbcUserWriter") ItemWriter<Person> writer) {
		return stepBuilderFactory.get("step").<Person, Person>chunk(10).reader(reader).processor(processor)
				.writer(writer).build();
	}

	@Bean(name = "csvReader")
	public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>().name("personItemReader")
				.resource(new ClassPathResource("input-data.csv")).delimited()
				.names(new String[] { "firstName", "lastName" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				}).build();
	}

	@Bean(name = "jdbcUserWriter")
	public JdbcBatchItemWriter<Person> writer() {
		return new JdbcBatchItemWriterBuilder<Person>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)").dataSource(dataSource)
				.build();
	}

}
