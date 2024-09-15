package com.file.processor.manager.controller;

import com.file.processor.manager.dto.FileMetadata;
import com.file.processor.manager.service.AwsSqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final AwsSqsService awsSqsService;

    @Autowired
    public FileUploadController(AwsSqsService awsSqsService) {
        this.awsSqsService = awsSqsService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody FileMetadata fileMetadata) throws Exception {

        awsSqsService.publishFileMetadata(fileMetadata);

        return ResponseEntity.ok("File uploaded successfully.");
    }
}