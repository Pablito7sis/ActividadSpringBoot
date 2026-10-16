package com.tienda.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para toda la API REST.
 *
 * <p>Utiliza {@code @RestControllerAdvice} para interceptar excepciones lanzadas
 * desde cualquier controlador y devolver respuestas HTTP con el código de estado
 * y cuerpo JSON adecuados.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    /**
     * Maneja excepciones de tipo {@link RecursoNoEncontradoException}.
     *
     * @param ex la excepción capturada
     * @return respuesta HTTP 404 Not Found con el mensaje de error en formato JSON
     */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepciones de tipo {@link IllegalArgumentException}.
     *
     * @param ex la excepción capturada
     * @return respuesta HTTP 400 Bad Request con el mensaje de error en formato JSON
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> manejarArgumentoIlegal(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones de validación de Bean Validation ({@code @Valid}).
     *
     * <p>Recopila todos los errores de validación de los campos y los retorna
     * en un mapa con el nombre del campo como clave y el mensaje de error como valor.</p>
     *
     * @param ex la excepción de validación capturada
     * @return respuesta HTTP 400 Bad Request con los errores de validación en formato JSON
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
