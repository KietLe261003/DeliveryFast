package com.example.UserService.Service;

import com.example.UserService.Model.Role;
import com.example.UserService.Repository.RoleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRespository roleRespository;

    public List<Role> findAll() {
        return roleRespository.findAll();
    }
}
