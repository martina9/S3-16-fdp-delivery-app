package com.fastdeliveryservice.controller;

import FDP.ProductService.MessageDirectory.Request.AddProduct;
import FDP.ProductService.MessageDirectory.Request.DeleteProduct;
import FDP.ProductService.MessageDirectory.Request.UpdateProduct;
import com.fastdeliveryservice.service.ProductService;
import com.fastdeliveryservice.viewModel.CategoryViewModel;
import com.fastdeliveryservice.viewModel.ProductViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;
import static com.fastdeliveryservice.utility.Mapper.ConvertFromMessage;
import static com.fastdeliveryservice.utility.Mapper.convertList;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author  mGabellini
 */

@RestController
@RequestMapping("/api")
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

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductViewModel> getProductByIdRpc(@PathVariable("id") int id){

        FDP.ProductService.MessageDirectory.Response.ProductInfo productInfo = productService.getProductById(id);

        ProductViewModel model = ConvertFromMessage(productInfo);

        return ResponseEntity.status(HttpStatus.OK).body(model);
    }

    /**
     * Returns an ResponseEntity for call request API getProductByIdRpc.
     *
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    public ResponseEntity<List<ProductViewModel>> getAllProductsByIdRpc(){
        List<FDP.ProductService.MessageDirectory.Response.ProductInfo> productResponse = productService.getAllProducts();

        List<ProductViewModel> viewModels   = new ArrayList<>();

        viewModels.addAll(convertList(productResponse, s -> new ProductViewModel(s.getId(),s.getName(),s.getCode(),s.getCategoryId(),s.getCategoryName())));

        return ResponseEntity.ok(viewModels);
    }

    /**
     * Returns an ResponseEntity for call request API getProductByIdRpc.
     *
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/categories", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryViewModel>> getAllCategoriesByRpc(){
        List<FDP.ProductService.Shared.CategoryInfo> categoryResponse = productService.getAllCategories();

        List<CategoryViewModel> viewModels  = convertList(categoryResponse, s -> new CategoryViewModel(s.getId(),s.getName()));

        if(viewModels == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.ok(viewModels);
        }
    }
    /*
     * Returns an ResponseEntity for call request API addProduct.
     *
     * @param  product for ProductViewModel
     * @param  builder for UriComponentsBuilder
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody ProductViewModel product, UriComponentsBuilder builder) {

        AddProduct addProduct = new AddProduct();
        addProduct.setName(product.getName());
        addProduct.setCode(product.getCode());
        addProduct.setCategoryId(product.getCategoryId());

        FDP.ProductService.MessageDirectory.Response.AddProduct response = productService.addProduct(addProduct);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * Returns an ResponseEntity for call request API updateProduct.
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody ProductViewModel product) {


        UpdateProduct updateProduct = new UpdateProduct();
        updateProduct.setId(id);
        updateProduct.setName(product.getName());
        updateProduct.setCode(product.getCode());
        updateProduct.setCategoryId(product.getCategoryId());
        FDP.ProductService.MessageDirectory.Response.UpdateProduct response = productService.updateProduct(updateProduct);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Returns an ResponseEntity for call request API deleteProduct.
     *
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {

        DeleteProduct deleteProduct = new DeleteProduct();
        deleteProduct.setId(id);
        productService.deleteProduct(deleteProduct);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}