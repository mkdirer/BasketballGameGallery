package project.basketballgamegallery.model;

import javax.persistence.*;

/**
 * The AccessRight class represents a specific access right or role in the basketball game gallery project.
 * It is used to define the access rights of users in the system.
 */
@Entity
@Table(name = "accessrights")
public class AccessRight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumAccessRight name;

    /**
     * Default constructor for the AccessRight class.
     */
    public AccessRight() {
    }

    /**
     * Constructor for the AccessRight class that accepts an EnumAccessRight value.
     *
     * @param name The access right name
     */
    public AccessRight(EnumAccessRight name) {
        this.name = name;
    }

    /**
     * Constructor for the AccessRight class that accepts a string representation of the access right name.
     *
     * @param name The access right name as a string
     */
    public AccessRight(String name) {
        this.name = EnumAccessRight.valueOf(name);
    }

    /**
     * Retrieves the ID of the access right.
     *
     * @return The access right ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the access right.
     *
     * @param id The access right ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the access right.
     *
     * @return The access right name
     */
    public EnumAccessRight getName() {
        return name;
    }

    /**
     * Sets the name of the access right.
     *
     * @param name The access right name
     */
    public void setName(EnumAccessRight name) {
        this.name = name;
    }
}
