package com.store.bookstore.controller;


import com.store.bookstore.dto.RequestUserdto;
import com.store.bookstore.service.AuthService;
import com.store.bookstore.service.CustomeUserDetailsService;
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
public ResponseEntity<String> registerUser(@RequestBody RequestUserdto userDTO){
        authService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully");
}






}
