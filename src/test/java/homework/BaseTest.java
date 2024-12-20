package homework;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static stubConfig.StubConfig.setupStubs;

public class BaseTest {
    private WireMockServer wireMockServer;

    @BeforeEach
    public void setupStubServer() throws IOException {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8080));
        wireMockServer.start();
        configureFor("localhost", 8080);
        setupStubs();
    }

    @AfterEach
    public void stopServer() {
        wireMockServer.stop();
    }
}
