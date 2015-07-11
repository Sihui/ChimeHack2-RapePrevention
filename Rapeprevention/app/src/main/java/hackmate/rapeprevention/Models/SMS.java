package hackmate.rapeprevention.Models;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SMS {
    public static boolean sendSMS(String number, String text, Context context){
        final GPSTracker  mGPS = new GPSTracker(context);
        if(mGPS.canGetLocation ){
            mGPS.getLocation();
            text+="\n Location : ( "+mGPS.getLatitude()+" , "+mGPS.getLongitude()+" )";
        }
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, text, null, null);
        }catch (Exception e) {
            System.out.println("SMS faild, please try again later!");
            return false;
        }
        return true;
    }
}
