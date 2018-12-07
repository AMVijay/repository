package vijay.poc.java.spring.batch.simplebatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
    private JobBuilderFactory jobBuilderFactory;
    
	@Bean(name="importUserJob")
	public Job importUsers(@Qualifier("importUserStep") Step step1) {
		return jobBuilderFactory.get("importUserJob")
	            .incrementer(new RunIdIncrementer())
	            .flow(step1)
	            .end()
	            .build();
	};
	
	@Bean(name="modifyUserJob")
	public Job modifyUsers(@Qualifier("modifyUserStep") Step Step1) {
		return jobBuilderFactory.get("modifyUserJob")
				.incrementer(new RunIdIncrementer())
				.flow(Step1)
				.end()
				.build();
	}
}
