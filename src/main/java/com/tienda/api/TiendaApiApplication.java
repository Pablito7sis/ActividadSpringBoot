package com.tienda.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot para la API REST de la Tienda.
 *
 * <p>Punto de entrada que inicializa el contexto de Spring y levanta
 * el servidor embebido en el puerto configurado.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
@SpringBootApplication
public class TiendaApiApplication {

    /**
     * Método principal que arranca la aplicación Spring Boot.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(TiendaApiApplication.class, args);
    }
}
