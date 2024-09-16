package com.file.processor.manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.file.processor.manager.model.FileMetadataModel;
import io.awspring.cloud.sns.core.SnsNotification;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

    SnsTemplate snsTemplate;

    @Value("${spring.cloud.aws.sns.arn-topic}")
    private static String arnTopic;

    @Autowired
    public AwsSnsService(SnsTemplate snsTemplate) {
        this.snsTemplate = snsTemplate;
    }

    public void sendNotification(FileMetadataModel fileMetadataModel) {
        SnsNotification<FileMetadataModel> snsNotification = SnsNotification.builder(fileMetadataModel).build();

        snsTemplate.sendNotification(arnTopic, snsNotification);
    }
}
