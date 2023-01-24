import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class test {

    private static String requestBody="{\n" +
            "  \"id\": 350,\n" +
            "  \"category\": {\n" +
            "    \"id\": 3,\n" +
            "    \"name\": \"Pet GOLD\"\n" +
            "  },\n" +
            "  \"name\": \"doggie\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"https://wallpaper.mob.org/image/3d-babochka-listya-krilya-kontrast-128113.html\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 3,\n" +
            "      \"name\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";

    @BeforeTest
    public void setup(){
        RestAssured.baseURI="https://petstore.swagger.io/v2";
    }

    @Test
    public void getPet(){

        Response response= given()
                .contentType(ContentType.JSON)
                .param("petId","350")
                .when()
                .get("/pet/350")
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
                .post("/pet")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void deletePet(){
        Response response= given()
                .header("Content-Type","application/json")
                .and()
                .delete("/pet/350")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(),200);
    }
}

