package ru.craftautoweb.models;

import org.springframework.stereotype.Component;

/**
 * Created by Администратор on 17.11.2016.
 */
@Component
public class LoginUser {
    public String Username;
    public String Password;

    public boolean isValid() {
        return true;
    }
}
