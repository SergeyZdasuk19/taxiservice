package taxiservice.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, TAXIDRIVER;

    @Override
    public String getAuthority() {
        return name();
    }
}
