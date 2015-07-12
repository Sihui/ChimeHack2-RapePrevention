package hackmate.rapeprevention.Models;

import android.content.Context;
import android.os.Handler;

import hackmate.rapeprevention.MyApplication;

public class DrunkAction {
    final static long intervalTime = 3000; // 5min
    final static int MAX_TRIES = 3;
    final static Handler HANDLER = new Handler();
    final static String USERNAME = "Rachel"; // get from model
    static String[] numbers = new String[]{"18329752606","17022907075", "18329146226"}; // set by user, get from model
    final static String PARENT_NUMBER = "18329752606";

    public static void drunkAction(){
        final String[] links = getLinks();
            HANDLER.postDelayed(new Runnable() {
                int count = 0;
                @Override
                public void run() {
                    count++;
                    Context context = MyApplication.getAppContext();
                    String address = GPSTracker.getAddress(context);
                    if(count == 3 && !pickupConfirmed()){
                        String text = USERNAME + " is drunk, please pick her up @" + address;
                        System.out.println("Sending text to a parent" + text);
                        //SMS.sendSMS(PARENT_NUMBER, text, context);
                        return;
                    }else if(pickupConfirmed()){
                        return;
                    }
                    String addrText = USERNAME + " is drunk, please pick her up @" + address;
                    String confirmText = "Confirm Pickup: " + links[0];
                    String denyText = "Deny Pickup: " + links[1];
                    String discText = "Join the Discussion: " + links[2];
                    for(String number:numbers){
                        System.out.println("Sending text to a friend");
                        /*SMS.sendSMS(number, addrText, context);
                        SMS.sendSMS(number, confirmText, context);
                        SMS.sendSMS(number, denyText, context);
                        SMS.sendSMS(number, discText, context);*/
                    }
                    HANDLER.postDelayed(this, intervalTime);
                }
            }, intervalTime);


    }

    private static String[] getLinks(){
        String jsonStr = "{\"confirmLink\":\"https://88d800c0.ngrok.io/yes?rescueId=0\",\"id\":\"0\",\"denyLink\":\"https://88d800c0.ngrok.io/no\",\"chatLink\":\"https://plus.google.com/hangouts/_/gwyoi6embwxabzvcibxauszbaea\"}";
        String [] links = JSONReader.getLinksFromJSON(jsonStr);

        return links;
    }

    private static boolean pickupConfirmed(){
        boolean pickupConfirmed = false; // call backend sever
        return pickupConfirmed;
    }
}
