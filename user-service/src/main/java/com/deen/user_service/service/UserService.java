package com.deen.user_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deen.user_service.dto.UserRequest;
import com.deen.user_service.dto.UserResponse;
import com.deen.user_service.entity.User;
import com.deen.user_service.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
  
  private final UserRepository userRepository;

  //CREATE NEW USERS
  public UserResponse createUser(UserRequest userRequest){
    User user = new User();
    user.setName(userRequest.getName());
    user.setEmail(userRequest.getEmail());
    user.setPhone(userRequest.getPhone());

    User saved=userRepository.save(user);

    return new UserResponse(
      saved.getUser_id(),
      saved.getName(),
      saved.getEmail(),
      saved.getPhone()
    );
  }

//GET USER BY USER_ID
  public UserResponse getUserById(Long user_id){
    User user = userRepository.findById(user_id)
            .orElseThrow(() -> new RuntimeException("User id not found"));
  
    return new UserResponse(
      user.getUser_id(),
      user.getName(),
      user.getEmail(),
      user.getPhone()   
     );
  }

  //GET ALL EXISTING USERS
  public List<UserResponse> getAllUsers(){
    return userRepository.findAll().stream()
            .map(user -> new UserResponse(
              user.getUser_id(),
              user.getName(),
              user.getEmail(),
              user.getPhone()
            ))
            .toList();
  }

  //UPDATE USER USING USER_ID
  public UserResponse updateUser(Long user_id,UserRequest userRequest){
    User user=userRepository.findById(user_id)
            .orElseThrow(()-> new RuntimeException("User not found"));

    user.setName(userRequest.getName());
    user.setEmail(userRequest.getEmail());
    user.setPhone(userRequest.getPhone());

    User updated=userRepository.save(user);

    return new UserResponse(
      updated.getUser_id(),
      updated.getName(),
      updated.getEmail(),
      updated.getPhone()
    );
  }

  public void deleteUser(Long user_id){
      userRepository.deleteById(user_id);
    }
}
