package com.fastdeliveryservice.service;


import FDP.OrderService.MessageDirectory.Shared.Product;

import java.util.List;

/**
 * Created by Martina Gabellini
 */

public interface OrderService {

    FDP.OrderService.MessageDirectory.Response.OrderList GetOrdersList(Integer userId, Integer restaurantId);

    FDP.OrderService.MessageDirectory.Response.OrderInfo GetOrderById(Integer orderId) throws Exception;

    int add(int userId, double amount, String deliveryType, String address, String city, String phoneNumber, String email, int restaurantId, List<Product> products);

}