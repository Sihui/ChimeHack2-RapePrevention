package hackmate.rapeprevention.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sihui on 7/11/15.
 */
public class JSONReader {
    public static String[] getLinksFromJSON(String strJson){
        try {
            JSONObject  jsonRootObject = new JSONObject(strJson);
            String yesLink = jsonRootObject.getString("confirmLink");
            String denyLink = jsonRootObject.getString("denyLink");
            String chatLink = jsonRootObject.getString("chatLink");
            return new String[]{ yesLink, denyLink, chatLink};

        } catch (JSONException e){
            System.out.println("getLinksFromJSON ERROR: " + e);
            return new String[]{};
        }
    }

    public static boolean getConfirmFromJSON(String strJson){
        try {
            JSONObject  jsonRootObject = new JSONObject(strJson);
            return jsonRootObject.getBoolean("pickedUp");
        } catch (JSONException e){
            System.out.println("getLinksFromJSON ERROR: " + e);
            return false;
        }

    }
}
