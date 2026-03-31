package com.deen.user_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deen.user_service.dto.UserRequest;
import com.deen.user_service.dto.UserResponse;
import com.deen.user_service.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  
  private final UserService userService;

  @PostMapping("/create")
  public UserResponse createUser(@RequestBody UserRequest userRequest){
    return userService.createUser(userRequest);
  }

  @GetMapping("/{user_id}")
  public UserResponse getUser(@PathVariable Long user_id){
    return userService.getUserById(user_id);
  }

  @GetMapping()
  public List<UserResponse> getAllUsers(){
    return userService.getAllUsers();
  }

  @PutMapping("/{user_id}")
  public UserResponse updateUser(@PathVariable Long user_id,@RequestBody UserRequest userRequest){
    return userService.updateUser(user_id,userRequest);
  }

  @DeleteMapping("{user_id}")
  public void deleteUser(@PathVariable Long user_id){
    userService.deleteUser(user_id);
  }
}
