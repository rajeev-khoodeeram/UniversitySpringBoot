package ca.cloudace.backend.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.cloudace.backend.model.Login;
import ca.cloudace.backend.repository.LoginRepository;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private LoginRepository loginRepository;

        @Override
        public UserDetails loadUserByUsername(String username)
                        throws UsernameNotFoundException {

                Optional<Login> loginOpt = loginRepository.findByUsername(username);
                Login user = loginOpt.orElseThrow(() -> new UsernameNotFoundException("User not found"));
                System.out.println("UserDetails password: " + user.getPasswordHash());
                System.out.println("Raw password: " + username);
                // Use Spring's built-in User builder to map DB entity to UserDetails
                // return org.springframework.security.core.userdetails.User
                // .withUsername(user.getUsername())
                // .password(user.getPasswordHash()) // Must be encoded (bcrypt)
                // .authorities(user.getRole())
                // .build();
                return User.builder()
                                .username(user.getUsername())
                                .password(user.getPasswordHash()) // BCrypt hashed
                                .roles(user.getRole()) // e.g., "USER"
                                .build();

        }
}
