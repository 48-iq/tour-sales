package dev.ivanov.tour_sales.security;

import dev.ivanov.tour_sales.entities.Role;
import dev.ivanov.tour_sales.entities.User;
import dev.ivanov.tour_sales.repositories.RoleRepository;
import dev.ivanov.tour_sales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));

        List<Role> roles = roleRepository.getRolesByUserId(user.getId());

        return new UserDetailsImpl(user, roles);
    }
}
