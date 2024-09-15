package com.file.processor.manager.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.file.processor.manager.dto.QueueFileMetadata;
import com.file.processor.manager.service.AwsDynamoDbService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AwsSqsConsumer {

    AwsDynamoDbService awsDynamoDbService;

    @Autowired
    public AwsSqsConsumer(AwsDynamoDbService awsDynamoDbService) {
        this.awsDynamoDbService = awsDynamoDbService;
    }

    @SqsListener("process-file-queue")
    public void consume(QueueFileMetadata queueFileMetadata) throws JsonProcessingException {
        System.out.println("Message Received: \n" + queueFileMetadata.content());

        if (queueFileMetadata.content().equals("[]")) {
            return;
        }

        awsDynamoDbService.save(queueFileMetadata);
    }
}
