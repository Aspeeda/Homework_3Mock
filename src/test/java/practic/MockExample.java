package practic;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

// мок проверяет, был ли запрос сделан и какие данные переданы
public class MockExample {
    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(8080);
        wireMockServer.start();


        wireMockServer.stubFor(get(urlEqualTo("/user/get/1"))
                .willReturn(aResponse().withStatus(200)));

        int response = RestAssured.get("http://localhost:8080/user/get/1")
                .then()
                .statusCode(200)
                .extract()
                        .statusCode();
        System.out.println("Ответ от mock" + response);

        wireMockServer.verify((getRequestedFor(urlEqualTo("/user/get/1"))));
        System.out.println("проверка пройдена");
        wireMockServer.stop();
    }
}
