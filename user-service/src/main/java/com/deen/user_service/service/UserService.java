package com.deen.user_service.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.deen.user_service.dto.UserRequest;
import com.deen.user_service.dto.UserResponse;
import com.deen.user_service.entity.User;
import com.deen.user_service.exception.UserNotFoundException;
import com.deen.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
  private  static final Logger log=LoggerFactory.getLogger(UserService.class);
  private final UserRepository userRepository;

  //CREATE NEW USERS
  public UserResponse createUser(UserRequest userRequest){
    log.info("Creating user with name: {} ",userRequest.getName());
    User user = new User();
    user.setName(userRequest.getName());
    user.setEmail(userRequest.getEmail());
    user.setPhone(userRequest.getPhone());

    User saved=userRepository.save(user);

    log.info("User created successfully with user id: {}",saved.getUser_id());
    return new UserResponse(
      saved.getUser_id(),
      saved.getName(),
      saved.getEmail(),
      saved.getPhone()
    );
  }

//GET USER BY USER_ID
  public UserResponse getUserById(Long user_id){
    log.info("Fetching user with user id: {}",user_id);
    User user = userRepository.findById(user_id)
            .orElseThrow(() -> new UserNotFoundException("User id not found with "+user_id));
  
    return new UserResponse(
      user.getUser_id(),
      user.getName(),
      user.getEmail(),
      user.getPhone()   
     );
  }

  //GET ALL EXISTING USERS
  public List<UserResponse> getAllUsers(){
     log.info("Fetching all the users");
     List<UserResponse> allUser = userRepository.findAll().stream()
            .map(user -> new UserResponse(
              user.getUser_id(),
              user.getName(),
              user.getEmail(),
              user.getPhone()
            ))
            .toList();
      log.info("Fetched all user successfully with the count: {}",allUser.size());
      return allUser;
  }

  //UPDATE USER USING USER_ID
  public UserResponse updateUser(Long user_id,UserRequest userRequest){
    log.info("Updating user with user id: {}",user_id);
    User user=userRepository.findById(user_id)
            .orElseThrow(()-> new UserNotFoundException("User id not found with "+user_id));

    user.setName(userRequest.getName());
    user.setEmail(userRequest.getEmail());
    user.setPhone(userRequest.getPhone());

    User updated=userRepository.save(user);
    log.info("Updated user succesfully with user id: {}",user_id);
    return new UserResponse(
      updated.getUser_id(),
      updated.getName(),
      updated.getEmail(),
      updated.getPhone()
    );
  }

  public void deleteUser(Long user_id){
      log.info("Deleting user with user id: {}",user_id);
      userRepository.deleteById(user_id);
      log.info("Deleted user successfully with user id: {}",user_id);
    }
}
