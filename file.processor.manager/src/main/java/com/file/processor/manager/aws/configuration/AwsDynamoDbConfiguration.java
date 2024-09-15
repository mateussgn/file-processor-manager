package com.file.processor.manager.aws.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class AwsDynamoDbConfiguration {

    private static final String DYNAMO_DB_URI = "http://localhost:4566";

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .endpointOverride(URI.create(DYNAMO_DB_URI))
                .region(Region.US_EAST_1)
                .build();
    }
}