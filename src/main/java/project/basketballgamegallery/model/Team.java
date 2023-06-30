package project.basketballgamegallery.model;

import javax.persistence.*;

/**
 * Represents a basketball team.
 */
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "league")
    private String league;

    /**
     * Creates a new instance of the Team class.
     */
    public Team() {
    }

    /**
     * Creates a new instance of the Team class with specified details.
     *
     * @param name    the name of the team
     * @param country the country of the team
     * @param league  the league of the team
     */
    public Team(String name, String country, String league) {
        this.name = name;
        this.country = country;
        this.league = league;
    }

    /**
     * Gets the ID of the team.
     *
     * @return the ID of the team
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the name of the team.
     *
     * @return the name of the team
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the team.
     *
     * @param name the name of the team
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the country of the team.
     *
     * @return the country of the team
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the team.
     *
     * @param country the country of the team
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the league of the team.
     *
     * @return the league of the team
     */
    public String getLeague() {
        return league;
    }

    /**
     * Sets the league of the team.
     *
     * @param league the league of the team
     */
    public void setLeague(String league) {
        this.league = league;
    }

    @Override
    public String toString() {
        return "My Teams";
    }
}
