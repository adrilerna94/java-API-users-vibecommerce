package com.adriauson.vibecommerce.vibecommerce.handler;

import com.adriauson.vibecommerce.vibecommerce.exception.EmailAlreadyExistsException;
import com.adriauson.vibecommerce.vibecommerce.exception.UserNoSuchElementException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/*
 * 🔹 GlobalExceptionHandler usa @RestControllerAdvice para capturar excepciones globalmente
 *    sin necesidad de manejarlas manualmente en cada controlador.
 *
 * 🔹 Este ejemplo captura EmailAlreadyExistsException y devuelve una respuesta personalizada
 *    con código HTTP 409 (Conflict) y un cuerpo con mensaje, status y error.
 *
 * 🔹 Esto mejora la claridad de los errores para el cliente y evita errores 500 innecesarios.
 */


// ✅ Esta clase maneja excepciones de forma centralizada en toda la aplicación
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 👉 Este método se ejecuta cuando se lanza EmailAlreadyExistsException
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailExists(EmailAlreadyExistsException exception) {

        // 🧾 Creamos una respuesta personalizada como mapa clave-valor
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CONFLICT.value()); // 409
        response.put("error", "Conflict"); // Nombre del estado HTTP
        response.put("message", exception.getMessage()); // Mensaje que lanzamos desde el servicio

        // 📤 Devolvemos una respuesta con cuerpo personalizado y código 409 Conflict
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UserNoSuchElementException.class)
    public ResponseEntity<?> handleUserNoSuchElementException(UserNoSuchElementException exception) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Not Found");
        response.put("message", exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

/*
 * 📌 <?> es un wildcard (comodín) genérico en Java.
 * Se usa cuando el tipo real no importa o no es necesario conocerlo.
 *
 * En ResponseEntity<?>, indica que el tipo del cuerpo puede ser cualquiera:
 * un objeto, un String, un Map, etc.
 *
 * Esto permite flexibilidad al devolver distintos tipos de respuesta HTTP
 * sin acoplarse a un tipo específico.
 */
