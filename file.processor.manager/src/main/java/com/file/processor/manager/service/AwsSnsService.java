package com.file.processor.manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.processor.manager.dto.FileMetadata;
import com.file.processor.manager.dto.QueueFileMetadata;
import io.awspring.cloud.sns.core.SnsNotification;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

    SnsTemplate snsTemplate;
    private static final String SNS_TOPIC_ARN = "arn:aws:sns:us-east-1:471112682449:file-processor-manager";

    @Autowired
    public AwsSnsService(SnsTemplate snsTemplate) {
        this.snsTemplate = snsTemplate;
    }

    public void sendNotification(QueueFileMetadata queueFileMetadata) throws JsonProcessingException {
        String content = queueFileMetadata.content();
        FileMetadata fileMetadata = toFileMetadata(content.substring(1, content.length() - 1));
        SnsNotification<FileMetadata> snsNotification = SnsNotification.builder(fileMetadata).build();

        snsTemplate.sendNotification(SNS_TOPIC_ARN, snsNotification);
    }

    private FileMetadata toFileMetadata(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, FileMetadata.class);
    }
}
