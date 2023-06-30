package project.basketballgamegallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.basketballgamegallery.model.AccessRight;
import project.basketballgamegallery.model.EnumAccessRight;
import project.basketballgamegallery.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Repository interface for accessing User entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User entity by username.
     *
     * @param username the username to search for
     * @return an optional User entity with the specified username
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a User entity exists with the given username.
     *
     * @param username the username to check
     * @return true if a User entity with the specified username exists, false otherwise
     */
    Boolean existsByUsername(String username);

    /**
     * Checks if a User entity exists with the given email.
     *
     * @param email the email to check
     * @return true if a User entity with the specified email exists, false otherwise
     */
    Boolean existsByEmail(String email);

    /**
     * Finds a list of User entities by username.
     *
     * @param username the username to search for
     * @return a list of User entities with usernames containing the specified username
     */
    List<User> findByUsernameContaining(String username);

    /**
     * Finds a list of User entities by email.
     *
     * @param email the email to search for
     * @return a list of User entities with emails containing the specified email
     */
    List<User> findByEmailContaining(String email);

    /**
     * Finds a list of User entities by username and access right.
     *
     * @param username     the username to search for
     * @param accessRight  the access right to search for
     * @return a list of User entities with usernames containing the specified username and having the specified access right
     */
    List<User> findByUsernameContainingAndAccessRights(String username, EnumAccessRight accessRight);

    /**
     * Finds a list of User entities by email and access right.
     *
     * @param email        the email to search for
     * @param accessRight  the access right to search for
     * @return a list of User entities with emails containing the specified email and having the specified access right
     */
    List<User> findByEmailContainingAndAccessRights(String email, EnumAccessRight accessRight);
    //List<User> findByAccessRights(Set<EnumAccessRight> accessRight);

    /**
     * Finds a list of User entities by access rights.
     *
     * @param accessRights the access rights to search for
     * @return a list of User entities with the specified access rights
     */
    @Query("SELECT u FROM User u JOIN u.accessRights ar WHERE ar.name IN :accessRights")
    List<User> findByAccessRights(@Param("accessRights") Set<EnumAccessRight> accessRights);
}