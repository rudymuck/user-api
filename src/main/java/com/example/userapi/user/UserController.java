package com.example.userapi.user;

import com.example.userapi.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/{login}")
    public ResponseEntity<UserDto> findByUuid(@PathVariable String login) {
        return ResponseEntity.ok(userFacade.findByLogin(login));
    }
}
