package project.basketballgamegallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.basketballgamegallery.model.Team;
import project.basketballgamegallery.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for handling basketball team data endpoints.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TeamController {
    @Autowired
    TeamRepository teamRepository;

    /**
     * Retrieves all teams or teams containing a specific name.
     *
     * @param name The name to filter teams (optional)
     * @return ResponseEntity with a list of teams
     */
    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams(@RequestParam(required = false) String name) {
        try {
            List<Team> teams = new ArrayList<Team>();
            if (name == null)
                teamRepository.findAll().forEach(teams::add);
            else
                teamRepository.findByNameContaining(name).forEach(teams::add);
            if (teams.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a team by its ID.
     *
     * @param id The ID of the team
     * @return ResponseEntity with the team
     */
    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") long id) {
        Optional<Team> clubData = teamRepository.findById(id);
        if (clubData.isPresent()) {
            return new ResponseEntity<>(clubData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new team.
     *
     * @param team The team object to be created
     * @return ResponseEntity with the created team
     */
    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        try {
            Team _team = teamRepository
                    .save(new Team(team.getName(), team.getCountry(), team.getLeague()));
            return new ResponseEntity<>(_team, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the details of a team.
     *
     * @param id   The ID of the team
     * @param team The updated team details
     * @return ResponseEntity with the updated team
     */
    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") long id, @RequestBody Team team) {
        Optional<Team> clubData = teamRepository.findById(id);
        if (clubData.isPresent()) {
            Team _team = clubData.get();
            _team.setName(team.getName());
            _team.setCountry(team.getCountry());
            _team.setLeague(team.getLeague());
            return new ResponseEntity<>(teamRepository.save(_team), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a team by its ID.
     *
     * @param id The ID of the team
     * @return ResponseEntity with the HTTP status
     */
    @DeleteMapping("/teams/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") long id) {
        try {
            teamRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes all teams.
     *
     * @return ResponseEntity with the HTTP status
     */
    @DeleteMapping("/teams")
    public ResponseEntity<HttpStatus> deleteAllTeams() {
        try {
            teamRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves teams by the specified league.
     *
     * @param league The league to filter teams
     * @return ResponseEntity with a list of teams
     */
    @GetMapping("/teams/league")
    public ResponseEntity<List<Team>> findByPublished(@RequestParam(required = true) String league) {
        try {
            List<Team> teams = teamRepository.findByLeagueContaining(league);
            if (teams.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
