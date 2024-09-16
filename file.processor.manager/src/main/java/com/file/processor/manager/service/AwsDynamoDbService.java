package com.file.processor.manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.processor.manager.dto.FileMetadata;
import com.file.processor.manager.dto.QueueFileMetadata;
import com.file.processor.manager.model.FileMetadataModel;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwsDynamoDbService {

    private DynamoDbTemplate dynamoDbTemplate;

    @Autowired
    public AwsDynamoDbService(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    public void save(FileMetadataModel fileMetadataModel) throws JsonProcessingException {
        dynamoDbTemplate.save(fileMetadataModel);
    }
}
