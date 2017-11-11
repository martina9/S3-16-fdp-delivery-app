package com.fastdeliveryservice.controller;

import FDP.ProductService.MessageDirectory.Response.ProductRestaurantList;
import com.fastdeliveryservice.service.ProductRestaurantService;
import com.fastdeliveryservice.viewModel.ProductRestaurantViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import static com.fastdeliveryservice.utility.Mapper.convertList;

/**
 * @author  mGabellini
 */

@RestController
@RequestMapping("/api")
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

    @RequestMapping(value = "/products/restaurant/{idRestaurant}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductRestaurantViewModel>> getProductsByRestaurantId(@PathVariable("idRestaurant") int idRestaurant) {

       ProductRestaurantList productList = productRestaurantService.getProductsByRestaurantId(idRestaurant);
        List<ProductRestaurantViewModel> viewModels  = new ArrayList<>();
        viewModels.addAll(convertList(productList.getItems(), s -> new ProductRestaurantViewModel(s.getId(),s.getPrice(),s.getName(),s.getProductId(),s.getRestaurantId(),s.getProductName(),s.getRestaurantName(),s.getQuantity())));

        return new ResponseEntity<>(viewModels, HttpStatus.OK);
    }
     /**
     * Returns an ResponseEntity for call request API getProductRestaurantById.
     *
     * @param id from Api
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/restaurant/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductRestaurantViewModel> getProductRestaurantById(@PathVariable("id") int id){

        FDP.ProductService.MessageDirectory.Response.ProductRestaurantInfo productRestaurantInfo = productRestaurantService.getProductRestaurantById(id);
        if(productRestaurantInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            ProductRestaurantViewModel viewModel = new ProductRestaurantViewModel(){{
                setId(productRestaurantInfo.getId());
                setName(productRestaurantInfo.getName());
                setPrice(productRestaurantInfo.getPrice());
                setProductId(productRestaurantInfo.getProductId());
                setRestaurantId(productRestaurantInfo.getRestaurantId());
                setQuantity(productRestaurantInfo.getQuantity());
                setProductName(productRestaurantInfo.getProductName());
                setRestaurantName(productRestaurantInfo.getRestaurantName());
            }};

            return ResponseEntity.ok(viewModel);
        }
    }

    /**
     * Returns an ResponseEntity for call request API addProduct.
     *
     * @param  productRestaurant for ProductViewModel
     * @return the ResponseEntity from Api called
     * @see    ResponseEntity
     */

    @RequestMapping(value = "/products/restaurant/product", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody ProductRestaurantViewModel productRestaurant) {

        try
        {
            productRestaurantService.add(productRestaurant.getName(),productRestaurant.getRestaurantId(),productRestaurant.getPrice(),productRestaurant.getProductId());

            return new ResponseEntity<>(HttpStatus.CREATED);
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

    @RequestMapping(value = "/products/restaurant/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable("id") int id , @RequestBody ProductRestaurantViewModel productRestaurant) {
        try
        {
            productRestaurantService.update(id,productRestaurant.getName(),productRestaurant.getRestaurantId(),productRestaurant.getPrice(),productRestaurant.getProductId());

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

    @RequestMapping(value = "/products/restaurant/product/{id}", method = RequestMethod.DELETE)
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