package com.example.inventoryback.services;

import com.example.inventoryback.exceptions.RoleNotFoundException;
import com.example.inventoryback.models.Role;
import com.example.inventoryback.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findById(Long id) throws RoleNotFoundException {
        return roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }

    public List<Role> findAll(){
        return this.roleRepository.findAll();
    }
}
