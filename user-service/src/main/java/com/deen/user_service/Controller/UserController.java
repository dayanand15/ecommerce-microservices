package com.deen.user_service.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deen.user_service.dto.ApiResponse;
import com.deen.user_service.dto.UserRequest;
import com.deen.user_service.dto.UserResponse;
import com.deen.user_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  
  private final UserService userService;

  @PostMapping("/create")
  public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest){
     UserResponse userResponse=userService.createUser(userRequest);

     return new ApiResponse<UserResponse>
     (LocalDateTime.now(),
      201,
      "User created successfully",
      userResponse
    );
  }

  @GetMapping("/{user_id}")
  public ApiResponse<UserResponse> getUser(@PathVariable Long user_id){
    UserResponse userResponse= userService.getUserById(user_id);

    return new ApiResponse<UserResponse>
     (LocalDateTime.now(),
      200,
      "User fetched successfully",
      userResponse
    );
  }

  @GetMapping()
  public ApiResponse<List<UserResponse>> getAllUsers(){
    List<UserResponse> userResponse= userService.getAllUsers();

    return new ApiResponse<List<UserResponse>>
     (LocalDateTime.now(),
      200,
      "User fetched successfully",
      userResponse
    );
  }

  @PutMapping("/{user_id}")
  public ApiResponse<UserResponse> updateUser(@PathVariable Long user_id,@Valid @RequestBody UserRequest userRequest){
    UserResponse userResponse= userService.updateUser(user_id,userRequest);

     return new ApiResponse<UserResponse>
     (LocalDateTime.now(),
      200,
      "User updated successfully",
      userResponse
    );
  }

  @DeleteMapping("{user_id}")
  public ApiResponse<String> deleteUser(@PathVariable Long user_id){
    userService.deleteUser(user_id);

     return new ApiResponse<String>
     (LocalDateTime.now(),
      200,
      "User deleted successfully",
      "User deleted successfully"
    );
  }
}
