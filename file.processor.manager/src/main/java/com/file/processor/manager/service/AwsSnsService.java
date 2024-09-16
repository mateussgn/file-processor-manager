package com.file.processor.manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.file.processor.manager.model.FileMetadataModel;
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

    public void sendNotification(FileMetadataModel fileMetadataModel) throws JsonProcessingException {
        SnsNotification<FileMetadataModel> snsNotification = SnsNotification.builder(fileMetadataModel).build();

        snsTemplate.sendNotification(SNS_TOPIC_ARN, snsNotification);
    }
}
