package com.file.processor.manager.producer;

import com.file.processor.manager.dto.QueueFileMetadata;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AwsSqsProducer implements CommandLineRunner {

    private static final String SQS_QUEUE_URL = "https://localhost.localstack.cloud:4566/000000000000/process-file-queue";

    @Autowired
    private SqsTemplate sqsTemplate;

    @Override
    public void run(String... args) throws Exception {
        sqsTemplate.send(
                SQS_QUEUE_URL,
                new QueueFileMetadata(Arrays.toString(args)));
    }
}
