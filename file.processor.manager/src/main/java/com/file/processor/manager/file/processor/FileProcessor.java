package com.file.processor.manager.file.processor;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface FileProcessor<T, G> {

    public T process(G file) throws JsonProcessingException;
}
