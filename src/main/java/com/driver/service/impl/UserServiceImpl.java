package com.driver.service.impl;

import com.driver.Converter.UserConverter;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId()); // This line fails
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity = userRepository.save(userEntity);
        return UserConverter.convertEntityToDto(userEntity);
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);
        return UserConverter.convertEntityToDto(userEntity);
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        return UserConverter.convertEntityToDto(userEntity);
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userEntity.setUserId(user.getUserId());
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        return UserConverter.convertEntityToDto(userEntity);
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntities= (List<UserEntity>) userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(UserEntity userEntity: userEntities){
            userDtoList.add(UserConverter.convertEntityToDto(userEntity));
        }
        return userDtoList;
    }
}