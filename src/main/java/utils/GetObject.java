package utils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetObject {
    public String Getstring(JSONObject dataJson, String a) {
        String object = dataJson.getString(a);
        return object;
    }
    public JSONObject getJasonobject (JSONObject dataJson,String a){
        JSONObject object = dataJson.getJSONObject(a);
        return object;
    }
    public JSONArray getArray(JSONObject dataJson,String a) {
        JSONArray array = dataJson.getJSONArray(a);
        return array;
    }


}
