package com.file.processor.manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.processor.manager.dto.FileMetadata;
import com.file.processor.manager.producer.AwsSqsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwsSqsService {

    AwsSqsProducer awsSqsProducer;

    @Autowired
    public AwsSqsService(AwsSqsProducer awsSqsProducer) {
        this.awsSqsProducer = awsSqsProducer;
    }

    public void publishFileMetadata(FileMetadata fileMetadata) throws Exception {
        awsSqsProducer.run(toJsonString(fileMetadata));
    }

    private String toJsonString(FileMetadata fileMetadata) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(fileMetadata);
    }
}
