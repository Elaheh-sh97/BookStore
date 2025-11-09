package com.store.bookstore.service;

import com.store.bookstore.config.JwtService;
import com.store.bookstore.dto.AuthResponse;
import com.store.bookstore.dto.SignInRequestdto;
import com.store.bookstore.dto.SignUpRequestdto;
import com.store.bookstore.dto.SignUpResponsedto;
import com.store.bookstore.model.RoleName;
import com.store.bookstore.model.Roles;
import com.store.bookstore.model.Users;
import com.store.bookstore.repository.RoleRepository;
import com.store.bookstore.repository.UserRepository;
import com.store.bookstore.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public SignUpResponsedto registerUser(SignUpRequestdto userdto) {
        if (userRepository.existsByEmail(userdto.getEmail())) {

        }
        Users users = new Users();
        users.setFirstname(userdto.getFirstname());
        users.setLastname(userdto.getLastname());
        String hashpassword = passwordEncoder.encode(userdto.getPassword());
        users.setPassword(hashpassword);
        users.setEmail(userdto.getEmail());
        Set<Roles> roles = new HashSet<>();
        for (String roleName : userdto.getRoles()) {
            RoleName rn = RoleName.valueOf(roleName);
            Roles role = roleRepository.findByname(rn);
            roles.add(role);
        }
        users.setRoles(roles);
        userRepository.save(users);
        SignUpResponsedto signUpResponsedto = new SignUpResponsedto(
                users.getId(), users.getEmail(),
                users.getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toSet())
        );
        return signUpResponsedto;
    }

    public AuthResponse loginUser(SignInRequestdto userdto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdto.getEmail(), userdto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        SignUpResponsedto signUpResponsedto = new SignUpResponsedto(userDetails.getId(),
                userDetails.getEmail(), userDetails.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()));
        String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse("User Login Successfully", token, signUpResponsedto);

        return authResponse;
    }


}
