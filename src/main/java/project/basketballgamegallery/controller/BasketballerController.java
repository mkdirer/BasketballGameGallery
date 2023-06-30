package project.basketballgamegallery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.basketballgamegallery.model.Basketballer;
import project.basketballgamegallery.repository.BasketballerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for routing player data
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BasketballerController {
    @Autowired
    BasketballerRepository basketballerRepository;

    /**
     * Returns all basketball players or those containing the specified last name or team name
     * @param lastname
     * @param team
     * @return
     */
    @GetMapping("/basketballers")
    public ResponseEntity<List<Basketballer>> getAllBasketballers(@RequestParam(required = false) String lastname,
                                                                  @RequestParam(required = false) String team) {
        try {
            List<Basketballer> players = new ArrayList<Basketballer>();
            if (lastname != null)
                basketballerRepository.findByLastnameContaining(lastname).forEach(players::add);
            else if (team != null)
                basketballerRepository.findByTeamContaining(team).forEach(players::add);
            else
                basketballerRepository.findAll().forEach(players::add);
            if (players.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(players, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a new basketball player in the database
     * @param player
     * @return
     */
    @PostMapping("/basketballers")
    public ResponseEntity<Basketballer> createBasketballers(@RequestBody Basketballer player) {
        try {
            Basketballer _player = basketballerRepository
                    .save(new Basketballer(player.getFirstName(), player.getLastName(), player.getAge(), player.getTeam()));
            return new ResponseEntity<>(_player, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Returns the basketball player with the specified id
     * @param id
     * @return
     */
    @GetMapping("/basketballers/{id}")
    public ResponseEntity<Basketballer> getBasketballerById(@PathVariable("id") long id) {
        Optional<Basketballer> playerData = basketballerRepository.findById(id);
        if (playerData.isPresent()) {
            return new ResponseEntity<>(playerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates the data of a basketball player
     * @param id
     * @param player
     * @return
     */
    @PutMapping("/basketballers/{id}")
    public ResponseEntity<Basketballer> updateBasketballer(@PathVariable("id") long id, @RequestBody Basketballer player) {
        Optional<Basketballer> _playerData = basketballerRepository.findById(id);
        if (_playerData.isPresent()) {
            Basketballer _player = _playerData.get();
            _player.setFirstName(player.getFirstName());
            _player.setLastName(player.getLastName());
            _player.setAge(player.getAge());
            _player.setTeam(player.getTeam());
            return new ResponseEntity<>(basketballerRepository.save(_player), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a specific basketball player
     * @param id
     * @return
     */
    @DeleteMapping("/basketballers/{id}")
    public ResponseEntity<HttpStatus> deleteBasketballer(@PathVariable("id") long id) {
        try {
            basketballerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes all basketball players
     * @return
     */
    @DeleteMapping("/basketballers")
    public ResponseEntity<HttpStatus> deleteAllBasketballers() {
        try {
            basketballerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
