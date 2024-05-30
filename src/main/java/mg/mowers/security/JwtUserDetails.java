package mg.mowers.security;

import mg.mowers.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class JwtUserDetails implements UserDetails {

    private final User user;

    public JwtUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Long roleId = user.getRoleId(); // Получаем айди роли пользователя
        if (roleId != null) {
            if (roleId == 1) {
                return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")); // Пользователь с ролью "USER"
            } else if (roleId == 2) {
                return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")); // Администратор с ролью "ADMIN"
            }
        }
        return Collections.emptyList(); // Возвращаем пустую коллекцию, если роль не определена или не существует
    }
    
    

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
