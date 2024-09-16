package com.file.processor.manager.aws.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import java.net.URI;

@Configuration
public class AwsSqsConfiguration {

    @Value("${spring.cloud.aws.sqs.endpoint}")
    private static String sqsEndpoint;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(sqsEndpoint))
                .region(Region.US_EAST_1)
                .build();
    }
}
