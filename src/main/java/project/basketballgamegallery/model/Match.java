package project.basketballgamegallery.model;

import javax.persistence.*;

/**
 * Represents a basketball match.
 */
@Entity
@Table(name = "match2")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private String date;
    @Column(name = "result")
    private String result;
    @Column(name = "homeTeam")
    private String homeTeam;
    @Column(name = "visitTeam")
    private String visitTeam;

    @Column(name = "visitPointGuard")
    private String visitPointGuard;
    @Column(name = "visitShootingGuard")
    private String visitShootingGuard;
    @Column(name = "visitSmallForward")
    private String visitSmallForward;
    @Column(name = "visitPowerForward")
    private String visitPowerForward;
    @Column(name = "visitCenter")
    private String visitCenter;

    @Column(name = "homePointGuard")
    private String homePointGuard;
    @Column(name = "homeShootingGuard")
    private String homeShootingGuard;
    @Column(name = "homeLeftCenterBack")
    private String homeSmallForward;
    @Column(name = "homePowerForward")
    private String homePowerForward;
    @Column(name = "homeCenter")
    private String homeCenter;

    /**
     * Default constructor for the Match class.
     */
    public Match() {
    }

    /**
     * Constructor for the Match class that sets the match details.
     *
     * @param date             the date of the match
     * @param result           the result of the match
     * @param homeTeam         the home team name
     * @param visitTeam        the visiting team name
     * @param visitPointGuard  the visiting team's point guard
     * @param visitShootingGuard  the visiting team's shooting guard
     * @param visitSmallForward  the visiting team's small forward
     * @param visitPowerForward  the visiting team's power forward
     * @param visitCenter  the visiting team's center
     * @param homePointGuard   the home team's point guard
     * @param homeShootingGuard  the home team's shooting guard
     * @param homeSmallForward  the home team's small forward
     * @param homePowerForward  the home team's power forward
     * @param homeCenter  the home team's center
     */
    public Match(String date, String result, String homeTeam, String visitTeam,
                 String visitPointGuard, String visitShootingGuard, String visitSmallForward, String visitPowerForward,
                 String visitCenter,
                 String homePointGuard, String homeShootingGuard, String homeSmallForward, String homePowerForward,
                 String homeCenter)
    {
        this.date = date;
        this.result = result;
        this.homeTeam = homeTeam;
        this.visitTeam = visitTeam;
        this.setVisitPointGuard(visitPointGuard);
        this.setVisitShootingGuard(visitShootingGuard);
        this.setVisitSmallForward(visitSmallForward);
        this.setVisitPowerForward(visitPowerForward);
        this.setVisitCenter(visitCenter);
        this.setHomePointGuard(homePointGuard);
        this.setHomeShootingGuard(homeShootingGuard);
        this.setHomeSmallForward(homeSmallForward);
        this.setHomePowerForward(homePowerForward);
        this.setHomeCenter(homeCenter);
    }

    /**
     * Get the ID of the match.
     *
     * @return the ID of the match
     */
    public long getId() {
        return id;
    }

    /**
     * Get the date of the match.
     *
     * @return the date of the match
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of the match.
     *
     * @param date the date of the match
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the result of the match.
     *
     * @return the result of the match
     */
    public String getResult() {
        return result;
    }

    /**
     * Set the result of the match.
     *
     * @param result the result of the match
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Get the home team name.
     *
     * @return the home team name
     */
    public String getHomeTeam() {
        return homeTeam;
    }

    /**
     * Set the home team name.
     *
     * @param homeTeam the home team name
     */
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    /**
     * Get the visiting team name.
     *
     * @return the visiting team name
     */
    public String getVisitTeam() {
        return visitTeam;
    }

    /**
     * Set the visiting team name.
     *
     * @param visitTeam the visiting team name
     */
    public void setVisitTeam(String visitTeam) {
        this.visitTeam = visitTeam;
    }

    /**
     * Get the visiting team's point guard.
     *
     * @return the visiting team's point guard
     */
    public String getVisitPointGuard() {
        return visitPointGuard;
    }

    /**
     * Set the visiting team's point guard.
     *
     * @param visitPointGuard the visiting team's point guard
     */
    public void setVisitPointGuard(String visitPointGuard) {
        this.visitPointGuard = visitPointGuard;
    }

    /**
     * Get the visiting team's shooting guard.
     *
     * @return the visiting team's shooting guard
     */
    public String getVisitShootingGuard() {
        return visitShootingGuard;
    }

    /**
     * Set the visiting team's shooting guard.
     *
     * @param visitShootingGuard the visiting team's shooting guard
     */
    public void setVisitShootingGuard(String visitShootingGuard) {
        this.visitShootingGuard = visitShootingGuard;
    }

    /**
     * Get the visiting team's small forward.
     *
     * @return the visiting team's small forward
     */
    public String getVisitSmallForward() {
        return visitSmallForward;
    }

    /**
     * Set the visiting team's small forward.
     *
     * @param visitSmallForward the visiting team's small forward
     */
    public void setVisitSmallForward(String visitSmallForward) {
        this.visitSmallForward = visitSmallForward;
    }

    /**
     * Get the visiting team's power forward.
     *
     * @return the visiting team's power forward
     */
    public String getVisitPowerForward() {
        return visitPowerForward;
    }

    /**
     * Set the visiting team's power forward.
     *
     * @param visitPowerForward the visiting team's power forward
     */
    public void setVisitPowerForward(String visitPowerForward) {
        this.visitPowerForward = visitPowerForward;
    }

    /**
     * Get the visiting team's center.
     *
     * @return the visiting team's center
     */
    public String getVisitCenter() {
        return visitCenter;
    }

    /**
     * Set the visiting team's center.
     *
     * @param visitCenter the visiting team's center
     */
    public void setVisitCenter(String visitCenter) {
        this.visitCenter = visitCenter;
    }

    /**
     * Get the home team's point guard.
     *
     * @return the home team's point guard
     */
    public String getHomePointGuard() {
        return homePointGuard;
    }

    /**
     * Set the home team's point guard.
     *
     * @param homePointGuard the home team's point guard
     */
    public void setHomePointGuard(String homePointGuard) {
        this.homePointGuard = homePointGuard;
    }

    /**
     * Get the home team's shooting guard.
     *
     * @return the home team's shooting guard
     */
    public String getHomeShootingGuard() {
        return homeShootingGuard;
    }

    /**
     * Set the home team's shooting guard.
     *
     * @param homeShootingGuard the home team's shooting guard
     */
    public void setHomeShootingGuard(String homeShootingGuard) {
        this.homeShootingGuard = homeShootingGuard;
    }

    /**
     * Get the home team's small forward.
     *
     * @return the home team's small forward
     */
    public String getHomeSmallForward() {
        return homeSmallForward;
    }

    /**
     * Set the home team's small forward.
     *
     * @param homeSmallForward the home team's small forward
     */
    public void setHomeSmallForward(String homeSmallForward) {
        this.homeSmallForward = homeSmallForward;
    }

    /**
     * Get the home team's power forward.
     *
     * @return the home team's power forward
     */
    public String getHomePowerForward() {
        return homePowerForward;
    }

    /**
     * Set the home team's power forward.
     *
     * @param homePowerForward the home team's power forward
     */
    public void setHomePowerForward(String homePowerForward) {
        this.homePowerForward = homePowerForward;
    }

    /**
     * Get the home team's center.
     *
     * @return the home team's center
     */
    public String getHomeCenter() {
        return homeCenter;
    }

    /**
     * Set the home team's center.
     *
     * @param homeCenter the home team's center
     */
    public void setHomeCenter(String homeCenter) {
        this.homeCenter = homeCenter;
    }
}
