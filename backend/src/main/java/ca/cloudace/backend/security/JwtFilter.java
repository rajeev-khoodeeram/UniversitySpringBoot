package ca.cloudace.backend.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final TokenBlackListService tokenBlacklistService;

    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService,
            TokenBlackListService tokenBlacklistService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    // constructor
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        if (path.startsWith("/api/auth/")) {
            // Skip JWT validation for login/register
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (tokenBlacklistService.isBlacklisted(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}