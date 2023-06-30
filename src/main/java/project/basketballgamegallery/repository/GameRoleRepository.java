package project.basketballgamegallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.basketballgamegallery.model.GameRole;

import java.util.List;

/**
 * Repository interface for accessing GameRole entities in the database.
 */
public interface GameRoleRepository extends JpaRepository<GameRole, Long> {

    /**
     * Finds a list of GameRole entities by the player's ID.
     *
     * @param playerId the ID of the player
     * @return a list of GameRole entities associated with the player
     */
    List<GameRole> findGameRolesByPlayersId(Long playerId);

    /**
     * Finds a list of GameRole entities by the team's ID.
     *
     * @param teamId the ID of the team
     * @return a list of GameRole entities associated with the team
     */
    List<GameRole> findByTeamId(Long teamId);
}
