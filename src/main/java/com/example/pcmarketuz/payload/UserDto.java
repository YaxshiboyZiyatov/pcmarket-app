package com.example.pcmarketuz.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    private String fullName;

    @NotNull(message = "Bo'sh bolmasin")
    private String email;

    private String password;

    private Integer attachmentId;
}
