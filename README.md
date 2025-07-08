# 🧪 Reto – API REST Usuarios

## Objetivo

Desarrollar una pequeña API REST que permita gestionar usuarios. Este reto nos permitirá valorar tu capacidad para estructurar código backend, implementar validaciones, conectar con una base de datos y documentar tu solución.

---

## 📌 Funcionalidades mínimas

Debes implementar una API REST que permita:

- Crear un nuevo usuario
- Consultar todos los usuarios
- Consultar un usuario por su ID
- Actualizar los datos de un usuario
- Eliminar un usuario

---

## 🧱 Modelo de datos

Un usuario debe tener los siguientes campos, junto con sus validaciones:

```json
{
  "id": 1,
  "email": "usuario@ejemplo.com",            // Debe tener formato válido (contener "@")
  "password": "Abcde",                        // Mínimo 5 caracteres, con al menos una mayúscula y una minúscula
  "confirmPassword": "Abcde",                // Debe coincidir con el campo 'password'
  "firstName": "Joan",                        // Solo letras
  "lastName": "Garcia",                       // Solo letras
  "address": "Carrer de Mallorca, 123"        // Sin validación específica
}
````
## 🔌 Endpoints requeridos

| Método | Ruta         | Descripción                       |
|--------|--------------|-----------------------------------|
| POST   | `/users`     | Crear una nueva tarea             |
| GET    | `/users`     | Listar todas las tareas           |
| GET    | `/users/:id` | Obtener una tarea por su ID       |
| PUT    | `/users/:id` | Actualizar una tarea existente    |
| DELETE | `/users/:id` | Eliminar una tarea                |

---

## 🛠 Tecnologías
- Lenguaje: Java
- Frameworks: Spring Boot
- DB: MySQL
---

## 🗂 Entrega

- Sube el código a un repositorio GitHub (público o privado).
- Incluye un `README.md` con:
  - Cómo ejecutar el proyecto
  - Tecnologías usadas
  - Estructura básica del código
  - Notas adicionales si las hay

---

## 🌟 Extra (opcional pero valorado)

- Documentación con Swagger/OpenAPI
- Tests (unitarios o de integración)
- Contenedor Docker
- Validaciones de datos
- Manejo de errores

---

## 🕒 Plazo

Tienes hasta el **Viernes 11 de Julio a las 00:00h** para realizar este ejercicio.

---