package com.store.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookStoreApplication {

    public static void main(String[] args) {

        SpringApplication.run(BookStoreApplication.class, args);
        System.out.print("Hello");
    }

}
