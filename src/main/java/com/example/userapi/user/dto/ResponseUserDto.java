package com.example.userapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDto extends BaseUserDto {

    private String avatar_url;
    private LocalDateTime created_at;
    private BigDecimal public_repos;
    private BigDecimal followers;

}
