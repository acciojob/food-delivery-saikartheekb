package com.driver.service.impl;

import com.driver.Converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodId(food.getFoodName()+food.getFoodCategory()+food.getFoodPrice()); // This line fails
        foodEntity.setFoodName(food.getFoodName());
        foodEntity.setFoodPrice(food.getFoodPrice());
        foodEntity.setFoodCategory(food.getFoodCategory());
        FoodEntity savedFood = foodRepository.save(foodEntity);
        return FoodConverter.convertEntityToDto(savedFood);
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity food = foodRepository.findByFoodId(foodId);
        return FoodConverter.convertEntityToDto(food);
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        foodEntity.setFoodId(foodDetails.getFoodId());
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());

        FoodEntity updatedFood = foodRepository.save(foodEntity);

        return FoodConverter.convertEntityToDto(updatedFood);
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity food = foodRepository.findByFoodId(id);
        foodRepository.delete(food);
    }

    @Override
    public List<FoodDto> getFoods() {
        Iterable<FoodEntity> foodEntities = foodRepository.findAll();
        List<FoodDto> list = new ArrayList<>();
        for(FoodEntity food: foodEntities){
            list.add(FoodConverter.convertEntityToDto(food));
        }
        return list;
    }
}