package com.store.bookstore.service;

import com.store.bookstore.dto.RequestUserdto;
import com.store.bookstore.dto.ResponseUserdto;
import com.store.bookstore.model.RoleName;
import com.store.bookstore.model.Roles;
import com.store.bookstore.model.Users;
import com.store.bookstore.repository.RoleRepository;
import com.store.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void registerUser(RequestUserdto userdto){
      if(userRepository.existsByEmail(userdto.getEmail())){

      }
          Users users=new Users();
          users.setFirstname(userdto.getFirstname());
          users.setLastname(userdto.getLastname());
          String hashpassword=passwordEncoder.encode(userdto.getPassword());
          users.setPassword(hashpassword);
          users.setEmail(userdto.getEmail());
          Set<Roles> roles=new HashSet<>();
          for(String roleName:userdto.getRoles()){
              RoleName rn = RoleName.valueOf(roleName);
              Roles role= roleRepository.findByname(rn);
              roles.add(role);
          }
       users.setRoles(roles);
 userRepository.save(users);
//        ResponseUserdto responseUserdto=new ResponseUserdto(
//                users.getId(),users.getEmail()
//        );

    }
}
