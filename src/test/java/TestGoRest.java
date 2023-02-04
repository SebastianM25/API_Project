import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestGoRest {



    private static final String requestBody="{\n" +
            "  \"id\": 93019,\n" +
            "  \"name\": \"SebaTest\",\n" +
            "  \"email\": \"sebbtest6@block.io\",\n" +
            "  \"gender\": \"male\",\n" +
            "  \"status\": \"inactive\"\n" +
            "}";

    @BeforeTest
    public void setup(){
        RestAssured.baseURI="https://gorest.co.in/public/v2/users";
        RequestSpecification request= RestAssured.given();
    }
    @BeforeTest
    public void auth(){

        String authToken="0c9c40d945079e2b1f6763d190da596ef3a71d334f79f07902dce75df50ccc99";
    }
    @Test
    public void getPet(){

        Response response= given()
                .contentType(ContentType.JSON)
                .param("users","178120")
                .when()
                .get("/178120")
                .then()
                .extract().response();

        System.out.println("Response: "+response.asString());
        System.out.println("Status code: "+response.statusCode());
        System.out.println("Body: "+response.getBody().asString());
        System.out.println("Header"+response.getHeader("content-type"));


    }

    @Test
    public void createPet(){


        Response response=given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post(baseURI)
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(),200);
    }
}
