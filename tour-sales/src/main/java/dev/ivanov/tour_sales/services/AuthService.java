package dev.ivanov.tour_sales.services;

import dev.ivanov.tour_sales.dto.auth.LoginDto;
import dev.ivanov.tour_sales.dto.auth.RegisterDto;
import dev.ivanov.tour_sales.entities.User;
import dev.ivanov.tour_sales.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    @Transactional
    public String register(RegisterDto registerDto) {
        User user = User.builder()
                .username(registerDto.getUsername())
                .password(registerDto.getPassword())
                .build();
        User savedUser = userRepository.save(user);

        log.info("User {} registered", user.getUsername());
        return jwtUtils.generate(user, registerDto.getRoles());
    }

    @Transactional
    public String login(LoginDto loginDto) {

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword());

        authenticationManager.authenticate(upat);

        String id = idService.generate();

        userRepository.createUser(id,
                loginDto.getUsername(),
                passwordEncoder.encode(loginDto.getPassword()),
                null,
                null,
                null,
                null,
                null);

        User user = userRepository.getUserById(id);
        return jwtUtils.generate(user, loginDto.getRoles());


    }


}
