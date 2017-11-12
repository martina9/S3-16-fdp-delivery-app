package com.fastdeliveryservice.controller;
import FDP.OrderService.MessageDirectory.Response.OrderInfo;
import com.fastdeliveryservice.service.OrderService;
import com.fastdeliveryservice.viewModel.CategoryViewModel;
import com.fastdeliveryservice.viewModel.OrderViewModel;
import com.fastdeliveryservice.viewModel.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static com.fastdeliveryservice.utility.Mapper.ConvertFromMessage;
import static com.fastdeliveryservice.utility.Mapper.convertList;

/**
 * Created by Martina Gabellini
 */

@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
            this.orderService = orderService;
    }

    @RequestMapping(value = "/orders/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderViewModel>> getOrdersByUserIdRpc(@PathVariable("userId") int userId){

        FDP.OrderService.MessageDirectory.Response.OrderList categoryResponse = orderService.GetOrdersList(userId,null);
        //String deliveryType, String address, String city, String phoneNumber, String email, int userId, double amount, Date confirmationDate
        List<OrderViewModel> viewModels  = convertList(categoryResponse.getItems(), s -> new OrderViewModel(s.getDeliveryType(),s.getAddress(),s.getCity(),s.getPhoneNumber(),s.getEmail(),s.getUserId(),s.getAmount(),s.getConfirmationDate()));

        if(viewModels == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.ok(viewModels);
        }
    }

    @RequestMapping(value = "/orders/restaurant/{restaurantId}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderViewModel>> getOrdersByRestaurantRpc(@PathVariable("restaurantId") int restaurantId){

        FDP.OrderService.MessageDirectory.Response.OrderList categoryResponse = orderService.GetOrdersList(null,restaurantId);
        //String deliveryType, String address, String city, String phoneNumber, String email, int userId, double amount, Date confirmationDate
        List<OrderViewModel> viewModels  = convertList(categoryResponse.getItems(), s -> new OrderViewModel(s.getDeliveryType(),s.getAddress(),s.getCity(),s.getPhoneNumber(),s.getEmail(),s.getUserId(),s.getAmount(),s.getConfirmationDate()));

        if(viewModels == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.ok(viewModels);
        }
    }

    @RequestMapping(value = "/orders/{Id}", method = RequestMethod.GET)
    public ResponseEntity<OrderViewModel> getOrderByIdRpc(@PathVariable("Id") int id) throws Exception {

        OrderInfo orderInfo = orderService.GetOrderById(id);
        OrderViewModel model = ConvertFromMessage(orderInfo);

        return ResponseEntity.status(HttpStatus.OK).body(model);
    }

    @RequestMapping(value = "/orders/user", method = RequestMethod.POST)
    public ResponseEntity<Void> addOrder(@RequestBody OrderViewModel order) {

      int id =  orderService.add(order.getUserId(),order.getAmount(),order.getConfirmationDate(), order.getDeliveryType(), order.getAddress(), order.getCity(), order.getPhoneNumber(), order.getEmail(),order.getRestaurantId());
      return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllOrders() {

     // TODO:   OrderRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}