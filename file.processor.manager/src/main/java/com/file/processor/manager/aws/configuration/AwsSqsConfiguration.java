package com.file.processor.manager.aws.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import java.net.URI;

@Configuration
public class AwsSqsConfiguration {


    private static final String SQS_URI = "https://sqs.us-east-1.amazonaws.com/471112682449/file-processor-manager";

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(SQS_URI))
                .region(Region.US_EAST_1)
                .build();
    }
}
