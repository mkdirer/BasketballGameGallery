package project.basketballgamegallery.payload.request;

import javax.validation.constraints.*;
import java.util.Set;

/**
 * Represents a signup request.
 */
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    /**
     * Gets the username from the signup request.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username in the signup request.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email from the signup request.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email in the signup request.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password from the signup request.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password in the signup request.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role from the signup request.
     *
     * @return the role
     */
    public Set<String> getRole() {
        return this.role;
    }

    /**
     * Sets the role in the signup request.
     *
     * @param role the role
     */
    public void setRole(Set<String> role) {
        this.role = role;
    }
}
