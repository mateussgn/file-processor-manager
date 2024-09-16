package com.file.processor.manager.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import java.util.UUID;

@DynamoDbBean
public class FileMetadataModel {
    private UUID fileId;
    private String fileName;
    private long fileNumberOfLines;
    private long fileSize;
    private String fileType;
    private String userId;
    private String email;

    public FileMetadataModel() {}

    public FileMetadataModel(
            String filename,
            long fileNumberOfLines,
            long fileSize,
            String fileType,
            String userId,
            String email) {
        this.fileId = UUID.randomUUID();
        this.fileName = filename;
        this.fileNumberOfLines = fileNumberOfLines;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.userId = userId;
        this.email = email;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("file_id")
    public UUID getFileId() {
        return fileId;
    }
    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }

    @DynamoDbAttribute("filename")
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @DynamoDbAttribute("file_number_of_lines")
    public long getFileNumberOfLines() {
        return fileNumberOfLines;
    }
    public void setFileNumberOfLines(long fileNumberOfLines) {
        this.fileNumberOfLines = fileNumberOfLines;
    }

    @DynamoDbAttribute("file_size")
    public long getFileSize() {
        return fileSize;
    }
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @DynamoDbAttribute("file_type")
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @DynamoDbAttribute("user_id")
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
