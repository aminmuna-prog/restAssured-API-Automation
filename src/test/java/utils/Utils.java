package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void setEnvVar(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }
    public static int generateRandomId(int min, int max){
        double randomId = Math.random() * (max - min) + min;
        return (int) randomId;
    }
    public static void saveUserInfo(String filePath, JSONObject jsonObject) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(filePath));
        jsonArray.add(jsonObject);
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();

    }

//    public  static void saveUserInfo(model.UserModel userModel) throws IOException, ParseException {
//        String filePath = "./src/test/resources/users.json";
//        JSONParser jsonParser = new JSONParser();
//        Object obj = jsonParser.parse(new FileReader(filePath));
//        JSONArray jsonArray = (JSONArray) obj;
//        JSONObject userInfo = new JSONObject();
//
//        userInfo.put("name",userModel.getName());
//        userInfo.put("email",userModel.getEmail());
//        userInfo.put("password",userModel.getPassword());
//        userInfo.put("phone_number",userModel.getPhone_number());
//        userInfo.put("nid",userModel.getNid());
//        userInfo.put("role",userModel.getRole());
//
//        jsonArray.add(userInfo);
//        FileWriter file = new FileWriter(filePath);
//        file.write(jsonArray.toJSONString());
//        file.flush();
//        file.close();
//
//    }
//    public static String getPhoneNumber(int index) throws IOException, ParseException {
//        String filePath = "./src/test/resources/users.json";
//        JSONParser jsonParser = new JSONParser();
//        Object obj = jsonParser.parse(new FileReader(filePath));
//        JSONArray jsonArray = (JSONArray) obj;
//        int arraySize = jsonArray.size();
//        int userIndex = arraySize - index;
//        JSONObject arrayObject = (JSONObject) jsonArray.get(userIndex);
//        String phone_number = arrayObject.get("phone_number").toString();
//        return phone_number;
//
//    }
}
