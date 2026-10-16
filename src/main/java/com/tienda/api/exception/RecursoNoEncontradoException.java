package com.tienda.api.exception;

/**
 * Excepción personalizada que se lanza cuando un recurso solicitado
 * no se encuentra en la base de datos.
 *
 * <p>Es capturada por {@link ManejadorGlobalExcepciones} y se traduce
 * en una respuesta HTTP 404 Not Found.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
public class RecursoNoEncontradoException extends RuntimeException {

    /**
     * Construye una nueva excepción con el mensaje especificado.
     *
     * @param mensaje descripción del recurso no encontrado
     */
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
