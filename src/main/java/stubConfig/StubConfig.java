package stubConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class StubConfig {

    public static void setupStubs() throws IOException {
        // Заглушка для /user/get/{id}
        stubFor(get(urlPathMatching("/user/get/[0-9]+"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"name\":\"Test user\",\"score\":78}")));

        // Заглушка для /cource/get/all
        stubFor(get(urlEqualTo("/cource/get/all"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"name\":\"QA java\",\"price\":15000},{\"name\":\"Java\",\"price\":12000}]")));

        // Заглушка для /user/get/all
        stubFor(get(urlEqualTo("/user/get/all"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"name\":\"Test user\",\"cource\":\"QA\",\"email\":\"test@test.test\",\"age\":23}]")));

        //для soap
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/soap/User.xml"));
        String soapResponse = String.join("\n", lines);

        stubFor(get(urlEqualTo("/soap/user/get/all"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/soap+xml")
                        .withBody(soapResponse)
                        .withStatus(200)));
    }
}
