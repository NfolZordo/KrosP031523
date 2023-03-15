package integration;

import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("integration")
@Tag("integration")
public abstract class ComponentTest {
    @Autowired
    protected WebTestClient webTestClient;
}
