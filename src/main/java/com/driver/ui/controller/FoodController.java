package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.driver.Converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto = foodService.getFoodById(id);
		return FoodConverter.convertFoodDtoToResponseDto(foodDto);
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto = FoodConverter.convertRequestDtoToFoodDto(foodDetails);
		foodDto = foodService.createFood(foodDto);
		return FoodConverter.convertFoodDtoToResponseDto(foodDto);
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto = FoodConverter.convertRequestDtoToFoodDto(foodDetails);
		foodDto = foodService.updateFoodDetails(id, foodDto);
		return FoodConverter.convertFoodDtoToResponseDto(foodDto);
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		foodService.deleteFoodItem(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {

		List<FoodDto> list = foodService.getFoods();
		List<FoodDetailsResponse> foodList = new ArrayList<>();
		for(FoodDto foodDto: list){
			foodList.add(FoodConverter.convertFoodDtoToResponseDto(foodDto));
		}
		return  foodList;
	}
}
