package project.basketballgamegallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.basketballgamegallery.model.Team;

import java.util.List;

/**
 * Repository interface for accessing Team entities in the database.
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

    /**
     * Finds a list of Team entities by name.
     *
     * @param name the name to search for
     * @return a list of Team entities with names containing the specified name
     */
    List<Team> findByNameContaining(String name);

    /**
     * Finds a list of Team entities by league.
     *
     * @param league the league to search for
     * @return a list of Team entities with leagues containing the specified league
     */
    List<Team> findByLeagueContaining(String league);
}
