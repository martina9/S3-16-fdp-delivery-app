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

import java.util.*;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<ProductRestaurantViewModel>> getProductsByRestaurantCode(@PathVariable("restaurantCode") String restaurantCode) {
        List<ProductRestaurantDto> productRestaurantDtoList = productRestaurantService.getProductByRestaurantCode(restaurantCode);
        List<ProductRestaurantViewModel> viewModels = new ArrayList<>();
        for (ProductRestaurantDto productRestaurantDto: productRestaurantDtoList){
            ProductRestaurantViewModel viewModel = new ProductRestaurantViewModel();
            viewModel.setName(productRestaurantDto.getName());
            viewModel.setId(productRestaurantDto.getId());
            viewModel.setPrice(productRestaurantDto.getPrice());
            viewModel.setRestaurantId(productRestaurantDto.getRestaurant().getId());
            viewModel.setProductId(productRestaurantDto.getProduct().getId());
            viewModel.setIngredients(productRestaurantDto.getProduct().getIngredients().stream().map(x->x.getName()).collect(Collectors.toList()));
            viewModels.add(viewModel);
        }

        return new ResponseEntity<>(viewModels, HttpStatus.OK);
    }

    /**
     * Returns an ResponseEntity for call request API getProductRestaurantById.
     *
     * @param id from Api
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductRestaurantDto> getProductRestaurantById(@PathVariable("id") int id){

        ProductRestaurantDto productRestaurantDto = productRestaurantService.getProductRestaurantById(id);
        if(productRestaurantDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.ok(productRestaurantDto);
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
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Integer> add(@RequestBody ProductRestaurantViewModel productRestaurant) {

        try
        {
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
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Returns an ResponseEntity for call request API updateProduct.
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody ProductRestaurantViewModel productRestaurant) {
        try
        {
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
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Returns an ResponseEntity for call request API deleteProduct.
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {

        try
        {
            int deletedId = productRestaurantService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
}