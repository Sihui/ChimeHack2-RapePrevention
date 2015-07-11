package hackmate.rapeprevention.Models;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class SMS {
    public static boolean sendSMS(String number, String text, Context context){
        /*final GPSTracker mGPS = new GPSTracker(context);
        if(mGPS.canGetLocation ){
            mGPS.getLocation();
            text+="\n Location : ( "+mGPS.getLatitude()+" , "+mGPS.getLongitude()+" )";
        }*/
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, text, null, null);
        }catch (Exception e) {
            System.out.println("SMS failed, please try again later!");
            return false;
        }
        return true;
    }
}