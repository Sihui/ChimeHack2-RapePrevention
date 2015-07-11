package hackmate.rapeprevention.Models;

import android.telephony.SmsManager;
import android.widget.Toast;

public class SMS {
    public static boolean sendSMS(String number, String text){
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
