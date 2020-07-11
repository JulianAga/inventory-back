package com.example.inventoryback.dto;

import com.example.inventoryback.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    private String mail;

    private String password;

    private String name;

    private String cellphone;

    private String address;

    private Long role;


}
