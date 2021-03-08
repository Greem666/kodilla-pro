package com.kodillapro.ex1_2;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    FlatFileItemReader<PersonDate> reader() {
        FlatFileItemReader<PersonDate> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("MOCK_DATA_NO_HEADERS.csv"));

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("name", "surname", "dob");
        tokenizer.setDelimiter(",");

        BeanWrapperFieldSetMapper<PersonDate> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(PersonDate.class);

        DefaultLineMapper<PersonDate> lineMapper = new DefaultLineMapper<>();
        lineMapper.setFieldSetMapper(mapper);
        lineMapper.setLineTokenizer(tokenizer);

        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    PersonProcessor processor() {
        return new PersonProcessor();
    }

    @Bean
    FlatFileItemWriter<PersonAge> writer() {
        BeanWrapperFieldExtractor<PersonAge> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"name", "surname", "age"});

        DelimitedLineAggregator<PersonAge> aggregator = new DelimitedLineAggregator<PersonAge>();
        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(extractor);

        FlatFileItemWriter<PersonAge> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("output.csv"));
        writer.setShouldDeleteIfExists(true);
        writer.setLineAggregator(aggregator);

        return writer;
    }

    @Bean
    Step calculateAgeStep(
        ItemReader<PersonDate> reader,
        ItemProcessor<PersonDate, PersonAge> processor,
        ItemWriter<PersonAge> writer) {

        return stepBuilderFactory
                .get("calculateAgeStep")
                .<PersonDate, PersonAge> chunk(50)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    Job calculateAgeJob(Step calculateAgeStep) {
        return jobBuilderFactory.get("calculateAgeJob")
                .incrementer(new RunIdIncrementer())
                .flow(calculateAgeStep)
                .end()
                .build();
    }
}
