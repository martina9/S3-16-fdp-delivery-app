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
    @Test
    public void getProductRestaurantById() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void getAllProductsRestaurantByRestaurantId() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void getAllProductsRestaurantByRestaurantCode() throws IOException {

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
