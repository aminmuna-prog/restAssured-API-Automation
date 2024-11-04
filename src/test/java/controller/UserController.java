package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.UserModel;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserController{
    Properties prop;
    public UserController(Properties prop){
        this.prop = prop;
    }
    public Response login(){
        RestAssured.baseURI = prop.getProperty("baseURL");
        return given().contentType("application/json")
                .body("{\n" +
                        "    \"email\":\"admin@roadtocareer.net\",\n" +
                        "    \"password\":\"1234\"\n" +
                        "}").when().post("/user/login");
    }
    public Response createUser(UserModel userModel) throws InterruptedException {
        RestAssured.baseURI= prop.getProperty("baseURL");
        return given().contentType("application/json").body(userModel)
                .header("Authorization", "bearer "+prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey")).when().post("/user/create");

    }

}
