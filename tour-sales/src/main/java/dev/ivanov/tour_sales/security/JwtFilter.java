package dev.ivanov.tour_sales.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import dev.ivanov.tour_sales.entities.Role;
import dev.ivanov.tour_sales.entities.User;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.RoleRepository;
import dev.ivanov.tour_sales.repositories.UserRepository;
import dev.ivanov.tour_sales.services.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwt = authHeader.substring(7);

                Map<String, Claim> claims = jwtUtils.verifyAndRetrieveClaims(jwt);
                String userId = claims.get("id").asString();

                User user = userRepository.getUserById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));

                List<Role> roles = roleRepository.getRolesByUserId(user.getId());

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(new UserDetailsImpl(user, roles), null);
                if (SecurityContextHolder.getContext().getAuthentication() == null ||
                        !SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("principal: ");
                System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            }
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            response.getWriter().write("jwt verification error");
        } catch (AuthenticationException | EntityNotFoundException e) {
            response.getWriter().write("authentication error");
        }

    }
}
