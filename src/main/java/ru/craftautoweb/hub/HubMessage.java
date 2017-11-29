package ru.craftautoweb.hub;

/**
 * Created by User on 04.12.2016.
 */
public class HubMessage {
    private String message;

    public HubMessage() {
        message = "";
    }

    public HubMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
