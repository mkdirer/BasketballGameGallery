package project.basketballgamegallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.basketballgamegallery.model.Match;

import java.util.List;

/**
 * Repository interface for accessing Match entities in the database.
 */
public interface MatchRepository extends JpaRepository<Match, Long> {

    /**
     * Finds a list of Match entities by the home team's name containing the specified club name.
     *
     * @param club the name of the club to search for
     * @return a list of Match entities where the home team's name contains the specified club name
     */
    List<Match> findByHomeTeamContaining(String club);

    /**
     * Finds a list of Match entities by the visiting team's name containing the specified club name.
     *
     * @param club the name of the club to search for
     * @return a list of Match entities where the visiting team's name contains the specified club name
     */
    List<Match> findByVisitTeamContaining(String club);
}
