package CNVP_Lab2;

import org.json.JSONObject;


public class JsonParser {
    public static String ConvertToJson(int input) {
        JSONObject jsonObject = new JSONObject();

        if (input == 1) {
            jsonObject.put("operation", "GetNumberOfMyBrigade");
        }
    }


}

