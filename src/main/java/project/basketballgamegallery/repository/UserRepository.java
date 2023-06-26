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
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findByUsernameContaining(String username);
    List<User> findByEmailContaining(String email);
    List<User> findByUsernameContainingAndAccessRights(String username, EnumAccessRight accessRight);
    List<User> findByEmailContainingAndAccessRights(String email, EnumAccessRight accessRight);
    //List<User> findByAccessRights(Set<EnumAccessRight> accessRight);
    @Query("SELECT u FROM User u JOIN u.accessRights ar WHERE ar.name IN :accessRights")
    List<User> findByAccessRights(@Param("accessRights") Set<EnumAccessRight> accessRights);

}