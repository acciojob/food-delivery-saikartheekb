package com.driver.Converter;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {

    public static OrderDto convertEntityToDto(OrderEntity orderEntity){
        return OrderDto.builder()
                .id(orderEntity.getId())
                .orderId(orderEntity.getOrderId())
                .cost(orderEntity.getCost())
                .items(orderEntity.getItems())
                .userId(orderEntity.getUserId())
                .status(orderEntity.isStatus())
                .build();
    }

    public static OrderDto convertRequestDtoToOrderDto(OrderDetailsRequestModel order){
        return OrderDto.builder()
                .items(order.getItems())
                .cost(order.getCost())
                .userId(order.getUserId())
                .build();
    }

    public static OrderDetailsResponse convertOrderDtoToResponseDto(OrderDto orderDto){
        return OrderDetailsResponse.builder()
                .orderId(orderDto.getOrderId())
                .cost(orderDto.getCost())
                .userId(orderDto.getUserId())
                .items(orderDto.getItems()).build();
    }
}
