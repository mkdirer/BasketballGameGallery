package project.basketballgamegallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.basketballgamegallery.model.Player;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository interface for accessing Player entities in the database.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

    /**
     * Finds a list of Player entities by the team's ID.
     *
     * @param postId the ID of the team
     * @return a list of Player entities belonging to the specified team
     */
    List<Player> findByTeamId(Long postId);

    /**
     * Finds a list of Player entities by the game role's ID.
     *
     * @param gameRoleId the ID of the game role
     * @return a list of Player entities associated with the specified game role
     */
    List<Player> findPlayersByGameRolesId(Long gameRoleId);

    /**
     * Deletes players associated with the specified team by team ID.
     *
     * @param teamId the ID of the team
     */
    @Transactional
    void deleteByTeamId(long teamId);
}