package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.auth.LoginDto;
import dev.ivanov.tour_sales.dto.auth.RegisterDto;
import dev.ivanov.tour_sales.entities.Role;
import dev.ivanov.tour_sales.entities.User;
import dev.ivanov.tour_sales.exceptions.EntityNotFoundException;
import dev.ivanov.tour_sales.repositories.RoleRepository;
import dev.ivanov.tour_sales.repositories.UserRepository;
import dev.ivanov.tour_sales.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdService idService;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${app.admin-password}")
    private String adminPassword;

    @Transactional
    public String register(RegisterDto registerDto) {

        String id = idService.generate();

        userRepository.createUser(id,
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                null,
                null,
                null,
                null,
                null);

        User user = userRepository.getUserById(id).get();
        List<Role> roles = roleRepository.getRolesByUserId(id);
        log.info("User {} registered", user.getUsername());
        return jwtUtils.generate(user, roles.stream().map(Role::getName).toList());
    }

    @Transactional
    public String login(LoginDto loginDto) {

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword());

        authenticationManager.authenticate(upat);

        User user = userRepository.getUserByUsername(loginDto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User with username " + loginDto.getUsername() + " not found"));
        List<Role> roles = roleRepository.getRolesByUserId(user.getId());

        return jwtUtils.generate(user, roles.stream().map(Role::getName).toList());


    }


    public String becomeAdmin(String adminPassword) {
        if (this.adminPassword.equals(adminPassword)) {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            User user = userDetails.getUser();
            roleRepository.setRoleAdminToUser(user.getId());
            return jwtUtils.generate(user, List.of("ROLE_USER", "ROLE_ADMIN"));
        } else {
            throw new IllegalArgumentException("Wrong admin password");
        }
    }
}
