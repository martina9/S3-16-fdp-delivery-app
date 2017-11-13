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
 * @author  mAkbarimoghadam
 */

@RunWith(SpringRunner.class)
public class RestaurantTest {

    private final String  RESTAURANT_SERVICE_API = "http://localhost:8089/FastDeliveryPizza/api/restaurants/";
    private final String  RESTAURANT_SERVICE_API_ERROR = "http://localhost:8089/FastDeliveryPizza/api/restaurants/error/";
    @Test
    public void getLocationsByAddress() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( RESTAURANT_SERVICE_API);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_OK));
    }

    @Test
    public void getLocationsByAddressError() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( RESTAURANT_SERVICE_API_ERROR);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }
}