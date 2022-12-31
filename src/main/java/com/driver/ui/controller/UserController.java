package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.driver.Converter.UserConverter;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto userDto = userService.getUserByUserId(id);
		return UserConverter.convertUserDtoToResponseDto(userDto);
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = UserConverter.convertRequestDtoToUserDto(userDetails);
		userDto =  userService.createUser(userDto);
		return UserConverter.convertUserDtoToResponseDto(userDto);
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = UserConverter.convertRequestDtoToUserDto(userDetails);
		userDto = userService.updateUser(id,userDto);
		return UserConverter.convertUserDtoToResponseDto(userDto);
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
		userService.deleteUser(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
		List<UserDto> userDtoList= userService.getUsers();
		List<UserResponse> userResponseList = new ArrayList<>();
		for(UserDto userDto: userDtoList){
			userResponseList.add(UserConverter.convertUserDtoToResponseDto(userDto));
		}
		return userResponseList;
	}
	
}
