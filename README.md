# Tienda API - REST con Spring Boot

API REST completa para la gestión de **Productos** y **Usuarios** de una tienda, desarrollada con **Spring Boot 3.x** y **Java 17**.

---

## Descripción

Este proyecto implementa una API RESTful con operaciones CRUD completas para dos módulos:

- **Productos:** Gestión del catálogo de productos (nombre, descripción, precio, stock).
- **Usuarios:** Gestión de usuarios del sistema (nombre, correo único, contraseña protegida, rol).

La API incluye manejo global de excepciones, validación de datos con Bean Validation y buenas prácticas de desarrollo con Lombok.

---

## Tecnologías y Versiones

| Tecnología         | Versión   |
|--------------------|-----------|
| Java               | 17+       |
| Spring Boot        | 3.2.5     |
| Spring Data JPA    | 3.2.x     |
| MySQL              | 8.x       |
| Apache Maven       | 3.8+      |
| Lombok             | 1.18.x    |
| Hibernate          | 6.x       |

---

## Configuración y Ejecución

### 1. Prerrequisitos

- **Java 17** o superior instalado.
- **Maven 3.8+** instalado.
- **MySQL 8.x** en ejecución en `localhost:3306`.

### 2. Crear la base de datos

Conéctate a MySQL y ejecuta:

```sql
CREATE DATABASE IF NOT EXISTS tienda_db;
```

### 3. Configurar credenciales

Edita el archivo `src/main/resources/application.properties` y ajusta las credenciales de MySQL si es necesario:

```properties
spring.datasource.username=root
spring.datasource.password=root
```

### 4. Compilar y ejecutar

```bash
# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

> **Nota:** Las tablas `productos` y `usuarios` se crearán automáticamente gracias a `spring.jpa.hibernate.ddl-auto=update`.

---

## Estructura del Proyecto

```
com.tienda.api
├── controller
│   ├── ProductoController.java
│   └── UsuarioController.java
├── service
│   ├── ProductoService.java
│   └── UsuarioService.java
├── repository
│   ├── ProductoRepository.java
│   └── UsuarioRepository.java
├── model
│   ├── Producto.java
│   └── Usuario.java
├── exception
│   ├── RecursoNoEncontradoException.java
│   └── ManejadorGlobalExcepciones.java
└── TiendaApiApplication.java
```

---

## Endpoints de la API

### Módulo de Productos (`/api/productos`)

| Método   | Endpoint               | Descripción                | Código de Respuesta         |
|----------|------------------------|----------------------------|-----------------------------|
| `GET`    | `/api/productos`       | Listar todos los productos | `200 OK`                    |
| `GET`    | `/api/productos/{id}`  | Obtener producto por ID    | `200 OK` / `404 Not Found`  |
| `POST`   | `/api/productos`       | Crear nuevo producto       | `201 Created`               |
| `PUT`    | `/api/productos/{id}`  | Actualizar producto        | `200 OK` / `404 Not Found`  |
| `DELETE` | `/api/productos/{id}`  | Eliminar producto          | `204 No Content` / `404 Not Found` |

### Módulo de Usuarios (`/api/usuarios`)

| Método   | Endpoint               | Descripción                | Código de Respuesta         |
|----------|------------------------|----------------------------|-----------------------------|
| `GET`    | `/api/usuarios`        | Listar todos los usuarios  | `200 OK`                    |
| `GET`    | `/api/usuarios/{id}`   | Obtener usuario por ID     | `200 OK` / `404 Not Found`  |
| `POST`   | `/api/usuarios`        | Crear nuevo usuario        | `201 Created` / `400 Bad Request` |
| `PUT`    | `/api/usuarios/{id}`   | Actualizar usuario         | `200 OK` / `404 Not Found`  |
| `DELETE` | `/api/usuarios/{id}`   | Eliminar usuario           | `204 No Content` / `404 Not Found` |

---

## Ejemplos de Peticiones y Respuestas

### Productos

#### `POST /api/productos` — Crear producto

**Petición:**

```json
{
    "nombre": "Laptop HP Pavilion",
    "descripcion": "Laptop 15.6 pulgadas, 16GB RAM, 512GB SSD",
    "precio": 899.99,
    "stock": 25
}
```

**Respuesta (201 Created):**

```json
{
    "id": 1,
    "nombre": "Laptop HP Pavilion",
    "descripcion": "Laptop 15.6 pulgadas, 16GB RAM, 512GB SSD",
    "precio": 899.99,
    "stock": 25
}
```

#### `GET /api/productos` — Listar todos

**Respuesta (200 OK):**

```json
[
    {
        "id": 1,
        "nombre": "Laptop HP Pavilion",
        "descripcion": "Laptop 15.6 pulgadas, 16GB RAM, 512GB SSD",
        "precio": 899.99,
        "stock": 25
    },
    {
        "id": 2,
        "nombre": "Mouse Logitech MX Master",
        "descripcion": "Mouse inalámbrico ergonómico",
        "precio": 79.99,
        "stock": 100
    }
]
```

#### `GET /api/productos/1` — Obtener por ID

**Respuesta (200 OK):**

```json
{
    "id": 1,
    "nombre": "Laptop HP Pavilion",
    "descripcion": "Laptop 15.6 pulgadas, 16GB RAM, 512GB SSD",
    "precio": 899.99,
    "stock": 25
}
```

#### `PUT /api/productos/1` — Actualizar producto

**Petición:**

```json
{
    "nombre": "Laptop HP Pavilion 15",
    "descripcion": "Laptop 15.6 pulgadas, 32GB RAM, 1TB SSD - Edición 2024",
    "precio": 1099.99,
    "stock": 15
}
```

**Respuesta (200 OK):**

```json
{
    "id": 1,
    "nombre": "Laptop HP Pavilion 15",
    "descripcion": "Laptop 15.6 pulgadas, 32GB RAM, 1TB SSD - Edición 2024",
    "precio": 1099.99,
    "stock": 15
}
```

#### `DELETE /api/productos/1` — Eliminar producto

**Respuesta:** `204 No Content` (sin cuerpo)

#### Producto no encontrado

**Respuesta (404 Not Found):**

```json
{
    "error": "Producto no encontrado con ID: 99"
}
```

---

### Usuarios

#### `POST /api/usuarios` — Crear usuario

**Petición:**

```json
{
    "nombre": "Juan Pérez",
    "correo": "juan.perez@email.com",
    "contrasena": "miPassword123",
    "rol": "USER"
}
```

**Respuesta (201 Created):**

> Nótese que la contraseña **no** se incluye en la respuesta.

```json
{
    "id": 1,
    "nombre": "Juan Pérez",
    "correo": "juan.perez@email.com",
    "rol": "USER"
}
```

#### `GET /api/usuarios` — Listar todos

**Respuesta (200 OK):**

```json
[
    {
        "id": 1,
        "nombre": "Juan Pérez",
        "correo": "juan.perez@email.com",
        "rol": "USER"
    },
    {
        "id": 2,
        "nombre": "Ana García",
        "correo": "ana.garcia@email.com",
        "rol": "ADMIN"
    }
]
```

#### `GET /api/usuarios/1` — Obtener por ID

**Respuesta (200 OK):**

```json
{
    "id": 1,
    "nombre": "Juan Pérez",
    "correo": "juan.perez@email.com",
    "rol": "USER"
}
```

#### `PUT /api/usuarios/1` — Actualizar usuario

**Petición:**

```json
{
    "nombre": "Juan Pérez López",
    "correo": "juan.perez@email.com",
    "contrasena": "nuevoPassword456",
    "rol": "ADMIN"
}
```

**Respuesta (200 OK):**

```json
{
    "id": 1,
    "nombre": "Juan Pérez López",
    "correo": "juan.perez@email.com",
    "rol": "ADMIN"
}
```

#### `DELETE /api/usuarios/1` — Eliminar usuario

**Respuesta:** `204 No Content` (sin cuerpo)

#### Correo duplicado

**Petición con correo existente (400 Bad Request):**

```json
{
    "error": "Ya existe un usuario registrado con el correo: juan.perez@email.com"
}
```

#### Usuario no encontrado

**Respuesta (404 Not Found):**

```json
{
    "error": "Usuario no encontrado con ID: 99"
}
```

---

## Manejo de Errores

La API cuenta con un manejador global de excepciones (`@RestControllerAdvice`) que captura y transforma las excepciones en respuestas HTTP consistentes:

| Excepción                        | Código HTTP        | Ejemplo de respuesta              |
|----------------------------------|--------------------|-----------------------------------|
| `RecursoNoEncontradoException`   | `404 Not Found`    | `{"error": "Producto no encontrado con ID: 99"}` |
| `IllegalArgumentException`       | `400 Bad Request`  | `{"error": "Ya existe un usuario registrado con el correo: ..."}` |
| `MethodArgumentNotValidException`| `400 Bad Request`  | `{"nombre": "El nombre del producto es obligatorio"}` |

---

## Autor

Proyecto desarrollado como ejercicio de aprendizaje de API REST con Spring Boot.

---

## Licencia

Este proyecto es de uso académico y libre.
