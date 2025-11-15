package com.store.bookstore.service;

import com.store.bookstore.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {
    private AuthService authUnderTest;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private AutoCloseable closeable;
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
//      authUnderTest=new AuthService(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void registerUser() {
    }

    @Disabled
    @Test
    void loginUser() {
    }
}