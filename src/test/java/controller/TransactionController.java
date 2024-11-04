package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.TransactionModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TransactionController {
    Properties prop;
    public TransactionController(Properties prop){
        this.prop = prop;
    }

    public Response depositFromSystemToagent() throws IOException, ParseException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(2);
        String agentNumber =(String) userObj.get("phoneNumber");
        int amount = 2000;
        TransactionModel model = new TransactionModel("SYSTEM",agentNumber,amount);
        RestAssured.baseURI= prop.getProperty("baseURL");
        return given().contentType("application/json").body(model)
                .header("Authorization", "bearer "+prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey")).when().post("/transaction/deposit");

    }
    public Response depositFromAgenttoCustomer1(String agentPhoneNumber, String customerPhoneNumber, int amount) throws IOException, ParseException, InterruptedException {
        TransactionModel model = new TransactionModel(agentPhoneNumber,customerPhoneNumber,amount);
        RestAssured.baseURI= prop.getProperty("baseURL");
        return given().contentType("application/json").body(model)
                .header("Authorization", "bearer "+prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey")).when().post("/transaction/deposit");

    }
    public Response withdrawFromAgent(String customerPhoneNumber, String agentPhoneNumber, int amount) throws IOException, ParseException, InterruptedException {
        TransactionModel model = new TransactionModel(customerPhoneNumber,agentPhoneNumber, amount);
        RestAssured.baseURI= prop.getProperty("baseURL");
        return given().contentType("application/json").body(model)
                .header("Authorization", "bearer "+prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey")).when().post("/transaction/withdraw");

    }
    public Response sendMoneyToCustomer2(String fromCustomer1Number, String toCustomer2Number, int amount) throws IOException, ParseException, InterruptedException {
//        Thread.sleep(5000);
        TransactionModel model = new TransactionModel(fromCustomer1Number,toCustomer2Number, amount);
        RestAssured.baseURI= prop.getProperty("baseURL");
        return given().contentType("application/json").body(model)
                .header("Authorization", "bearer "+prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey")).when().post("/transaction/sendmoney");

    }
    public Response paymentToMerchant(String fromNumber, String toNumber, int amount) throws IOException, ParseException, InterruptedException {
        TransactionModel model = new TransactionModel(fromNumber,toNumber, amount);
        RestAssured.baseURI= prop.getProperty("baseURL");
        return given().contentType("application/json").body(model)
                .header("Authorization", "bearer "+prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey")).when().post("/transaction/payment");

    }
    public Response checkCustomerBalance() throws IOException, ParseException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(0);
        String customerNumber =(String) userObj.get("phoneNumber");
        RestAssured.baseURI= prop.getProperty("baseURL");
        return given().contentType("application/json")
                .header("Authorization", "bearer "+prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey")).when().get("/transaction/balance/"+customerNumber);

    }

}
