package com.deen.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
  
  private Long userId;
  private String name;
  private String email;
  private String phone;
}
