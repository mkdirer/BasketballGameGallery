package project.basketballgamegallery.payload.response;

import java.util.List;

/**
 * Represents a JWT response.
 */
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> accessRights;

    /**
     * Constructs a JwtResponse object with the provided access token, user ID, username, email, and access rights.
     *
     * @param accessToken   the access token
     * @param id            the user ID
     * @param username      the username
     * @param email         the email
     * @param accessRights  the access rights
     */
    public JwtResponse(String accessToken, Long id, String username, String email, List<String> accessRights) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.accessRights = accessRights;
    }

    /**
     * Gets the access token from the JWT response.
     *
     * @return the access token
     */
    public String getAccessToken() {
        return token;
    }

    /**
     * Sets the access token in the JWT response.
     *
     * @param accessToken the access token
     */
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    /**
     * Gets the token type from the JWT response.
     *
     * @return the token type
     */
    public String getTokenType() {
        return type;
    }

    /**
     * Sets the token type in the JWT response.
     *
     * @param tokenType the token type
     */
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    /**
     * Gets the user ID from the JWT response.
     *
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user ID in the JWT response.
     *
     * @param id the user ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the email from the JWT response.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email in the JWT response.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the username from the JWT response.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username in the JWT response.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the access rights from the JWT response.
     *
     * @return the access rights
     */
    public List<String> getAccessRights() {
        return accessRights;
    }
}
