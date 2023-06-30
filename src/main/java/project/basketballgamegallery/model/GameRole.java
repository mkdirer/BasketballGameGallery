package project.basketballgamegallery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a game role in a basketball game.
 */
@Entity
@Table(name = "gamerole")
public class GameRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "points")
    private int points;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Team team;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "gamerole_player",
            joinColumns = { @JoinColumn(name = "gameroles_id") },
            inverseJoinColumns = { @JoinColumn(name = "player_id") })
    private Set<Player> players = new HashSet<>();

    /**
     * Default constructor for the GameRole class.
     */
    public GameRole()
    {
    }

    /**
     * Constructor for the GameRole class that sets the points.
     *
     * @param points the points to set for the game role
     */
    public GameRole(int points)
    {
        this.points = points;
    }

    /**
     * Get the ID of the game role.
     *
     * @return the ID of the game role
     */
    public long getId() {
        return id;
    }
    /**
     * Get the points of the game role.
     *
     * @return the points of the game role
     */
    public int getPoints() { return points; }

    /**
     * Set the points of the game role.
     *
     * @param points the points to set for the game role
     */
    public void setPoints(int points) { this.points = points; }

    /**
     * Get the team associated with the game role.
     *
     * @return the team associated with the game role
     */
    public Team getTeam() { return team; }

    /**
     * Set the team associated with the game role.
     *
     * @param team the team to set for the game role
     */
    public void setTeam(Team team) { this.team = team; }

    /**
     * Add a player to the game role.
     *
     * @param player the player to add to the game role
     */
    public void addPlayer(Player player) {
        this.players.add(player);
        player.getGameRoles().add(this);
    }

    /**
     * Remove a player from the game role.
     *
     * @param playerId the ID of the player to remove from the game role
     */
    public void removePlayer(long playerId) {
        Player player = this.players.stream().filter(p -> p.getId() == playerId).findFirst().orElse(null);
        if (player != null) {
            this.players.remove(player);
            player.getGameRoles().remove(this);
        }
    }

    /**
     * Get the match associated with the game role.
     *
     * @return the match associated with the game role
     */
    public Match getMatch() { return this.match; }

    /**
     * Set the match associated with the game role.
     *
     * @param match the match to set for the game role
     */
    public void setMatch(Match match) { this.match = match; }

    @Override
    public String toString() {
        return "sklad gracza";
    }
}
