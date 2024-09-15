package com.file.processor.manager.consumer;

import com.file.processor.manager.dto.QueueFileMetadata;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class AwsSqsConsumer {

    @SqsListener("process-file-queue")
    public void consume(QueueFileMetadata queueFileMetadata) {
        System.out.println("Message Received: \n" + queueFileMetadata.content());
    }
}
