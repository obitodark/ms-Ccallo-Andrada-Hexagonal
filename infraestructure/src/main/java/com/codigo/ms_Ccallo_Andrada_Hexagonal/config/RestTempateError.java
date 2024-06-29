package com.codigo.ms_Ccallo_Andrada_Hexagonal.config;

import com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.personalize.NotContentException;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.personalize.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTempateError implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        return (statusCode.is4xxClientError() || statusCode.is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.NOT_FOUND) {
            throw new NotFoundException("Resource not found");
        } else if (statusCode == HttpStatus.NO_CONTENT) {
            throw new NotContentException("No content available");
        } else {
            throw new RuntimeException("An error occurred: " + statusCode);
        }
    }
}
