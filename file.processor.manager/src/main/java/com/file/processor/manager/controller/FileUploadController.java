package com.file.processor.manager.controller;

import com.file.processor.manager.dto.FileMetadata;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody FileMetadata fileMetadata) {

        System.out.println(fileMetadata);

        return ResponseEntity.ok("File uploaded successfully.");
    }
}