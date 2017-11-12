package junit;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author  mCastillo
 */

@RunWith(SpringRunner.class)
public class ProductsClass {

     private final String  BASE_URL = "http://localhost:8089/FastDeliveryPizza/api/";
     private final String  PRODUCT_SERVICE_API =  "http://localhost:8089/FastDeliveryPizza/api/products/";
     private final String  RESTAURANT_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/restaurants/";
     private final String  CATEGORIES_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/products/categories/";
     private final String  ORDER_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/orders/user/";
     private final String  USER_ORDER_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/orders/user/";
     private final String  RESTAURANT_ORDER_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/orders/restaurant/";
     private final String  PRODUCT_RESTAURANT_PRODUCT_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/products/restaurant/product/";
     private final String  PRODUCT_RESTAURANT_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/products/restaurant/";

     @Test
    public void getProductsById() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( PRODUCT_SERVICE_API);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void getAllProductsByRestaurantId() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void getAllProductsByRestaurantCode() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void add() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void update() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void delete() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }
}