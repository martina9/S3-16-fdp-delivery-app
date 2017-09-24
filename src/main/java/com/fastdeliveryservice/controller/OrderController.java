package com.fastdeliveryservice.controller;

import com.fastdeliveryservice.domain.OrderDto;
import com.fastdeliveryservice.domain.ProductRestaurantDto;
import com.fastdeliveryservice.service.OrderService;
import com.fastdeliveryservice.viewModel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

/**
 * Created by Martina Gabellini
 */

@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
            this.orderService = orderService;
    }

    @RequestMapping(value = "/orders/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<OrderViewModel>>> getOrdersByUserIdRpc(@PathVariable("userId") int userId){

        List<OrderDto> results = orderService.getOrderByUserId(userId);
        List<OrderViewModel> result = new ArrayList<>();

        for (OrderDto orderDto : results) {
            OrderViewModel model = new OrderViewModel();
            model.setUserId(orderDto.getUserId());
            model.setDeliveryType(orderDto.getDeliveryType());

            for (ProductRestaurantDto productRestaurantDto: orderDto.getProductRestaurants()) {
                model.getProducts().add(productRestaurantDto.getProduct().getId());
            }

        }
        return new ResponseEntity<>(Collections.singletonMap("orders", result), HttpStatus.OK);
    }

    @RequestMapping(value = "/orders/{Id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, OrderViewModel>> getOrderByIdRpc(@PathVariable("Id") int id){

        OrderDto orderDto = orderService.getOrderById(id);

        Map<String, OrderViewModel> result = new HashMap<>();

        OrderViewModel view = new OrderViewModel();
        view.setUserId(orderDto.getUserId());
        view.setDeliveryType(orderDto.getDeliveryType());
        for (ProductRestaurantDto productRestaurantDto: orderDto.getProductRestaurants()) {
            view.getProducts().add(productRestaurantDto.getProduct().getId());
        }

        result.put("order", view);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateOrder() {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    //public ResponseEntity<Void> addOrder(@PathVariable("IdsProductRestaurant") List<Integer> idsProductRestaurant, @PathVariable("deliveryType") String deliveryType, UriComponentsBuilder builder) {
    public ResponseEntity<Void> addOrder(@RequestBody OrderViewModel order, UriComponentsBuilder builder) {

        OrderDto orderdto = new OrderDto();

        for (Integer id : order.getProducts()) {
            ProductRestaurantDto productRestaurantDto = new ProductRestaurantDto();
            productRestaurantDto.setId(id);
            orderdto.getProductRestaurants().add(productRestaurantDto);
        }
        orderdto.setDeliveryType(order.getDeliveryType());
        orderdto.setUserId(order.getUserId());

        boolean flag = orderService.add(orderdto);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/orders/{id}").buildAndExpand(orderdto.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

   /* @PutMapping("article")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
        return new ResponseEntity<Article>(article, HttpStatus.OK);
    }*/
    @RequestMapping(value = "/orders", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllOrders() {

     // TODO:   OrderRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}