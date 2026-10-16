package com.tienda.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad JPA que representa un usuario del sistema.
 *
 * <p>Mapea la tabla {@code usuarios} en la base de datos y contiene
 * la información del usuario: nombre, correo electrónico, contraseña y rol.</p>
 *
 * <p>La contraseña se configura como {@code WRITE_ONLY} para que nunca
 * sea incluida en las respuestas JSON de la API.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    /**
     * Identificador único del usuario (clave primaria auto-incrementable).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre completo del usuario.
     * No puede ser nulo ni estar vacío. Máximo 80 caracteres.
     */
    @NotBlank(message = "El nombre del usuario es obligatorio")
    @Size(max = 80, message = "El nombre no puede exceder los 80 caracteres")
    @Column(nullable = false, length = 80)
    private String nombre;

    /**
     * Correo electrónico del usuario. Debe ser único en el sistema.
     * No puede ser nulo ni estar vacío. Máximo 120 caracteres.
     */
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido")
    @Size(max = 120, message = "El correo no puede exceder los 120 caracteres")
    @Column(nullable = false, unique = true, length = 120)
    private String correo;

    /**
     * Contraseña del usuario.
     * No puede ser nula ni estar vacía.
     * Configurada como {@code WRITE_ONLY} para que nunca se retorne en respuestas JSON.
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String contrasena;

    /**
     * Rol asignado al usuario (ej. ADMIN, USER).
     */
    private String rol;
}
