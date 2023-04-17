package APITests;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessagingAppApiTests {

    int userID;
    String request;


    @Test
    public void testGetAllMessagesReturns200()  throws ClientProtocolException, IOException {
        userID = 1;
        request = "http://localhost:8080/messages";
        int expectedResult = HttpStatus.SC_OK;

        HttpClient  client = HttpClientBuilder.create().build();
        HttpUriRequest httpUriRequest = new HttpGet(request);

        HttpResponse response = client.execute(httpUriRequest);
        int actualResult = response.getStatusLine().getStatusCode();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetSpecificMessagesReturns200()  throws ClientProtocolException, IOException {
        userID = 1;
        request = "http://localhost:8080/messages";
        int expectedResult = HttpStatus.SC_OK;

        HttpClient  client = HttpClientBuilder.create().build();
        HttpUriRequest httpUriRequest = new HttpGet(request+"/"+userID);

        HttpResponse response = client.execute(httpUriRequest);
        int actualResult = response.getStatusLine().getStatusCode();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testWithInvalidMessageIDReturns200()  throws ClientProtocolException, IOException {
        userID = 51;
        request = "http://localhost:8080/messages";
        int expectedResult = HttpStatus.SC_OK;

        HttpClient  client = HttpClientBuilder.create().build();
        HttpUriRequest httpUriRequest = new HttpGet(request+"/"+userID);

        HttpResponse response = client.execute(httpUriRequest);
        int actualResult = response.getStatusLine().getStatusCode();

        assertEquals(expectedResult, actualResult);
    }

}
