package com.fastdeliveryservice.controller;

import FDP.ProductService.MessageDirectory.Response.RestaurantList;
import com.fastdeliveryservice.service.RestaurantServiceImpl;
import com.fastdeliveryservice.viewModel.RestaurantViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.fastdeliveryservice.utility.Mapper.convertList;

/**
 * @author  sKahatib
 */

@RestController
@RequestMapping("/api")
public class RestaurantController {
    private RestaurantServiceImpl restaurantServiceImpl;

    //Injection parameters
    @Autowired
    public RestaurantController(RestaurantServiceImpl restaurantServiceImpl) {
            this.restaurantServiceImpl = restaurantServiceImpl;
    }

    @RequestMapping(value = "/restaurants/{city}", method = RequestMethod.GET)
    public ResponseEntity<List<RestaurantViewModel> > getRestaurantByCity(@PathVariable("city") String city) {

        RestaurantList response = restaurantServiceImpl.getRestaurantList(city,null);

        List<RestaurantViewModel> viewModels = new ArrayList<>();

        viewModels.addAll(convertList(response.getItems(),
                s -> new RestaurantViewModel(s.getId(),s.getCode(),s.getName(),s.getCity(),s.getAddress(),s.getZipCode(),s.getPhoneNumber())));

            return ResponseEntity.ok(viewModels);
    }


    @RequestMapping(value = "/restaurants/", method = RequestMethod.GET)
    public ResponseEntity<List<RestaurantViewModel> > getAllRestaurants() {

        RestaurantList response = restaurantServiceImpl.getRestaurantList(null,null);

        List<RestaurantViewModel> viewModels = new ArrayList<>();

        viewModels.addAll(convertList(response.getItems(),
                s -> new RestaurantViewModel(s.getId(),s.getCode(),s.getName(),s.getCity(),s.getAddress(),s.getZipCode(),s.getPhoneNumber())));

        return ResponseEntity.ok(viewModels);
    }

    @RequestMapping(value = "/restaurants/drop", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteAllRestaurants() {

     // TODO:   RestaurantRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}