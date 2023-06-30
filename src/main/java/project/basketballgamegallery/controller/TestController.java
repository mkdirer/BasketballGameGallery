package project.basketballgamegallery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.basketballgamegallery.model.AccessRight;
import project.basketballgamegallery.model.EnumAccessRight;
import project.basketballgamegallery.model.User;
import project.basketballgamegallery.repository.AccessRightRepository;
import project.basketballgamegallery.repository.UserRepository;

import java.util.*;

/**
 * Controller for handling test-related endpoints.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccessRightRepository accessRightRepository;
    @GetMapping("/all")
    public String allAccess() {
        return "Welcome to BasketballGameGallery! This site is designed for basketball enthusiasts. We offer a platform to track your favorite players, clubs, and completed matches.";
    }

    /**
     * Retrieves all users by username, email, or role.
     *
     * @param username The username to filter users (optional)
     * @param email    The email to filter users (optional)
     * @param role     The role to filter users (optional)
     * @return ResponseEntity with a list of users
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role
    ) {
        try {
            List<User> users = new ArrayList<>();

            if (role != null) {
                switch (role) {
                    case "user":
                        if (username != null) {
                            userRepository.findByUsernameContainingAndAccessRights(username, EnumAccessRight.ROLE_USER).forEach(users::add);
                        } else if (email != null) {
                            userRepository.findByEmailContainingAndAccessRights(email, EnumAccessRight.ROLE_USER).forEach(users::add);
                        } else {
                            userRepository.findByAccessRights(Collections.singleton(EnumAccessRight.ROLE_USER)).forEach(users::add);
                        }
                        break;

                    case "mod":
                        if (username != null) {
                            userRepository.findByUsernameContainingAndAccessRights(username, EnumAccessRight.ROLE_MODERATOR).forEach(users::add);
                        } else if (email != null) {
                            userRepository.findByEmailContainingAndAccessRights(email, EnumAccessRight.ROLE_MODERATOR).forEach(users::add);
                        } else {
                            userRepository.findByAccessRights(Collections.singleton(EnumAccessRight.ROLE_MODERATOR)).forEach(users::add);
                        }
                        break;

                    case "admin":
                        if (username != null) {
                            userRepository.findByUsernameContainingAndAccessRights(username, EnumAccessRight.ROLE_ADMIN).forEach(users::add);
                        } else if (email != null) {
                            userRepository.findByEmailContainingAndAccessRights(email, EnumAccessRight.ROLE_ADMIN).forEach(users::add);
                        } else {
                            userRepository.findByAccessRights(Collections.singleton(EnumAccessRight.ROLE_ADMIN)).forEach(users::add);
                        }
                        break;

                    default:
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                userRepository.findAll().forEach(users::add);
            }

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all users with the 'USER' role.
     *
     * @return ResponseEntity with a list of users
     */
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<User>> getAllByUserRole() {
        try {
            List<User> users = new ArrayList<>();
            userRepository.findByAccessRights(Collections.singleton(EnumAccessRight.ROLE_USER)).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all users with the 'MODERATOR' role.
     *
     * @return ResponseEntity with a list of users
     */
    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<List<User>> getAllByModeratorRole() {
        try {
            List<User> users = new ArrayList<>();
            userRepository.findByAccessRights(Collections.singleton(EnumAccessRight.ROLE_MODERATOR)).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all users with the 'ADMIN' role.
     *
     * @return ResponseEntity with a list of users
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllByAdminRole() {
        try {
            List<User> users = new ArrayList<>();
            userRepository.findByAccessRights(Collections.singleton(EnumAccessRight.ROLE_ADMIN)).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user
     * @return ResponseEntity with the user information
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates the user information.
     *
     * @param id   The ID of the user to update
     * @param user The updated user object
     * @return ResponseEntity with the updated user information
     * @throws JsonProcessingException if there is an error processing the JSON data
     */
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) throws JsonProcessingException {
        Optional<User> _userData = userRepository.findById(id);
        System.out.println(user);
        String userJson = new ObjectMapper().writeValueAsString(user);
        System.out.println("Received User: " + userJson);
        if (_userData.isPresent()) {
            User _user = _userData.get();
            _user.setUsername(user.getUsername());
            _user.setEmail(user.getEmail());
            Set<AccessRight> accessRights = new HashSet<>();
            Set<AccessRight> oldAccessRights = user.getAccessRights();

            if (!oldAccessRights.isEmpty()) {
                AccessRight[] accessRightsArray = oldAccessRights.toArray(new AccessRight[0]);
                AccessRight firstAccessRight = accessRightsArray[0];

                AccessRight userAccessRight1 = accessRightRepository.save(new AccessRight(firstAccessRight.getName()));
                accessRights.add(userAccessRight1);
            }

            _user.setAccessRights(accessRights);
            System.out.print("Updated User accessRights: ");
            for (AccessRight accessRight : _user.getAccessRights()) {
                System.out.print(accessRight.getName() + ", ");
            }
            System.out.println();
                return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete
     * @return ResponseEntity with the operation status
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes all users.
     *
     * @return ResponseEntity with the operation status
     */
    @DeleteMapping("/user")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
