package junit;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;

import org.junit.Test;

/**
 * @author  mFéliz
 */


public class ProductRestaurantTest {
    private final String  PRODUCT_RESTAURANT_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/products/restaurant/";
    private final String  PRODUCT_RESTAURANT_PRODUCT_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/products/restaurant/product/";

    @Test
    public void getProductRestaurantById() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( PRODUCT_RESTAURANT_SERVICE_API);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void getAllProductsRestaurantByRestaurantId() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( PRODUCT_RESTAURANT_PRODUCT_SERVICE_API);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }

}
