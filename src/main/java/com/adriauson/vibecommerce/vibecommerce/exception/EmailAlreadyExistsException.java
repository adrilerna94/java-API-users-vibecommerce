package com.adriauson.vibecommerce.vibecommerce.exception;

/*
 * 📌 EmailAlreadyExistsException es una excepción personalizada que extiende RuntimeException.
 * Se lanza cuando se intenta registrar un usuario con un email ya existente.
 *
 * 🎯 La manejamos globalmente con @RestControllerAdvice para devolver
 *     un error limpio y semántico (409 Conflict) en vez de 500 Internal Server Error.
 *
 * ✅ Al usar una excepción específica:
 *    - Mejoramos la legibilidad del código
 *    - Separamos errores de negocio de errores técnicos
 *    - Podemos capturarla con @ExceptionHandler de forma controlada
 *    - Evitamos exponer trazas innecesarias al cliente
 *
 * 💡 Esto mejora la experiencia del cliente API y mantiene la aplicación robusta y coherente.
 */


// ✅ Creamos una excepción personalizada para manejar casos donde el email ya existe
public class EmailAlreadyExistsException extends RuntimeException {

    // 🧱 Constructor que recibe un mensaje de error personalizado
    public EmailAlreadyExistsException(String message) {
        // 🔁 Llama al constructor de la clase base (RuntimeException) con ese mensaje
        super(message);
    }
}

