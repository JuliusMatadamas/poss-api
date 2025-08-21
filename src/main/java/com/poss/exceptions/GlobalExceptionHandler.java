package com.poss.exceptions;

import com.poss.dto.ApiResponseDTO;
import com.poss.utils.Meta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<ApiResponseDTO> handleApiException(ApiException ex, WebRequest request) {
        Meta meta = Meta.builder()
                .transactionID(UUID.randomUUID().toString())
                .status(ex.getStatus().getReasonPhrase())
                .statusCode(ex.getStatus().value())
                .timestamp(LocalDateTime.now().toString())
                .devMessage(ex.getMessage())
                .message(ex.getStatus().getReasonPhrase())
                .build();

        ApiResponseDTO apiResponse = ApiResponseDTO.builder()
                .meta(meta)
                .data(null)
                .build();

        return new ResponseEntity<>(apiResponse, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponseDTO> handleAllExceptions(Exception ex, WebRequest request) {
        Meta meta = Meta.builder()
                .transactionID(UUID.randomUUID().toString())
                .status("INTERNAL_SERVER_ERROR")
                .statusCode(500)
                .timestamp(LocalDateTime.now().toString())
                .devMessage(ex.getMessage())
                .message("Ocurrió un error inesperado en el servidor.")
                .build();

        ApiResponseDTO apiResponse = ApiResponseDTO.builder()
                .meta(meta)
                .data(null)
                .build();

        return new ResponseEntity<>(apiResponse, org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

