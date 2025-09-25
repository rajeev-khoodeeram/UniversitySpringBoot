package ca.cloudace.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final TokenBlackListService tokenBlacklistService;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtUtil jwtUtil,
            TokenBlackListService tokenBlacklistService) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    // Expose AuthenticationManager so it can be injected into AuthController
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // JwtFilter jwtFilter = new JwtFilter(jwtUtil, userDetailsService,
    // tokenBlacklistService);
    // // http
    // // .cors().and().csrf().disable()
    // //
    // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    // // .and()
    // // .authorizeHttpRequests()
    // // .requestMatchers("/api/auth/**").permitAll()
    // // .anyRequest().authenticated();

    // http
    // .cors() // enable CORS
    // .and()
    // .csrf(csrf -> csrf.disable())
    // .authorizeHttpRequests(auth -> auth
    // .requestMatchers("/api/auth/**").permitAll()
    // .anyRequest().authenticated());

    // // return http.build();
    // // http.addFilterBefore(jwtFilter,
    // UsernamePasswordAuthenticationFilter.class);
    // return http.build();
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf(csrf -> csrf.disable()) // disable CSRF for REST APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/**").permitAll() // Public endpoints
                        .anyRequest().authenticated());

        http.exceptionHandling()
                .authenticationEntryPoint((req, res, authException) -> {
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                })
                .and()
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        JwtFilter jwtFilter = new JwtFilter(jwtUtil, userDetailsService, tokenBlacklistService);
        http.addFilterBefore(jwtFilter,
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
