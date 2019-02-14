package hu.eronix.bookstore.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserContext {

    @Getter
    @Setter
    private final String username;

    @Getter
    @Setter
    private final List<GrantedAuthority> authorities;

    private UserContext(String username, List<GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public static UserContext create(String username, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, authorities);
    }
}
