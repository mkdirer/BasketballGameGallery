package project.basketballgamegallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.basketballgamegallery.model.Basketballer;

import java.util.List;

/**
 * Repository interface for accessing Basketballer entities in the database.
 */
public interface BasketballerRepository extends JpaRepository<Basketballer, Long> {

    /**
     * Finds a list of Basketballer entities by the team name containing the specified club name.
     *
     * @param club the club name to search for
     * @return a list of Basketballer entities matching the search criteria
     */
    List<Basketballer> findByTeamContaining(String club);

    /**
     * Finds a list of Basketballer entities by the lastname containing the specified lastname.
     *
     * @param lastname the lastname to search for
     * @return a list of Basketballer entities matching the search criteria
     */
    List<Basketballer> findByLastnameContaining(String lastname);
}
