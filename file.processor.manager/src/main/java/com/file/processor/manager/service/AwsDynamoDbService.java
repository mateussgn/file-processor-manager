package com.file.processor.manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.processor.manager.dto.FileMetadata;
import com.file.processor.manager.dto.QueueFileMetadata;
import com.file.processor.manager.model.FileMetadataModel;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AwsDynamoDbService {

    private DynamoDbTemplate dynamoDbTemplate;

    @Autowired
    public AwsDynamoDbService(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    public void save(QueueFileMetadata queueFileMetadata) throws JsonProcessingException {
        FileMetadataModel fileMetadataModel = toFileMetadataModel(queueFileMetadata.content());
        dynamoDbTemplate.save(fileMetadataModel);
    }

    public FileMetadataModel toFileMetadataModel(String content) throws JsonProcessingException {
        FileMetadata fileMetadata = toFileMetadata(content.substring(1, content.length() - 1));

        return new FileMetadataModel(
                fileMetadata.filename(),
                fileMetadata.fileSize(),
                fileMetadata.fileType(),
                fileMetadata.userId(),
                fileMetadata.email()
        );
    }

    private FileMetadata toFileMetadata(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, FileMetadata.class);
    }
}
