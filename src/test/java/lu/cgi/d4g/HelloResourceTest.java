package lu.cgi.d4g;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class HelloResourceTest {

    @Test
    public void testHelloEndpoint() {
        // @formatter:off
        given()
            .when().get("/hello")
            .then()
                .statusCode(200)
                .body(is("Hello!"));
        // @formatter:on
    }

    @Test
    public void testHelloNameEndpoint() {
        // @formatter:off
        given()
            .when().get("/hello/me")
            .then()
                .statusCode(200)
                .body(is("Hello, me!"));
        // @formatter:on
    }
}
