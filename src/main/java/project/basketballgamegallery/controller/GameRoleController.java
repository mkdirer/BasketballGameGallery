package project.basketballgamegallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.basketballgamegallery.model.GameRole;
import project.basketballgamegallery.model.Player;
import project.basketballgamegallery.repository.TeamRepository;
import project.basketballgamegallery.repository.GameRoleRepository;
import project.basketballgamegallery.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class GameRoleController {
    @Autowired
    GameRoleRepository gameRoleRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Retrieves all game roles.
     *
     * @return ResponseEntity containing a list of GameRole objects if found, or HttpStatus.NO_CONTENT if no game roles are available.
     */
    @GetMapping("/gameroles")
    public ResponseEntity<List<GameRole>> getAllGameRole() {
        List<GameRole> gameRoles = new ArrayList<GameRole>();
        gameRoleRepository.findAll().forEach(gameRoles::add);

        if (gameRoles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gameRoles, HttpStatus.OK);
    }

    /**
     * Retrieves a specific game role by its ID.
     *
     * @param id The ID of the game role to retrieve.
     * @return ResponseEntity containing the GameRole object if found, or HttpStatus.NOT_FOUND if the game role is not found.
     */
    @GetMapping("/gameroles/{id}")
    public ResponseEntity<GameRole> getGameRoleById(@PathVariable("id") long id) {
        GameRole gameRole = gameRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Player Position with id = " + id));
        return new ResponseEntity<>(gameRole, HttpStatus.OK);
    }

    /**
     * Retrieves all game roles associated with a specific team.
     *
     * @param teamId The ID of the team.
     * @return ResponseEntity containing a list of GameRole objects if found, or HttpStatus.NOT_FOUND if the team is not found.
     */
    @GetMapping("/teams/{clubId}/gameroles")
    public ResponseEntity<List<GameRole>> getAllPlayersByClubId(@PathVariable(value = "clubId") Long teamId) {
        if (!teamRepository.existsById(teamId)) {
            throw new RuntimeException("Not found Team with id = " + teamId);
        }
        List<GameRole> gameRoles = gameRoleRepository.findByTeamId(teamId);
        return new ResponseEntity<>(gameRoles, HttpStatus.OK);
    }

    /**
     * Creates a new game role associated with a specific team.
     *
     * @param teamId          The ID of the team.
     * @param gameRoleRequest The GameRole object to be created.
     * @return ResponseEntity containing the created GameRole object if successful, or HttpStatus.NOT_FOUND if the team is not found.
     */
    @PostMapping("/teams/{clubId}/gamerole")
    public ResponseEntity<GameRole> createGameRole(@PathVariable(value = "clubId") Long teamId,
                                                   @RequestBody GameRole gameRoleRequest) {
        GameRole gameRole = teamRepository.findById(teamId).map(team -> {
            gameRoleRequest.setTeam(team);
            return gameRoleRepository.save(gameRoleRequest);
        }).orElseThrow(() -> new RuntimeException("Not found Team with id = " + teamId));
        return new ResponseEntity<>(gameRole, HttpStatus.CREATED);
    }

    /**
     * Updates a specific game role.
     *
     * @param id        The ID of the game role to update.
     * @param gameRole  The updated GameRole object.
     * @return ResponseEntity containing the updated GameRole object if successful, or HttpStatus.NOT_FOUND if the game role is not found.
     */
    @PutMapping("/gameroles/{id}")
    public ResponseEntity<GameRole> updateGameRole(@PathVariable("id") long id, @RequestBody GameRole gameRole) {
        GameRole _gameRole = gameRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Player Position with id = " + id));
        _gameRole.setPoints(gameRole.getPoints());

        return new ResponseEntity<>(gameRoleRepository.save(_gameRole), HttpStatus.OK);
    }

    /**
     * Deletes a specific game role.
     *
     * @param id The ID of the game role to delete.
     * @return ResponseEntity with HttpStatus.NO_CONTENT if the game role is successfully deleted.
     */
    @DeleteMapping("/gameroles/{id}")
    public ResponseEntity<HttpStatus> deleteGameRole(@PathVariable("id") long id) {
        gameRoleRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes all game roles.
     *
     * @return ResponseEntity with HttpStatus.NO_CONTENT if all game roles are successfully deleted.
     */
    @DeleteMapping("/gameroles")
    public ResponseEntity<HttpStatus> deleteAllGameRoles() {
        gameRoleRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves all players associated with a specific game role.
     *
     * @param gameRoleId The ID of the game role.
     * @return ResponseEntity containing a list of Player objects if found, or HttpStatus.NOT_FOUND if the game role is not found.
     */
    @GetMapping("/gameroles/{gameRoleId}/players")
    public ResponseEntity<List<Player>> getAllPlayersByGameRoleId(@PathVariable(value = "gameRoleId") Long gameRoleId) {
        if (!gameRoleRepository.existsById(gameRoleId)) {
            throw new RuntimeException("Not found Player Position with id = " + gameRoleId);
        }
        List<Player> players = playerRepository.findPlayersByGameRolesId(gameRoleId);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    /**
     * Retrieves all game roles associated with a specific player.
     *
     * @param playerId The ID of the player.
     * @return ResponseEntity containing a list of GameRole objects if found, or HttpStatus.NOT_FOUND if the player is not found.
     */
    @GetMapping("/players/{playerId}/gameroles")
    public ResponseEntity<List<GameRole>> getAllGameRolesByPlayerId(@PathVariable(value = "playerId") Long playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw new RuntimeException("Not found Player Position with id = " + playerId);
        }
        List<GameRole> gameRoles = gameRoleRepository.findGameRolesByPlayersId(playerId);
        return new ResponseEntity<>(gameRoles, HttpStatus.OK);
    }

    /**
     * Adds a player to a specific game role.
     *
     * @param gameRoleId     The ID of the game role.
     * @param playerRequest  The Player object to be added.
     * @return ResponseEntity containing the created Player object if successful, or HttpStatus.NOT_FOUND if the game role is not found.
     */
    @PostMapping("/gameroles/{gameRoleId}/player")
    public ResponseEntity<Player> addPlayer(@PathVariable(value = "gameRoleId") Long gameRoleId, @RequestBody Player playerRequest) {
        Player tag = gameRoleRepository.findById(gameRoleId).map(gameRole -> {
            long playerId = playerRequest.getId();

            // playerId is existed
            if (playerId != 0L) {
                Player _player = playerRepository.findById(playerId)
                        .orElseThrow(() -> new RuntimeException("Not found Player Position with id = " + playerId));
                gameRole.addPlayer(_player);
                gameRoleRepository.save(gameRole);
                return _player;
            }

            // add and create new Player Position
            gameRole.addPlayer(playerRequest);
            return playerRepository.save(playerRequest);
        }).orElseThrow(() -> new RuntimeException("Not found Player Position with id = " + gameRoleId));
        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }
}
