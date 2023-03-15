package integration.component;

import com.ars.manager.Application;
import integration.ComponentTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class})
public class CompanyTest extends ComponentTest {
    private MockWebServer webServer;

    private static Stream<Arguments> companyByIdArguments() {
        return Stream.of(
                Arguments.arguments(HttpStatus.OK, HttpStatus.OK),
                Arguments.arguments(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND),
                Arguments.arguments(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }


    @ParameterizedTest
    @MethodSource("companyByIdArguments")
    void getCompanyById(HttpStatus mockResponse, HttpStatus response) throws IOException {
        setUpMockWebServer(mockResponse);

        var companyId = "1ed7471c-b467-11ed-afa1-0242ac120002";

        webTestClient.delete()
                .uri("/v1/companies/{id}", companyId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(response);


        webServer.shutdown();
    }

    private void setUpMockWebServer(HttpStatus status) throws IOException {

        webServer = new MockWebServer();

        webServer.enqueue(
                new MockResponse()
                        .setResponseCode(status.value())
                        .addHeader("Content-Type", "application/json")
        );

        webServer.start(8090);
    }

    private void setUpMockWebServer(HttpStatus status, String pathToResponse) throws IOException {
        var responseBody = new String(requireNonNull(this.getClass().getResourceAsStream(pathToResponse)).readAllBytes());

        webServer = new MockWebServer();

        webServer.enqueue(
                new MockResponse()
                        .setResponseCode(status.value())
                        .setBody(responseBody)
                        .addHeader("Content-Type", "application/json")
        );

        webServer.start(8090);
    }
}
