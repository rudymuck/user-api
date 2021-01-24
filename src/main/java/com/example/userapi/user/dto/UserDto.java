package com.example.userapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseUserDto {

    private String avatarUrl;
    private String createdAt;
    private String calculations;

    @Builder
    public UserDto(Long id, String login, String name, String type, String avatarUrl, String createdAt, String calculations) {
        super(id, login, name, type);
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.calculations = calculations;
    }
}
