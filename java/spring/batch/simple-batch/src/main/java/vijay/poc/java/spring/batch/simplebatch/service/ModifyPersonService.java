package vijay.poc.java.spring.batch.simplebatch.service;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import vijay.poc.java.spring.batch.simplebatch.dto.Person;

@Service
public class ModifyPersonService {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean(name = "modifyUserStep")
	public Step step(@Qualifier("csvReader") ItemReader<Person> reader,
			@Qualifier("PersonItemProcessor") ItemProcessor<Person, Person> processor,
			@Qualifier("cvsWriter") ItemWriter<Person> writer) {
		return stepBuilderFactory.get("step").<Person, Person>chunk(10).reader(reader).processor(processor)
				.writer(writer).build();
	}

	@Bean("cvsWriter")
	public FlatFileItemWriter<Person> writer1() {
		System.out.println("In Writer");
		FlatFileItemWriter<Person> writer = new FlatFileItemWriter<Person>();
		writer.setResource(new FileSystemResource("src/main/resources/output-data.csv"));
		DelimitedLineAggregator<Person> delLineAgg = new DelimitedLineAggregator<Person>();
		delLineAgg.setDelimiter(",");
		BeanWrapperFieldExtractor<Person> fieldExtractor = new BeanWrapperFieldExtractor<Person>();
		fieldExtractor.setNames(new String[] { "firstName", "lastName" });
		delLineAgg.setFieldExtractor(fieldExtractor);
		writer.setLineAggregator(delLineAgg);
		return writer;
	}

}
