package com.silverfox.newcarshop.batchapi.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.api.chunk.ItemReader;
import javax.batch.api.chunk.ItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory
                .get("carrosJob")
                .start(carroValidateTaskletStep())
                .next(carroEnriquecimentoChunckletStep(carroItemReader(),
                        carroItemProcessor(),
                        carroItemWriter()))
                .build();
    }

    @Bean
    public Step carroValidateTaskletStep() {
        return stepBuilderFactory
                .get("carroValidateTaskletStep")
                .tasklet(new CarroValidateTasklet("carros-import"))
                .build();
    }

    @Bean
    public Step carroEnriquecimentoChunckletStep(ItemReader<CarroDto> carroDtoItemReader,
                                                 ItemProcessor<CarroDto, Carro> carroItemProcessor,
                                                 ItemWriter<Carro> carroItemWriter) {
            return stepBuilderFactory
                    .get("carroEnriquecimentoChunckletStep")
                    .<CarroDto, Carro>chunk(5)
                    .reader(carroDtoItemReader)
                    .processor(carroItemProcessor)
                    .writer(carroItemWriter)
                    .build();
    }

    @Bean
    public ItemReader<CarroDto> carroDtoItemReader(){
        return new CarroItemReader();
    }

    @Bean
    public ItemProcessor<CarroDto, Carro> carroDtoCarroItemProcessor(){
        return new CarroItemProcessor();
    }

    @Bean
    public ItemWriter<Carro> carroItemWriter(){
        return new CarroItemWriter();
    }




}
