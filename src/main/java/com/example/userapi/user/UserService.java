package com.example.userapi.user;

import com.example.userapi.user.dto.ResponseUserDto;
import com.example.userapi.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
class UserService {

    private static final String USERS = "/users";

    private final String baseUrl;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    UserService(@Value("${github.api.path}") String baseUrl, RestTemplate restTemplate, UserRepository userRepository) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }


    UserDto findByLogin(String login) {
        ResponseUserDto responseUserDto = fetchDataFromApi(login);
        BigDecimal calculations = calculate(responseUserDto);
        storeServiceRequestData(login);
        return createResponse(responseUserDto, calculations);
    }

    private UserDto createResponse(ResponseUserDto responseUserDto, BigDecimal calculations) {
        log.info("Create response data for User with login: {}", responseUserDto.getLogin());
        return UserDto.builder()
                .id(responseUserDto.getId())
                .login(responseUserDto.getLogin())
                .name(responseUserDto.getName())
                .type(responseUserDto.getType())
                .avatarUrl(responseUserDto.getAvatar_url())
                .createdAt(responseUserDto.getCreated_at().toString())
                .calculations(calculations.toString())
                .build();
    }

    private void storeServiceRequestData(String login) {
        log.info("Update UserRequest for login: {}", login);
        UserRequest userRequest = userRepository.findByLogin(login).orElse(null);
        if (Objects.nonNull(userRequest)) {
            userRequest.increaseRequestCount();
        } else {
            UserRequest newUserRequest = UserRequest.builder().login(login).requestCount(1).build();
            userRepository.save(newUserRequest);
            log.info("UserRequest created and saved successfully with login: {}", login);
        }
    }

    // the result is dependent from precision
    private BigDecimal calculate(ResponseUserDto responseUserDto) {
        try {
            return new BigDecimal(6).divide(responseUserDto.getFollowers(), 20, RoundingMode.HALF_EVEN)
                    .multiply(new BigDecimal(2).add(responseUserDto.getPublic_repos()));
        } catch (ArithmeticException e) {
            throw new RuntimeException(e);
        }
    }

    private ResponseUserDto fetchDataFromApi(String login) {
        Map<String, Object> urlParams = Map.of("login", login);
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl).path(USERS + "/{login}").buildAndExpand(urlParams).toUriString();
        try {
            return restTemplate.getForObject(url, ResponseUserDto.class);
        } catch (Exception e) {
            log.error("Problem with github api integration occurred {}", e);
            throw new RuntimeException(e);
        }
    }
}
