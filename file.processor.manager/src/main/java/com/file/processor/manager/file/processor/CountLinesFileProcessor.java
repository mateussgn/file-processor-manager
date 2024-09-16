package com.file.processor.manager.file.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.processor.manager.dto.FileMetadata;
import com.file.processor.manager.model.FileMetadataModel;

public class CountLinesFileProcessor implements FileProcessor<FileMetadataModel, String> {

    private static final String LINE_BREAK = "\n";

    @Override
    public FileMetadataModel process(String file) throws JsonProcessingException {

        return toFileMetadataModel(file, countFileLines(file));
    }

    private Integer countFileLines(String file) {
        String[] lines = file.split(LINE_BREAK);
        return lines.length;
    }

    private FileMetadataModel toFileMetadataModel(String content, Integer numberOfLines) throws JsonProcessingException {
        FileMetadata fileMetadata = toFileMetadata(content.substring(1, content.length() - 1));

        return new FileMetadataModel(
                fileMetadata.filename(),
                numberOfLines,
                fileMetadata.fileSize(),
                fileMetadata.fileType(),
                fileMetadata.userId(),
                fileMetadata.email());
    }

    private FileMetadata toFileMetadata(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, FileMetadata.class);
    }
}
