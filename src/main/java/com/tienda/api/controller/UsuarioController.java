package com.tienda.api.controller;

import com.tienda.api.model.Usuario;
import com.tienda.api.service.UsuarioService;
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
 * Controlador REST para la gestión de usuarios.
 *
 * <p>Expone los endpoints CRUD bajo la ruta base {@code /api/usuarios}
 * y delega la lógica de negocio al {@link UsuarioService}.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Obtiene la lista completa de usuarios.
     *
     * <p>La contraseña de cada usuario no será incluida en la respuesta
     * gracias a la configuración {@code WRITE_ONLY} en el modelo.</p>
     *
     * @return {@code 200 OK} con la lista de usuarios en formato JSON
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    /**
     * Obtiene un usuario específico por su ID.
     *
     * @param id el identificador del usuario a consultar
     * @return {@code 200 OK} con el usuario encontrado (sin contraseña),
     *         o {@code 404 Not Found} si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    /**
     * Crea un nuevo usuario.
     *
     * <p>Valida que el correo electrónico no esté ya registrado.
     * Si el correo ya existe, retorna {@code 400 Bad Request}.</p>
     *
     * @param usuario el objeto {@link Usuario} recibido en el cuerpo de la petición
     * @return {@code 201 Created} con el usuario creado (sin contraseña en la respuesta)
     */
    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioCreado = usuarioService.crear(usuario);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

    /**
     * Actualiza un usuario existente.
     *
     * <p>Si el correo electrónico cambia, valida que el nuevo correo
     * no pertenezca a otro usuario.</p>
     *
     * @param id      el identificador del usuario a actualizar
     * @param usuario el objeto {@link Usuario} con los datos actualizados
     * @return {@code 200 OK} con el usuario actualizado (sin contraseña),
     *         o {@code 404 Not Found} si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id,
                                              @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizar(id, usuario));
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id el identificador del usuario a eliminar
     * @return {@code 204 No Content} si se eliminó correctamente,
     *         o {@code 404 Not Found} si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
