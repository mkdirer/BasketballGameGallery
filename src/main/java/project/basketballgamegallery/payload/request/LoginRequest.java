package project.basketballgamegallery.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * Represents a login request.
 */
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    /**
     * Gets the username from the login request.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username in the login request.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password from the login request.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password in the login request.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
