package project.basketballgamegallery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_generator")
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "age")
    private int age;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Team team;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "players")
    @JsonIgnore
    private Set<GameRole> gameRoles = new HashSet<>();

    /**
     * Constructs an empty Player object.
     */
    public Player() {
    }

    /**
     * Constructs a Player object with the specified first name, last name, and age.
     *
     * @param firstname the first name of the player
     * @param lastname  the last name of the player
     * @param age       the age of the player
     */
    public Player(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    /**
     * Get the ID of the player.
     *
     * @return the ID of the player
     */
    public long getId() {
        return id;
    }

    /**
     * Get the first name of the player.
     *
     * @return the first name of the player
     */
    public String getFirstName() { return firstname; }

    /**
     * Set the first name of the player.
     *
     * @param firstname the first name of the player
     */
    public void setFirstName(String firstname) { this.firstname = firstname; }

    /**
     * Get the last name of the player.
     *
     * @return the last name of the player
     */
    public String getLastName() { return lastname; }

    /**
     * Set the last name of the player.
     *
     * @param lastname the last name of the player
     */
    public void setLastName(String lastname) { this.lastname = lastname; }

    /**
     * Get the age of the player.
     *
     * @return the age of the player
     */
    public int getAge() { return age; }

    /**
     * Set the age of the player.
     *
     * @param age the age of the player
     */
    public void setAge(int age) { this.age = age; }

    /**
     * Get the team that the player belongs to.
     *
     * @return the team that the player belongs to
     */
    public Team getTeam() { return team; }

    /**
     * Set the team that the player belongs to.
     *
     * @param team the team that the player belongs to
     */
    public void setTeam(Team team) { this.team = team; }

    /**
     * Get the set of game roles that the player has.
     *
     * @return the set of game roles that the player has
     */
    public Set<GameRole> getGameRoles() {
        return gameRoles;
    }

    /**
     * Set the set of game roles for the player.
     *
     * @param gameRoles the set of game roles for the player
     */
    public void setGameRoles(Set<GameRole> gameRoles) {
        this.gameRoles = gameRoles;
    }

    /**
     * Returns a string representation of the Player object.
     *
     * @return a string representation of the Player object
     */
    @Override
    public String toString() {
        return "Player gracz";
    }
}
