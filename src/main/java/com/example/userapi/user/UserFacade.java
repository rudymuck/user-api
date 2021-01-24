package com.example.userapi.user;

import com.example.userapi.common.Facade;
import com.example.userapi.user.dto.UserDto;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public UserDto findByLogin(String login) {
        UserDto userDto = userService.findByLogin(login);
        return userDto;
    }
}
