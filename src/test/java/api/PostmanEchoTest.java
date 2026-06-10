import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class PostmanEchoTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void testGet() {
        RestAssured
                .given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }

    @Test
    public void testPostRaw() {
        String body = "This is expected to be sent back as part of response body.";

        RestAssured
                .given()
                .contentType("text/plain")
                .body(body)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(body));
    }

    @Test
    public void testPostForm() {
        RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }

    @Test
    public void testPut() {
        String body = "This is expected to be sent back as part of response body.";

        RestAssured
                .given()
                .contentType("text/plain")
                .body(body)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(body));
    }

    @Test
    public void testPatch() {
        String body = "This is expected to be sent back as part of response body.";

        RestAssured
                .given()
                .contentType("text/plain")
                .body(body)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(body));
    }

    @Test
    public void testDelete() {
        String body = "This is expected to be sent back as part of response body.";

        RestAssured
                .given()
                .contentType("text/plain")
                .body(body)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(body));
    }
}