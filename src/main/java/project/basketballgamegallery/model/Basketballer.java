package project.basketballgamegallery.model;

import javax.persistence.*;

/**
 * The Basketballer class represents a basketball player in the basketball game gallery project.
 * It contains information about the player's first name, last name, age, and team.
 */
@Entity
@Table(name = "basketballer")
public class Basketballer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "age")
    private int age;
    @Column(name = "team")
    private String team;

    /**
     * Default constructor for the Basketballer class.
     */
    public Basketballer() {
    }

    /**
     * Constructor for the Basketballer class that accepts the player's first name, last name, age, and team.
     *
     * @param firstname The first name of the basketball player
     * @param lastname  The last name of the basketball player
     * @param age       The age of the basketball player
     * @param team      The team of the basketball player
     */
    public Basketballer(String firstname, String lastname, int age, String team) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.team = team;
    }

    /**
     * Retrieves the ID of the basketball player.
     *
     * @return The ID of the basketball player
     */
    public Long getId() {
        return id;
    }

    /**
     * Retrieves the first name of the basketball player.
     *
     * @return The first name of the basketball player
     */
    public String getFirstName() { return firstname; }

    /**
     * Sets the first name of the basketball player.
     *
     * @param firstname The first name of the basketball player
     */
    public void setFirstName(String firstname) { this.firstname = firstname; }

    /**
     * Retrieves the last name of the basketball player.
     *
     * @return The last name of the basketball player
     */
    public String getLastName() { return lastname; }

    /**
     * Sets the last name of the basketball player.
     *
     * @param lastname The last name of the basketball player
     */
    public void setLastName(String lastname) { this.lastname = lastname; }

    /**
     * Retrieves the age of the basketball player.
     *
     * @return The age of the basketball player
     */
    public int getAge() { return age; }

    /**
     * Sets the age of the basketball player.
     *
     * @param age The age of the basketball player
     */
    public void setAge(int age) { this.age = age; }

    /**
     * Retrieves the team of the basketball player.
     *
     * @return The team of the basketball player
     */
    public String getTeam() { return team; }

    /**
     * Sets the team of the basketball player.
     *
     * @param team The team of the basketball player
     */
    public void setTeam(String team) { this.team = team; }

    /**
     * Returns a string representation of the basketball player.
     *
     * @return A string representation of the basketball player
     */
    @Override
    public String toString() {
        return "Very well player";
    }
}
