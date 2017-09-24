package com.fastdeliveryservice.controller;

import com.fastdeliveryservice.domain.RestaurantDto;
import com.fastdeliveryservice.service.RestaurantService;
import com.fastdeliveryservice.viewModel.SellerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  mGabellini
 */

@RestController
public class RestaurantController {
    private RestaurantService restaurantService;

    //Injection parameters
    @Autowired
    public RestaurantController( RestaurantService restaurantService) {
            this.restaurantService = restaurantService;
    }

    @RequestMapping(value = "/restaurants/{city}", method = RequestMethod.GET)
    public ResponseEntity<List<SellerViewModel>> getRestaurantByCityRpc(@PathVariable("city") String city) {

        //use service Manager for Message With Rabbit
        List<RestaurantDto> results = restaurantService.getRestaurantMessageRpc(city);

        //Mapping Result into View Model
        List<SellerViewModel> viewModels = new ArrayList<>();

        for (RestaurantDto dto : results) {
            SellerViewModel model = new SellerViewModel();
            model.setIdSeller(dto.getId());
            model.setCode(dto.getCode());
            model.setName(dto.getName());
            model.setEmail(dto.getAddressRestaurants().stream().findFirst().get().getEmail());
            model.setPhoneNumber(dto.getAddressRestaurants().stream().findFirst().get().getPhoneNumber());
            model.setCity(dto.getAddressRestaurants().stream().findFirst().get().getCity());
            model.setStreet(dto.getAddressRestaurants().stream().findFirst().get().getStreet());
            model.setZipCode(dto.getAddressRestaurants().stream().findFirst().get().getZipCode());
            model.setCode(dto.getAddressRestaurants().stream().findFirst().get().getCode());
            viewModels.add(model);
        }
        return new ResponseEntity<>(viewModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/restaurants/drop", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteAllRestaurants() {

     // TODO:   RestaurantRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}