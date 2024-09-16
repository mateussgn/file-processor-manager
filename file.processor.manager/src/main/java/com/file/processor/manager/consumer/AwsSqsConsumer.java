package com.file.processor.manager.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.file.processor.manager.dto.QueueFileMetadata;
import com.file.processor.manager.file.processor.CountLinesFileProcessor;
import com.file.processor.manager.file.processor.FileProcessor;
import com.file.processor.manager.model.FileMetadataModel;
import com.file.processor.manager.service.AwsDynamoDbService;
import com.file.processor.manager.service.AwsSnsService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AwsSqsConsumer {

    AwsDynamoDbService awsDynamoDbService;
    AwsSnsService awsSnsService;

    @Autowired
    public AwsSqsConsumer(AwsDynamoDbService awsDynamoDbService, AwsSnsService awsSnsService) {
        this.awsDynamoDbService = awsDynamoDbService;
        this.awsSnsService = awsSnsService;
    }

    @SqsListener("file-processor-manager")
    public void consume(QueueFileMetadata queueFileMetadata) throws JsonProcessingException {
        if (queueFileMetadata.content().equals("[]")) {
            return;
        }
        System.out.println("Message consumed.");
        System.out.println("Start file metadata processing.");

        FileProcessor<FileMetadataModel, String> fileProcessor = new CountLinesFileProcessor();
        FileMetadataModel fileMetadataModel = fileProcessor.process(queueFileMetadata.content());
        System.out.println("File metadata processed successfully.");

        awsDynamoDbService.save(fileMetadataModel);
        System.out.println("Start file processing.");

        awsSnsService.sendNotification(fileMetadataModel);
        System.out.println("SNS Notification sent successfully.");

    }
}
