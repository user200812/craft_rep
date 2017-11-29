package ru.craftautoweb.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Администратор on 23.11.2016.
 */
public class CraftAuthority implements GrantedAuthority {
    int role = 1;

    public CraftAuthority(int role) {
        this.role = role;
    }

    public String getAuthority() {
        if(role == 1) return "ROLE_USER";
        if(role == 0) return "ROLE_ADM";

        return null;
    }
}
