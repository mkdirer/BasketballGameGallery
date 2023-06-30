package project.basketballgamegallery.payload.response;

/**
 * Represents a response message.
 */
public class MessageResponse {

    private String message;

    /**
     * Constructs a MessageResponse object with the provided message.
     *
     * @param message the response message
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     * Gets the response message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the response message.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}