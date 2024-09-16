package com.file.processor.manager.producer;

import com.file.processor.manager.dto.QueueFileMetadata;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class AwsSqsProducer implements CommandLineRunner {
    
    @Value("${spring.cloud.aws.sqs.endpoint}")
    private static String sqsEndpoint;

    @Autowired
    private SqsTemplate sqsTemplate;

    @Override
    public void run(String... args) throws Exception {
        sqsTemplate.send(
                sqsEndpoint,
                new QueueFileMetadata(Arrays.toString(args)));
    }
}
