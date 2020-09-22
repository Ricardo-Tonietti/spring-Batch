package com.silverfox.newcarshop.batchapi.configuration.chuncklet;

import com.silverfox.newcarshop.batchapi.converter.CarroConverter;
import com.silverfox.newcarshop.batchapi.dto.CarroDto;
import com.silverfox.newcarshop.batchapi.model.Carro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

public class CarroItemProcessor implements ItemProcessor<CarroDto, Carro>, StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarroItemProcessor.class);


    @Override
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.info("Iniciando o PROCESSOR ...");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        LOGGER.info("Finalizando o PROCESSOR ...");
        return ExitStatus.COMPLETED;
    }

    @Override
    public Carro process(CarroDto carroDto)  {
       return CarroConverter.getCarro(carroDto);
    }
}
