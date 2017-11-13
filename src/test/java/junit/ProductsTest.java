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
public class ProductsTest {

     private final String  PRODUCT_SERVICE_API =  "http://localhost:8089/FastDeliveryPizza/api/products/";
     private final String  PRODUCT_RESTAURANT_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/products/restaurant/";

     @Test
    public void getProductsById() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet(PRODUCT_SERVICE_API);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_OK));
    }


    @Test
    public void getAllProductsByRestaurantId() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( PRODUCT_RESTAURANT_SERVICE_API);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_OK));
    }

}