package com.store.bookstore.controller;
import com.store.bookstore.dto.AuthResponse;
import com.store.bookstore.dto.SignInRequestdto;
import com.store.bookstore.dto.SignUpRequestdto;
import com.store.bookstore.dto.SignUpResponsedto;
import com.store.bookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookstore")
public class StoreController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
public ResponseEntity<AuthResponse<SignUpResponsedto>> signUpUser(@RequestBody SignUpRequestdto userDTO){
        SignUpResponsedto signUpResponsedto = authService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("User Registered Successfully", signUpResponsedto));
}
@PostMapping("/signin")
public ResponseEntity<AuthResponse<SignUpResponsedto>> SignInUser(@RequestBody SignInRequestdto userDTO){
       SignUpResponsedto signUpResponsedto=authService.loginUser(userDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("User Login Successfully", signUpResponsedto));
}


}
