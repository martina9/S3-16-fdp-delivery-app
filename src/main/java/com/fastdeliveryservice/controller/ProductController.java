package com.fastdeliveryservice.controller;

import com.fastdeliveryservice.domain.ProductDto;

import com.fastdeliveryservice.service.ProductService;

import com.fastdeliveryservice.viewModel.ProductViewModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

/**
 * @author  mGabellini
 */

@RestController
public class ProductController {
    private ProductService productService;

    /**
     * Constructor
     *
     * @param productService (required) to process API request for product
     */

    @Autowired
    public ProductController(ProductService productService) {
            this.productService = productService;
    }

    /**
     * Returns an ResponseEntity for call request API getProductByIdRpc.
     *
     * @param  id from Api
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    /*@RequestMapping(value = "/product/{Id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, ProductViewModel>> getProductByIdRpc(@PathVariable("Id") int id){
        ProductDto productDto = productService.getProductById(id);

        Map<String, ProductViewModel> result = new HashMap<>();

        ProductViewModel view = new ProductViewModel();
        view.setId(productDto.getId());
        view.setIngredients(productDto.getIngredients());
        view.setName(productDto.getName());

        result.put("product", view);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }*/

    /**
     * Returns an ResponseEntity for call request API addProduct.
     *
     * @param  product for ProductViewModel
     * @param  builder for UriComponentsBuilder
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     *//*

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody ProductViewModel product, UriComponentsBuilder builder) {

        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setIngredients(product.getIngredients());
        productDto.setPrice(product.getPrice());

        int flag = productService.add(productDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/product/{id}").buildAndExpand(productDto.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    *//**
     * Returns an ResponseEntity for call request API updateProduct.
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     *//*

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public ResponseEntity<Void> update() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    *//**
     * Returns an ResponseEntity for call request API deleteProduct.
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     *//*

    @RequestMapping(value = "/products", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete() {

     // TODO:   OrderRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }*/
}