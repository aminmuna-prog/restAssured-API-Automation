package testRunner;

import controller.TransactionController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import setUp.Setup;

import java.io.FileReader;
import java.io.IOException;

public class TransactionTestRunner extends Setup {

    @Test(priority = 1, description = "System to agent 2000tk transaction")
    public void depositFromSystemToagent() throws IOException, InterruptedException, ParseException {
        TransactionController transactionController = new TransactionController(prop);
        Response res= transactionController.depositFromSystemToagent();
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String messageActual = jsonPath.get("message");
        String messageexpected ="Deposit successful";
        Assert.assertTrue(messageActual.contains(messageexpected));
    }
   @Test(priority = 2, description = "Agent to customer1 1500tk transaction")
    public void depositAgentToCustomer1() throws IOException, ParseException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(0);
        String customer1Number =(String) userObj.get("phoneNumber");
        JSONObject userObj2 = (JSONObject) jsonArray.get(2);
        String agentNumber = (String) userObj2.get("phoneNumber");
        int amount = 1500;
        TransactionController transactionController = new TransactionController(prop);
        Response res = transactionController.depositFromAgenttoCustomer1(agentNumber, customer1Number, amount);
        System.out.println(res.asString());
       JsonPath jsonPath = res.jsonPath();
       String messageActual = jsonPath.get("message");
       String messageexpected ="Deposit successful";
       Assert.assertTrue(messageActual.contains(messageexpected));
    }
    @Test(priority = 3, description = "Withdraw 500tk by the customer to the agent")
    public void withdrawByCustomer1() throws IOException, ParseException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(0);
        String customer1Number =(String) userObj.get("phoneNumber");
        JSONObject userObj2 = (JSONObject) jsonArray.get(2);
        String agentNumber = (String) userObj2.get("phoneNumber");
        int amount = 500;
        TransactionController transactionController = new TransactionController(prop);
        Response res = transactionController.withdrawFromAgent(customer1Number,agentNumber,amount);
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String messageActual = jsonPath.get("message");
        String messageexpected ="Withdraw successful";
        Assert.assertTrue(messageActual.contains(messageexpected));
    }
    @Test(priority = 4, description = "Withdraw 500 tk by the customer to the agent")
    public void sendMoneyToCustomer2() throws IOException, ParseException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(0);
        String customer1Number =(String) userObj.get("phoneNumber");
        JSONObject userObj2 = (JSONObject) jsonArray.get(1);
        String customer2Number = (String) userObj2.get("phoneNumber");
        int amount = 500;
        TransactionController transactionController = new TransactionController(prop);
        Response res = transactionController.sendMoneyToCustomer2(customer1Number,customer2Number,amount);
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String messageActual = jsonPath.get("message");
        String messageexpected ="Send money successful";
        Assert.assertTrue(messageActual.contains(messageexpected));
    }
    @Test(priority = 5, description = "Pay 100 tk to any merchant by the recipient customer")
    public void paymentToMerchant() throws IOException, ParseException, InterruptedException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj = (JSONObject) jsonArray.get(1);
        String customer2Number =(String) userObj.get("phoneNumber");
        String merchantNumber = "01301831905";
        int amount = 100;
        TransactionController transactionController = new TransactionController(prop);
        Response res = transactionController.paymentToMerchant(customer2Number,merchantNumber,amount);
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String messageActual = jsonPath.get("message");
        String messageexpected ="Payment successful";
        Assert.assertTrue(messageActual.contains(messageexpected));
    }
   @Test(priority = 6, description = "Check customer balance")
    public void checkCustomerBalance() throws IOException, ParseException, InterruptedException {
        TransactionController transactionController = new TransactionController(prop);
        Response res = transactionController.checkCustomerBalance();
        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String messageActual = jsonPath.get("message");
        String messageexpected ="User balance";
        Assert.assertTrue(messageActual.contains(messageexpected));
    }
    @AfterMethod
    public void delay() throws InterruptedException {
        Thread.sleep(2000);
    }


}
