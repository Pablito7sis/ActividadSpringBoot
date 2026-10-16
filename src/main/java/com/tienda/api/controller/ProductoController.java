package com.tienda.api.controller;

import com.tienda.api.model.Producto;
import com.tienda.api.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para la gestión de productos.
 *
 * <p>Expone los endpoints CRUD bajo la ruta base {@code /api/productos}
 * y delega la lógica de negocio al {@link ProductoService}.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Obtiene la lista completa de productos.
     *
     * @return {@code 200 OK} con la lista de productos en formato JSON
     */
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    /**
     * Obtiene un producto específico por su ID.
     *
     * @param id el identificador del producto a consultar
     * @return {@code 200 OK} con el producto encontrado, o {@code 404 Not Found} si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    /**
     * Crea un nuevo producto.
     *
     * @param producto el objeto {@link Producto} recibido en el cuerpo de la petición
     * @return {@code 201 Created} con el producto creado y su ID asignado
     */
    @PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto) {
        Producto productoCreado = productoService.crear(producto);
        return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id       el identificador del producto a actualizar
     * @param producto el objeto {@link Producto} con los datos actualizados
     * @return {@code 200 OK} con el producto actualizado, o {@code 404 Not Found} si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id,
                                               @Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id el identificador del producto a eliminar
     * @return {@code 204 No Content} si se eliminó correctamente, o {@code 404 Not Found} si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
