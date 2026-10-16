package com.tienda.api.service;

import com.tienda.api.exception.RecursoNoEncontradoException;
import com.tienda.api.model.Producto;
import com.tienda.api.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de lógica de negocio para la gestión de productos.
 *
 * <p>Encapsula las operaciones CRUD sobre la entidad {@link Producto},
 * delegando la persistencia al {@link ProductoRepository}.</p>
 *
 * <p>Utiliza {@code @Slf4j} para el registro de eventos y
 * {@code @RequiredArgsConstructor} para la inyección de dependencias por constructor.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Obtiene la lista completa de productos registrados.
     *
     * @return lista de todos los {@link Producto} en la base de datos
     */
    public List<Producto> listarTodos() {
        log.info("Listando todos los productos");
        return productoRepository.findAll();
    }

    /**
     * Busca un producto por su identificador único.
     *
     * @param id el identificador del producto a buscar
     * @return el {@link Producto} encontrado
     * @throws RecursoNoEncontradoException si no existe un producto con el ID proporcionado
     */
    public Producto buscarPorId(Long id) {
        log.info("Buscando producto con ID: {}", id);
        return productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Producto no encontrado con ID: " + id));
    }

    /**
     * Registra un nuevo producto en la base de datos.
     *
     * @param producto el {@link Producto} a crear
     * @return el {@link Producto} creado con su ID asignado
     */
    public Producto crear(Producto producto) {
        log.info("Creando nuevo producto: {}", producto.getNombre());
        return productoRepository.save(producto);
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * <p>Busca el producto por ID y actualiza todos sus campos con los valores
     * proporcionados en el objeto {@code productoActualizado}.</p>
     *
     * @param id                  el identificador del producto a actualizar
     * @param productoActualizado el objeto con los nuevos datos del producto
     * @return el {@link Producto} actualizado y persistido
     * @throws RecursoNoEncontradoException si no existe un producto con el ID proporcionado
     */
    public Producto actualizar(Long id, Producto productoActualizado) {
        log.info("Actualizando producto con ID: {}", id);
        Producto productoExistente = buscarPorId(id);

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());

        return productoRepository.save(productoExistente);
    }

    /**
     * Elimina un producto de la base de datos por su ID.
     *
     * @param id el identificador del producto a eliminar
     * @throws RecursoNoEncontradoException si no existe un producto con el ID proporcionado
     */
    public void eliminar(Long id) {
        log.info("Eliminando producto con ID: {}", id);
        Producto producto = buscarPorId(id);
        productoRepository.delete(producto);
        log.info("Producto con ID: {} eliminado exitosamente", id);
    }
}
