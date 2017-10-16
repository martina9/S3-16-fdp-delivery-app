package com.fastdeliveryservice.utility;

import FDP.OrderService.MessageDirectory.Response.OrderInfo;
import FDP.ProductService.MessageDirectory.Response.ProductInfo;
import com.fastdeliveryservice.viewModel.OrderViewModel;
import com.fastdeliveryservice.viewModel.ProductViewModel;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Martina on 01/10/2017.
 */
public class Mapper {

    public static ProductViewModel ConvertFromMessage(ProductInfo productInfo)
    {
        ProductViewModel view = new ProductViewModel();
        view.setId(productInfo.getId());
        view.setName(productInfo.getName());
        view.setCategoryId(productInfo.getCategoryId());
        view.setCode(productInfo.getCode());

        return view;
    }

    public static OrderViewModel ConvertFromMessage(OrderInfo orderInfo)
    {
        OrderViewModel view = new OrderViewModel();
        view.setAddress(orderInfo.getAddress());
        view.setAmount(orderInfo.getAmount());
        view.setCity(orderInfo.getCity());
        view.setConfirmationDate(orderInfo.getConfirmationDate());
        view.setUserId(orderInfo.getUserId());
        view.setCity(orderInfo.getCity());
        view.setEmail(orderInfo.getEmail());
        view.setDeliveryType(orderInfo.getDeliveryType());
        view.setProducts(orderInfo.getProducts());
        return view;
    }


    public static FDP.ProductService.MessageDirectory.Request.AddProduct ConvertToRequestAddProduct(ProductViewModel productViewModel)
    {
        FDP.ProductService.MessageDirectory.Request.AddProduct addProduct = new FDP.ProductService.MessageDirectory.Request.AddProduct();
        addProduct.setCategoryId(addProduct.getCategoryId());
        addProduct.setCode(addProduct.getCode());
        addProduct.setName(addProduct.getName());
        return addProduct;
    }

    public static FDP.ProductService.MessageDirectory.Request.UpdateProduct ConvertToRequestUpdateProduct(ProductViewModel productViewModel)
    {
        FDP.ProductService.MessageDirectory.Request.UpdateProduct updateProduct = new FDP.ProductService.MessageDirectory.Request.UpdateProduct();
        updateProduct.setCategoryId(productViewModel.getCategoryId());
        updateProduct.setCode(productViewModel.getCode());
        updateProduct.setName(productViewModel.getName());
        updateProduct.setId(productViewModel.getId());
        return updateProduct;
    }

    public static FDP.ProductService.MessageDirectory.Request.DeleteProduct ConvertToRequestDeleteProduct(ProductViewModel productViewModel)
    {
        FDP.ProductService.MessageDirectory.Request.DeleteProduct deleteProduct = new FDP.ProductService.MessageDirectory.Request.DeleteProduct();
        deleteProduct.setId(productViewModel.getId());
        return deleteProduct;
    }

    public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }
}
