package com.deen.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UserRequest {

  @NotBlank(message="User name can not be blank")
  private String name;

  @Email(message="Email is not vaild")
  private String email;

  @NotNull(message="Phone numbers can not be null")
  @Positive(message="Phone nummber can not ne negative")
  private String phone;
}
