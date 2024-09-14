package com.file.processor.manager.dto;

public record FileMetadata(
        String filename,
        long fileSize,
        String fileType,
        String userId,
        String email
) {
}
