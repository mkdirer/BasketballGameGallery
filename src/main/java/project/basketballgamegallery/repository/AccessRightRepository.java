package project.basketballgamegallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.basketballgamegallery.model.EnumAccessRight;
import project.basketballgamegallery.model.AccessRight;

import java.util.Optional;

/**
 * Repository interface for accessing AccessRight entities in the database.
 */
@Repository
public interface AccessRightRepository extends JpaRepository<AccessRight, Long> {

    /**
     * Finds an AccessRight entity by its name.
     *
     * @param name the name of the AccessRight
     * @return an Optional containing the AccessRight entity, or empty if not found
     */
    Optional<AccessRight> findByName(EnumAccessRight name);
}
