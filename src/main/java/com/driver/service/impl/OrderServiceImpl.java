package com.driver.service.impl;

import com.driver.Converter.OrderConverter;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId()); // This line fails
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(orderEntity.getItems());
        orderEntity.setUserId(orderEntity.getUserId());
        orderEntity.setStatus(true);
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        return OrderConverter.convertEntityToDto(orderEntity);
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setStatus(order.isStatus());
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());

        OrderEntity updatedOrder = orderRepository.save(orderEntity);

        return OrderConverter.convertEntityToDto(updatedOrder);
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity order = orderRepository.findByOrderId(orderId);
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getOrders() {
        Iterable<OrderEntity> orderEntities = orderRepository.findAll();
        List<OrderDto> list = new ArrayList<>();
        for(OrderEntity order: orderEntities){
            list.add(OrderConverter.convertEntityToDto(order));
        }
        return list;
    }
}