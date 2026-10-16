package com.tienda.api.repository;

import com.tienda.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Usuario}.
 *
 * <p>Proporciona operaciones CRUD estándar y consultas derivadas
 * sobre la tabla de usuarios mediante Spring Data JPA.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param correo el correo electrónico a buscar
     * @return un {@link Optional} con el usuario encontrado, o vacío si no existe
     */
    Optional<Usuario> findByCorreo(String correo);

    /**
     * Verifica si ya existe un usuario con el correo electrónico dado.
     *
     * @param correo el correo electrónico a verificar
     * @return {@code true} si el correo ya está registrado, {@code false} en caso contrario
     */
    boolean existsByCorreo(String correo);
}
