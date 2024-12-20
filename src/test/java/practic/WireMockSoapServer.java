package practic;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockSoapServer {
    public static void main(String[] args) throws IOException {
        WireMockServer wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/soap=responses/user_response.xml"));
        String soapResponse = String.join("\n", lines);

        wireMockServer.stubFor(get(urlEqualTo("/soap/user/get/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/soap+xml")
                        .withBody(soapResponse)
                        .withStatus(200)));

        Response response = RestAssured.get("http://localhost:8080/soap/user/get/1");

        System.out.println("Response code: " + response.statusCode());
        System.out.println("Response body: " + response.asString());
        wireMockServer.stop();
    }
}
