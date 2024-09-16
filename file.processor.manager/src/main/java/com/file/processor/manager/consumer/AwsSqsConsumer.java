package com.file.processor.manager.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.file.processor.manager.dto.QueueFileMetadata;
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

    @SqsListener("process-file-queue")
    public void consume(QueueFileMetadata queueFileMetadata) throws JsonProcessingException {
        System.out.println("Message Received: \n" + queueFileMetadata.content());

        if (queueFileMetadata.content().equals("[]")) {
            return;
        }

        awsDynamoDbService.save(queueFileMetadata);

        awsSnsService.sendNotification(queueFileMetadata);
    }
}
