package com.tienda.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad JPA que representa un producto en la tienda.
 *
 * <p>Mapea la tabla {@code productos} en la base de datos y contiene
 * la información básica de cada producto: nombre, descripción, precio y stock.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
@Entity
@Table(name = "productos")
public class Producto {

    /**
     * Identificador único del producto (clave primaria auto-incrementable).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del producto.
     * No puede ser nulo ni estar vacío. Máximo 100 caracteres.
     */
    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * Descripción detallada del producto.
     */
    private String descripcion;

    /**
     * Precio unitario del producto.
     * No puede ser nulo.
     */
    @NotNull(message = "El precio del producto es obligatorio")
    @Column(nullable = false)
    private Double precio;

    /**
     * Cantidad disponible en inventario.
     */
    private Integer stock;

    public Producto() {
    }

    public Producto(Long id, String nombre, String descripcion, Double precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
