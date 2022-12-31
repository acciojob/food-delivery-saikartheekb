package com.driver.Converter;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

    public static UserDto convertRequestDtoToUserDto(UserDetailsRequestModel userDetails) {
        return UserDto.builder()
                .email(userDetails.getEmail())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .build();
    }

    public static UserResponse convertUserDtoToResponseDto(UserDto userDto) {
        return UserResponse.builder()
                .userId(userDto.getUserId())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }

    public static UserDto convertEntityToDto(UserEntity user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}