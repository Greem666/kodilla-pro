package com.kodillapro.ex1_3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.LoggingHandler;

import java.io.File;

@Configuration
public class FileIntegrationConfiguration {

    @Bean
    IntegrationFlow fileIntegrationFlow(
            FileReadingMessageSource fileAdapter,
            FileTransformer transformer,
            FileWritingMessageHandler outputFileHandler) {
        return IntegrationFlows.from(fileAdapter, config -> config.poller(Pollers.fixedDelay(1000)))
                .log(LoggingHandler.Level.INFO)
                .transform(transformer, "returnFileName")
                .log(LoggingHandler.Level.INFO)
                .handle(outputFileHandler)
                .get();
    }

    @Bean
    FileReadingMessageSource fileAdapter() {
        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File("ex1_3/data/input"));

        return fileSource;
    }

    @Bean
    FileTransformer transformer() {
        return new FileTransformer();
    }

    @Bean
    FileWritingMessageHandler outputFileAdapter() {
        File directory = new File("ex1_3/data/output");
        FileWritingMessageHandler handler = new FileWritingMessageHandler(directory);
        handler.setExpectReply(false);
        handler.setAppendNewLine(true);
        handler.setFileNameGenerator(name -> "input_file_names.txt");
        handler.setFileExistsMode(FileExistsMode.APPEND);

        return handler;
    }
}
