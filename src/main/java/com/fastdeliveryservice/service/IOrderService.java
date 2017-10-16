package com.fastdeliveryservice.service;

import java.util.Date;

/**
 * Created by Martina Gabellini
 */

public interface IOrderService {

    FDP.OrderService.MessageDirectory.Response.OrderList GetOrdersList(Integer userId, Integer restaurantId);

    FDP.OrderService.MessageDirectory.Response.OrderInfo GetOrderById(Integer orderId) throws Exception;

    int add(int userId, double amount, Date confirmationDate, String deliveryType, String address, String city, String phoneNumber, String email, int restaurantId);

}