package homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class WireMockSoap extends BaseTest {

    @Test
    public void soapMockTest() {

        Response response = RestAssured.get("http://localhost:8080/soap/user/get/all");

        response
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/soap+xml"))
                .body(containsString(" <age>23</age>"))
                .body(containsString("<name>Test user</name>"));
    }
}
