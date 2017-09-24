package com.fastdeliveryservice.service;

import com.fastdeliveryservice.domain.OrderDto;

import java.util.List;

/**
 * Created by Martina Gabellini
 */

public interface IOrderService {

    List<OrderDto> getOrderByUserId(int userId);

    //List<Order> getProductsByRestaurant(int id);

    OrderDto getOrderById(int id);

    boolean add(OrderDto productDto);

    boolean update(OrderDto productDto);

    boolean delete(OrderDto productDto);
}