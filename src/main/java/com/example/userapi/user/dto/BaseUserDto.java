package com.example.userapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserDto {

    private Long id;
    private String login;
    private String name;
    private String type;

}
