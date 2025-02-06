package com.example.UserService.Repository;

import com.example.UserService.Model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRespository extends MongoRepository<Role, String> {
}
