package com.example.UserService.Service;

import com.example.UserService.Dto.Request.UserRequest;
import com.example.UserService.Dto.Request.UserUpdateRequest;
import com.example.UserService.Dto.Response.UserResponse;
import com.example.UserService.Mapper.UserMapper;
import com.example.UserService.Model.User;
import com.example.UserService.Repository.UserRepository;
import com.example.UserService.base_exception.AppException;
import com.example.UserService.base_exception.ErrorCode;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthService authService;
    public User findById(String id){
        return userRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.User_NOT_FOUND));
    }
    public List<User> findAll(String token) throws ParseException, JOSEException {
        if(token==null || token.isEmpty())
            throw new AppException(ErrorCode.Verify_Failed);
        if(!authService.introspect(token))
            throw new AppException(ErrorCode.Verify_Failed);
        return userRepository.findAll();
    }
    private UserResponse maptoUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFullName(user.getFullName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        return userResponse;
    }
    public UserResponse CreateUser(UserRequest userRequest) {
        User newUser = userMapper.toUser((UserRequest) userRequest);
        if(userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new AppException(ErrorCode.Email_Already_Exist);
        }
        if(userRepository.findByphoneNumber(newUser.getPhoneNumber()).isPresent()) {
            throw new AppException(ErrorCode.Phone_Already_Exist);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        if(userRequest.getRole()==null)
             newUser.setRole("user");
        else
            newUser.setRole(userRequest.getRole());
        newUser.setAccountStatus("Active");
        newUser.setCreatedAt(new Date());
        newUser.setUpdatedAt(new Date());
        userRepository.save(newUser);
        UserResponse userResponse = maptoUserResponse(newUser);
        return userResponse;
    }
    public User updateUser(String id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.User_NOT_FOUND));
        userMapper.updateUser(user,userUpdateRequest);
        user.setUpdatedAt(new Date());
        userRepository.save(user);
        return user;
    }
    public String deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.User_NOT_FOUND));
        userRepository.delete(user);
        return  "Delete user: "+user.getUserId()+" Successfully";
    }
    public User findUserByToken(String token){
        String email = authService.getEmailFromToken(token);
        return userRepository.findByEmail(email).orElseThrow(()->new AppException(ErrorCode.User_NOT_FOUND));
    }

}
