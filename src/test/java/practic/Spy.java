package practic;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import io.restassured.RestAssured;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class Spy {
    public static void main(String[] args) {

        WireMockServer wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        //
        wireMockServer.stubFor(get(urlEqualTo("/user/get/1"))
                .willReturn(aResponse().proxiedFrom("http://localhost:8080/user/get/1")));


        int response = RestAssured.get("http://localhost:8080/user/get/1")
                .then()
                .extract()
                .statusCode();


        int response2 = RestAssured.get("http://localhost:8080/user/get/1")
                .then()
                .extract()
                .statusCode();

        List<LoggedRequest> requests = wireMockServer.findAll(getRequestedFor(urlEqualTo("/user/get/1")));
        System.out.println(requests);

        wireMockServer.stop();
    }
}
