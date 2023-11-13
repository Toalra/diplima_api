package models;

import lombok.Data;

@Data
public class LoginUserRequestModel {
   private String email, password;
}
