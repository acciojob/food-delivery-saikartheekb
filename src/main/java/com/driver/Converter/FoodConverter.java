package com.driver.Converter;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FoodConverter {

    public static FoodDto convertEntityToDto(FoodEntity food){
        return FoodDto.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .foodCategory(food.getFoodCategory()).build();
    }

    public static FoodDetailsResponse convertFoodDtoToResponseDto(FoodDto foodDto){
        return FoodDetailsResponse.builder()
                .foodId(foodDto.getFoodId())
                .foodName(foodDto.getFoodName())
                .foodPrice(foodDto.getFoodPrice())
                .foodCategory(foodDto.getFoodCategory()).build();
    }

    public static FoodDto convertRequestDtoToFoodDto(FoodDetailsRequestModel foodDetails){
        return FoodDto.builder()
                .foodName(foodDetails.getFoodName())
                .foodCategory(foodDetails.getFoodCategory())
                .foodPrice(foodDetails.getFoodPrice())
                .build();
    }
}
