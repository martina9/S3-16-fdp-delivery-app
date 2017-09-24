package com.fastdeliveryservice.controller;

import com.fastdeliveryservice.domain.ProductDto;
import com.fastdeliveryservice.domain.ProductRestaurantDto;
import com.fastdeliveryservice.domain.RestaurantDto;
import com.fastdeliveryservice.service.ProductRestaurantService;

import com.fastdeliveryservice.viewModel.ProductRestaurantViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  mGabellini
 */

@RestController
public class ProductRestaurantController {
    private ProductRestaurantService productRestaurantService;

    /**
     * Constructor
     *
     * @param productRestaurantService (required) to process API request for productRestaurant
     */

    @Autowired
    public ProductRestaurantController( ProductRestaurantService productRestaurantService) {
            this.productRestaurantService = productRestaurantService;
    }

    /**
     * Returns an ResponseEntity for call request API getProductsByRestaurantId.
     *
     * @param  idRestaurant from Api
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/restaurant1/{idRestaurant}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductRestaurantDto>> getProductsByRestaurantId(@PathVariable("idRestaurant") int idRestaurant) {
        List<ProductRestaurantDto> productRestaurantDto = productRestaurantService.getProductsByRestaurant(idRestaurant);
        return new ResponseEntity<>(productRestaurantDto, HttpStatus.OK);
    }

    /**
     * Returns an ResponseEntity for call request API getProductsByRestaurantId.
     *
     * @param  restaurantCode from Api
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/restaurant/{restaurantCode}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductRestaurantDto>> getProductsByRestaurantCode(@PathVariable("restaurantCode") String restaurantCode) {
        List<ProductRestaurantDto> productRestaurantDto = productRestaurantService.getProductByRestaurantCode(restaurantCode);
        return new ResponseEntity<>(productRestaurantDto, HttpStatus.OK);
    }

    /**
     * Returns an ResponseEntity for call request API getProductRestaurantById.
     *
     * @param id from Api
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, ProductRestaurantDto>> getProductRestaurantById(@PathVariable("id") int id){

        ProductRestaurantDto productRestaurantDto = productRestaurantService.getProductRestaurantById(id);
        if(productRestaurantDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            Map<String, ProductRestaurantDto> result = new HashMap<>();
            result.put("productRestaurant", productRestaurantDto);
            return ResponseEntity.ok(result);
        }
    }

    /**
     * Returns an ResponseEntity for call request API deleteAllRestaurants.
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    /*@RequestMapping(value = "/products", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllRestaurants() {
        // TODO:   RestaurantRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }*/

    /**
     * Returns an ResponseEntity for call request API addProduct.
     *
     * @param  productRestaurant for ProductViewModel
     * @param  builder for UriComponentsBuilder
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Integer> add(@RequestBody ProductRestaurantViewModel productRestaurant, UriComponentsBuilder builder) {

        ProductRestaurantDto productRestaurantDto = new ProductRestaurantDto();
        productRestaurantDto.setId(productRestaurant.getId());
        productRestaurantDto.setName(productRestaurant.getName());
        productRestaurantDto.setPrice(productRestaurant.getPrice());
        productRestaurantDto.setQuantity(productRestaurant.getQuantity());
        productRestaurantDto.setPrice(productRestaurant.getPrice());
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(productRestaurant.getRestaurantId());
        productRestaurantDto.setRestaurant(restaurantDto);

        ProductDto productDto = new ProductDto();
        productDto.setId(productRestaurant.getProductId());
        productRestaurantDto.setProduct(productDto);

        Integer productId = productRestaurantService.add(productRestaurantDto);

        return new ResponseEntity<Integer>(productId, HttpStatus.CREATED);
    }

    /**
     * Returns an ResponseEntity for call request API updateProduct.
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody ProductRestaurantViewModel productRestaurant) {
        ProductRestaurantDto productRestaurantDto = new ProductRestaurantDto();

        productRestaurantDto.setId(productRestaurant.getId());
        productRestaurantDto.setName(productRestaurant.getName());
        productRestaurantDto.setPrice(productRestaurant.getPrice());
        productRestaurantDto.setQuantity(productRestaurant.getQuantity());
        productRestaurantDto.setPrice(productRestaurant.getPrice());
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(productRestaurant.getRestaurantId());
        productRestaurantDto.setRestaurant(restaurantDto);

        ProductDto productDto = new ProductDto();
        productDto.setId(productRestaurant.getProductId());
        productRestaurantDto.setProduct(productDto);

        productRestaurantService.update(productRestaurantDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Returns an ResponseEntity for call request API deleteProduct.
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("Id") int id) {

        boolean deleted = productRestaurantService.delete(id);
        if(deleted) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}