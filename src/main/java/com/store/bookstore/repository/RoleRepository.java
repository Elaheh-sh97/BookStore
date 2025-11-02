package com.store.bookstore.repository;

import com.store.bookstore.model.RoleName;
import com.store.bookstore.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Roles findByname(RoleName name);
}
