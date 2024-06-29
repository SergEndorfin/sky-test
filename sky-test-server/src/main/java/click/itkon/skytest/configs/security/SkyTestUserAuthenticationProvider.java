package click.itkon.skytest.configs.security;

import click.itkon.skytest.exceptions.NotFoundException;
import click.itkon.skytest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SkyTestUserAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var usersEmail = authentication.getName();
        var password = authentication.getCredentials().toString();
        var user = userRepository.findByEmail(usersEmail)
                .orElseThrow(() -> new NotFoundException(usersEmail));
        if (passwordEncoder.matches(password, user.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
