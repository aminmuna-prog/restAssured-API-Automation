package testRunner;

import com.github.javafaker.Faker;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import setUp.Setup;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {
    @Test(priority = 1, description = "user login")
    public void login() throws ConfigurationException, InterruptedException {
        UserController userController = new UserController(prop);
        Response res = userController.login();
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String token = jsonPath.get("token");
        System.out.println(token);
        Utils.setEnvVar("token", token);
    }
   @Test(priority = 2, description = "create customer 1")
    public void createUser() throws ConfigurationException, IOException, ParseException, InterruptedException {
        UserController userController = new UserController(prop);
        Faker faker = new Faker();
        String phonenum = "01505"+ Utils.generateRandomId(100000, 999999);
        String role = "Customer";
        UserModel userModel = new UserModel();
        userModel.setName(faker.name().fullName());
        userModel.setEmail("rowshonamin10+17@gmail.com");
        userModel.setPassword("1234");
        userModel.setPhone_number(phonenum);
        userModel.setNid("123456789");
        userModel.setRole(role);
        Response res = userController.createUser(userModel);
        System.out.println(res.asString());
        JsonPath jsonPath2 = res.jsonPath();
        String messageActual = jsonPath2.get("message");
        String messageexpected ="User created";
        Assert.assertTrue(messageActual.contains(messageexpected));
//        save user info
       JSONObject userObj = new JSONObject();
       userObj.put("phoneNumber", phonenum);
       userObj.put("role", role);
       Utils.saveUserInfo("./src/test/resources/users.json", userObj);

    }
  @Test(priority = 3, description = "create customer 2")
    public void createUser2() throws ConfigurationException, IOException, ParseException, InterruptedException {
        UserController userController = new UserController(prop);
        Faker faker = new Faker();
       String phonenum = "01505"+ Utils.generateRandomId(100000, 999999);
       String role = "Customer";
        UserModel userModel = new UserModel();
        userModel.setName(faker.name().fullName());
        userModel.setEmail("rowshonamin10+18@gmail.com");
        userModel.setPassword("1234");
        userModel.setPhone_number(phonenum);
        userModel.setNid("123456789");
        userModel.setRole(role);
        Response res = userController.createUser(userModel);
        System.out.println(res.asString());
        JsonPath jsonPath2 = res.jsonPath();
        String messageActual = jsonPath2.get("message");
        String messageexpected ="User created";
        Assert.assertTrue(messageActual.contains(messageexpected));
       // save customer phone number and role
        JSONObject userObj = new JSONObject();
       userObj.put("phoneNumber", phonenum);
       userObj.put("role", role);
       Utils.saveUserInfo("./src/test/resources/users.json", userObj);
    }
   @Test(priority = 4, description = "create agent")
    public void createUser3() throws ConfigurationException, IOException, ParseException, InterruptedException {
        UserController userController = new UserController(prop);
        Faker faker = new Faker();
       String phonenum = "01505"+ Utils.generateRandomId(100000, 999999);
       String role = "Agent";
        UserModel userModel = new UserModel();
        userModel.setName(faker.name().fullName());
        userModel.setEmail("rowshonamin10+19@gmail.com");
        userModel.setPassword("1234");
        userModel.setPhone_number(phonenum);
        userModel.setNid("123456789");
        userModel.setRole("Agent");
        Response res = userController.createUser(userModel);
        System.out.println(res.asString());
        JsonPath jsonPath2 = res.jsonPath();
        String messageActual = jsonPath2.get("message");
        String messageexpected ="User created";
        Assert.assertTrue(messageActual.contains(messageexpected));
        // save Agent info
       JSONObject userObj = new JSONObject();
       userObj.put("phoneNumber", phonenum);
       userObj.put("role", role);
       Utils.saveUserInfo("./src/test/resources/users.json", userObj);
    }
    @AfterMethod
    public void delay() throws InterruptedException {
        Thread.sleep(2000);
    }
}