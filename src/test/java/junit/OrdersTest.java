package junit;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author  mAkbarimoghadam
 */

@RunWith(SpringRunner.class)
public class OrdersTest {

    @Test
    public void getOrders() throws IOException {

        // Given
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void getOrderById() throws IOException {

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
}