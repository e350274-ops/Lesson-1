import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class PostmanEchoTest {

    @Test
    public void testGet() {
        RestAssured
                .given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("https://postman-echo.com/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }

    @Test
    public void testPost() {
        String body = "This is expected to be sent back as part of response body.";

        RestAssured
                .given()
                .body(body)
                .when()
                .post("https://postman-echo.com/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(body))
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    public void testPut() {
        String body = "This is expected to be sent back as part of response body.";

        RestAssured
                .given()
                .body(body)
                .when()
                .put("https://postman-echo.com/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(body))
                .body("url", equalTo("https://postman-echo.com/put"));
    }

    @Test
    public void testPatch() {
        String body = "This is expected to be sent back as part of response body.";

        RestAssured
                .given()
                .body(body)
                .when()
                .patch("https://postman-echo.com/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(body))
                .body("url", equalTo("https://postman-echo.com/patch"));
    }

    @Test
    public void testDelete() {
        String body = "This is expected to be sent back as part of response body.";

        RestAssured
                .given()
                .body(body)
                .when()
                .delete("https://postman-echo.com/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(body))
                .body("url", equalTo("https://postman-echo.com/delete"));
    }
}