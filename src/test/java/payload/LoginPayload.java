package payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.LoginRequest;
public class LoginPayload {
    public  Response sendPostRequest(String url, LoginRequest payload) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(url);
    }

}
