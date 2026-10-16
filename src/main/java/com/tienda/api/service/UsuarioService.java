package com.tienda.api.service;

import com.tienda.api.exception.RecursoNoEncontradoException;
import com.tienda.api.model.Usuario;
import com.tienda.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de lógica de negocio para la gestión de usuarios.
 *
 * <p>Encapsula las operaciones CRUD sobre la entidad {@link Usuario},
 * delegando la persistencia al {@link UsuarioRepository}.</p>
 *
 * <p>Implementa reglas de negocio como la unicidad del correo electrónico.
 * Utiliza {@code @Slf4j} para el registro de eventos y
 * {@code @RequiredArgsConstructor} para la inyección de dependencias por constructor.</p>
 *
 * @author Tienda API
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Obtiene la lista completa de usuarios registrados.
     *
     * @return lista de todos los {@link Usuario} en la base de datos
     */
    public List<Usuario> listarTodos() {
        log.info("Listando todos los usuarios");
        return usuarioRepository.findAll();
    }

    /**
     * Busca un usuario por su identificador único.
     *
     * @param id el identificador del usuario a buscar
     * @return el {@link Usuario} encontrado
     * @throws RecursoNoEncontradoException si no existe un usuario con el ID proporcionado
     */
    public Usuario buscarPorId(Long id) {
        log.info("Buscando usuario con ID: {}", id);
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Usuario no encontrado con ID: " + id));
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * <p>Antes de crear el usuario, valida que el correo electrónico
     * no esté ya registrado en la base de datos.</p>
     *
     * @param usuario el {@link Usuario} a crear
     * @return el {@link Usuario} creado con su ID asignado
     * @throws IllegalArgumentException si el correo electrónico ya está registrado
     */
    public Usuario crear(Usuario usuario) {
        log.info("Creando nuevo usuario con correo: {}", usuario.getCorreo());

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException(
                    "Ya existe un usuario registrado con el correo: " + usuario.getCorreo());
        }

        return usuarioRepository.save(usuario);
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * <p>Busca el usuario por ID, valida que el nuevo correo electrónico
     * no pertenezca a otro usuario, y actualiza todos los campos.</p>
     *
     * @param id                 el identificador del usuario a actualizar
     * @param usuarioActualizado el objeto con los nuevos datos del usuario
     * @return el {@link Usuario} actualizado y persistido
     * @throws RecursoNoEncontradoException si no existe un usuario con el ID proporcionado
     * @throws IllegalArgumentException     si el nuevo correo ya está registrado por otro usuario
     */
    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        log.info("Actualizando usuario con ID: {}", id);
        Usuario usuarioExistente = buscarPorId(id);

        // Validar unicidad del correo si ha cambiado
        if (!usuarioExistente.getCorreo().equals(usuarioActualizado.getCorreo())
                && usuarioRepository.existsByCorreo(usuarioActualizado.getCorreo())) {
            throw new IllegalArgumentException(
                    "Ya existe un usuario registrado con el correo: " + usuarioActualizado.getCorreo());
        }

        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        usuarioExistente.setContrasena(usuarioActualizado.getContrasena());
        usuarioExistente.setRol(usuarioActualizado.getRol());

        return usuarioRepository.save(usuarioExistente);
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param id el identificador del usuario a eliminar
     * @throws RecursoNoEncontradoException si no existe un usuario con el ID proporcionado
     */
    public void eliminar(Long id) {
        log.info("Eliminando usuario con ID: {}", id);
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
        log.info("Usuario con ID: {} eliminado exitosamente", id);
    }
}
