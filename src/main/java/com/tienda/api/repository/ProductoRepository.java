package com.tienda.api.repository;

import com.tienda.api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link Producto}.
 *
 * <p>Proporciona operaciones CRUD estándar y consultas derivadas
 * sobre la tabla de productos mediante Spring Data JPA.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
