package com.store.bookstore.controller;

import com.store.bookstore.dto.AuthResponse;
import com.store.bookstore.dto.SignInRequestdto;
import com.store.bookstore.dto.SignUpRequestdto;
import com.store.bookstore.dto.SignUpResponsedto;
import com.store.bookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bookstore")
public class StoreController {
    @Autowired
    AuthService authService;

    @GetMapping("/")
    public ResponseEntity<?> home(){
        Map<String,String> response=Map.of(
                "message","Api is running",
                  "version","1.0.0"
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse<SignUpResponsedto>> signUpUser(@RequestBody SignUpRequestdto userDTO) {
        SignUpResponsedto signUpResponsedto = authService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("User Registered Successfully", null, signUpResponsedto));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> SignInUser(@RequestBody SignInRequestdto userDTO) {
        AuthResponse authResponse = authService.loginUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }

    @PostMapping("/signout")
    public ResponseEntity<String> SignOutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.OK).body("User Signed Out Successfully");
    }
}
