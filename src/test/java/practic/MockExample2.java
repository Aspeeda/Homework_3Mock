package practic;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

// стаб возвращает заранее заданный ответ на определенный запрос

public class MockExample2 {
    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        //
        wireMockServer.stubFor(get(urlEqualTo("/user/get/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"name\": \"Test user\" , \"age\" : 23 }")
                        .withStatus(200)));

        String response = RestAssured.get("http://localhost:8080/user/get/1")
                .then()
                .statusCode(200)
                .extract()
                .asString();
        System.out.println("Ответ от стаба" + response);

        wireMockServer.verify((getRequestedFor(urlEqualTo("/user/get/1"))));
        System.out.println("проверка пройдена");
        wireMockServer.stop();
    }
}
