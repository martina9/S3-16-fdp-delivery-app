package com.fastdeliveryservice.service;

/**
 * Created by Martina
 */

import com.fastdeliveryservice.domain.ProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> getAllProductsByRestaurant(int idRestaurant);
    ProductDto getProductById(int productId);
    int add(ProductDto product);
    int update(ProductDto product);
    int delete(int productId);
}