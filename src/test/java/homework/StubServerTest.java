package homework;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StubServerTest extends BaseTest {

    @Test
    void stubServerTestAllUser() {

        int response = RestAssured.get(System.getProperty("baseUri") + "/user/get/all")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema/User.json"))
                .statusCode(200)
                .extract()
                .statusCode();
        System.out.println("Ответ от mock" + response);

        verify((getRequestedFor(urlEqualTo("/user/get/all"))));
        System.out.println("проверка пройдена");
    }

    @Test
    void stubServerTestCourse() {

        int response = RestAssured.get(System.getProperty("baseUri") + "/cource/get/all")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema/Cources.json"))
                .statusCode(200)
                .extract()
                .statusCode();
        System.out.println("Ответ от mock" + response);

        verify((getRequestedFor(urlEqualTo("/cource/get/all"))));
        System.out.println("проверка пройдена");
    }

    @Test
    void stubServerTestScore() {

        //пока поставила d=1 нужно вынести
        int response = RestAssured.get(System.getProperty("baseUri") + "/user/get/1")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema/Score.json"))
                .statusCode(200)
                .extract()
                .statusCode();
        System.out.println("Ответ от mock" + response);

        verify((getRequestedFor(urlEqualTo("/user/get/1"))));
        System.out.println("проверка пройдена");
    }
}
