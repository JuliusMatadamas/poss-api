package com.poss.utils;

import com.poss.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

public final class ApiResponseUtil {

    private ApiResponseUtil() {
        // Clase de utilidad, no instanciable
    }

    /**
     * Construye una respuesta exitosa con estado HTTP 200 OK.
     * @param data El cuerpo de la respuesta (payload).
     * @return ResponseEntity con la respuesta estandarizada.
     */
    public static <T> ResponseEntity<ApiResponseDTO> buildSuccessResponse(T data) {
        Meta meta = new Meta(UUID.randomUUID().toString(), "OK", HttpStatus.OK.value());
        ApiResponseDTO responseBody = new ApiResponseDTO(meta, data);
        return ResponseEntity.ok(responseBody);
    }

    /**
     * Construye una respuesta para un recurso creado con estado HTTP 201 Created.
     * @param data El cuerpo de la respuesta (payload).
     * @return ResponseEntity con la respuesta estandarizada.
     */
    public static <T> ResponseEntity<ApiResponseDTO> buildCreatedResponse(T data) {
        Meta meta = new Meta(UUID.randomUUID().toString(), "Created", HttpStatus.CREATED.value());
        ApiResponseDTO responseBody = new ApiResponseDTO(meta, data);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    /**
     * Construye una respuesta vacía con estado HTTP 204 No Content.
     * Ideal para operaciones exitosas que no necesitan devolver un cuerpo (ej. DELETE).
     * @return ResponseEntity sin cuerpo y con estado 204.
     */
    public static ResponseEntity<Void> buildNoContentResponse() {
        return ResponseEntity.noContent().build();
    }
}
