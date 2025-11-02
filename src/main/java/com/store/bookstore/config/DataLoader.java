package com.store.bookstore.config;

import com.store.bookstore.model.RoleName;
import com.store.bookstore.model.Roles;
import com.store.bookstore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
if(roleRepository.findAll().size()==0){
        for(RoleName rn:RoleName.values()){
            Roles roles=new Roles();
         roles.setName(rn);
            roleRepository.save(roles);
        }
    }
    }
}
