package ru.craftautoweb.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.craftautoweb.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class CraftAuthenticationProvider  implements AuthenticationProvider {
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserEntity user = UserEntity.IsValid(username, password);
        if(user != null) {
            GrantedAuthority authority = new CraftAuthority(user.getRole());
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(authority);
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(
                            username, password, authorities);
            return token;
        }

        return null;
    }

    public boolean supports(Class<?> aClass) {
        return true;
        //return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
