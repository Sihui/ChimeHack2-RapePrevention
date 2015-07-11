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
        final GPSTracker  mGPS = new GPSTracker(context);
        if(mGPS.canGetLocation ){
            mGPS.getLocation();
            text+="\n Location : ( "+mGPS.getLatitude()+" , "+mGPS.getLongitude()+" )";
            List<Address> addresses = getAddress(mGPS.getLatitude(), mGPS.getLongitude(), context);
            if (addresses.size() > 0){
                Address addr = addresses.get(0);
                String address = addr.getAddressLine(0);
                String city = addr.getAddressLine(1);
                String country = addr.getAddressLine(2);
                text += address + city + country;
                System.out.println("here");
                System.out.print(text);
            }
        }
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, text, null, null);
        }catch (Exception e) {
            System.out.println("SMS failed, please try again later!");
            return false;
        }
        return true;
    }

    private static List<Address> getAddress(double latitude, double longitude, Context context) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context);
            if (latitude != 0 || longitude != 0) {
                addresses = geocoder.getFromLocation(latitude ,
                        longitude, 1);
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getAddressLine(1);
                String country = addresses.get(0).getAddressLine(2);
                Log.d("TAG", "address = " + address + ", city =" + city + ", country = " + country);
                return addresses;
            } else {
                /*Toast.makeText(context, "latitude and longitude are null",
                        Toast.LENGTH_LONG).show();*/
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
