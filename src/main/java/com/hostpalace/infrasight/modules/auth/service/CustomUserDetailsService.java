package com.hostpalace.infrasight.modules.auth.service;

import com.hostpalace.infrasight.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor; // <--- Import this
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; // <--- Import this

@Service // <--- Missing: Registers this as a Spring Bean
@RequiredArgsConstructor // <--- Missing: Injects the repository automatically
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository; // <--- Inject the instance

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Use the instance 'userRepository', NOT the class 'UserRepository'
        var user = userRepository.findByEmail(email) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPasswordHash())
                .roles(user.getRole().name())
                .build();
    }
}