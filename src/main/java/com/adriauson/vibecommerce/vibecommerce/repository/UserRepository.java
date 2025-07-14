package com.adriauson.vibecommerce.vibecommerce.repository;


import com.adriauson.vibecommerce.vibecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * UserRepository extiende JpaRepository, lo que proporciona automáticamente
 * todos los métodos CRUD básicos sin necesidad de implementarlos manualmente.
 *
 * Métodos heredados de JpaRepository:
 *  - findById(Long id)
 *  - findAll()
 *  - save(User user)
 *  - deleteById(Long id)
 *  - existsById(Long id)
 *
 * Por eso, no es necesario definir métodos personalizados para operaciones comunes.
 *
 * Se define el método existsByEmail(String email) como ejemplo útil para validar,
 * por ejemplo, si un usuario ya está registrado al momento de crear uno nuevo.
 *
 * Spring Data JPA permite generar consultas automáticamente a partir del nombre
 * del método (query derivation), por lo tanto, escribir existsByEmail(...) es suficiente
 * para que Spring genere la lógica internamente.
 *
 * Solo se deben añadir más métodos personalizados si son necesarios
 * para una funcionalidad específica (principio YAGNI: "You Aren’t Gonna Need It").
 */

/*
 🔍 ¿Qué es JpaRepository<T, ID>?
  JpaRepository es una interfaz genérica de Spring Data JPA
  que proporciona toda la funcionalidad estándar de acceso a datos para entidades JPA
  (como guardar, buscar, eliminar, etc.).

   ➡️ T → es el tipo de entidad que vas a gestionar (tu clase modelo).

   ➡️ ID → es el tipo del identificador (clave primaria) de esa entidad.

    User: la entidad que representa la tabla (por ejemplo, users)
    Long: el tipo de dato del campo @Id en la clase User
*/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email); // comprobaremos si ya existe usuario con esa cuenta
}
