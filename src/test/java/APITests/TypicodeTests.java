package APITests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import typicodeClasses.User;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypicodeTests {
    int userID;
    String request;
    int expectedResult;

    @Test
    public void testGetUsersReturns200()  throws ClientProtocolException, IOException {
        userID = 1;
        request = "http://jsonplaceholder.typicode.com/users/";
       expectedResult = HttpStatus.SC_OK;

        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest httpUriRequest = new HttpGet(request);

        HttpResponse response = client.execute(httpUriRequest);
        int actualResult = response.getStatusLine().getStatusCode();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetUsersWithKnownUserReturns200()  throws ClientProtocolException, IOException {
        userID = 1;
        request = "http://jsonplaceholder.typicode.com/users/" + userID;
        expectedResult = HttpStatus.SC_OK;

        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest httpUriRequest = new HttpGet(request);

        HttpResponse response = client.execute(httpUriRequest);
        int actualResult = response.getStatusLine().getStatusCode();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetUsersWithInvalidUserReturns404()  throws ClientProtocolException, IOException {
        userID = 15;
        request = "http://jsonplaceholder.typicode.com/users/" + userID;
        expectedResult = HttpStatus.SC_NOT_FOUND;

        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest httpUriRequest = new HttpGet(request);

        HttpResponse response = client.execute(httpUriRequest);
        int actualResult = response.getStatusLine().getStatusCode();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testUser1()  throws ClientProtocolException, IOException {
        userID = 1;
        request = "http://jsonplaceholder.typicode.com/users/" + userID;
        String expectedResult = "Leanne Graham";

        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest httpUriRequest = new HttpGet(request);

        HttpResponse response = client.execute(httpUriRequest);

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(response.getEntity().getContent(), User.class);

        String actualResult = user.getName();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testUser1HasCorrectCity()  throws ClientProtocolException, IOException {
        userID = 1;
        request = "http://jsonplaceholder.typicode.com/users/" + userID;
        String expectedResult = "Gwenborough";

        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest httpUriRequest = new HttpGet(request);

        HttpResponse response = client.execute(httpUriRequest);

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(response.getEntity().getContent(), User.class);

        String actualResult = user.getAddress().getCity();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testNoUserLivesInWilloughby()  throws ClientProtocolException, IOException {

        request = "http://jsonplaceholder.typicode.com/users/";
        String expectedResult = "Gwenborough";

        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest httpUriRequest = new HttpGet(request);

        HttpResponse response = client.execute(httpUriRequest);

        ObjectMapper mapper = new ObjectMapper();
        User[] user = mapper.readValue(response.getEntity().getContent(), User[].class);

    }

}
