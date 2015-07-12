package hackmate.rapeprevention.Models;

import android.content.Context;
import android.os.Handler;

import java.util.*;
import java.util.Observable;

import hackmate.rapeprevention.MyApplication;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DrunkAction{
    static Links links;
    final static long intervalTime = 3000; // 5min
    final static int MAX_TRIES = 3;
    final static Handler HANDLER = new Handler();
    final static String USERNAME = "Rachel"; // get from model
    static String[] numbers = new String[]{"18329752606", "17022907075", "18329146226"}; // set by user, get from model
    final static String PARENT_NUMBER = "18329752606";

    public static void drunkAction() {
        getLinks();
           /* HANDLER.postDelayed(new Runnable() {
                int count = 0;
                String backendRequestId;

                @Override
                public void run() {
                    count++;
                    Context context = MyApplication.getAppContext();
                    String address = GPSTracker.getAddress(context);
                    if (count == 3 && !pickupConfirmed(backendRequestId)) {
                        String text = USERNAME + " is drunk, please pick her up @" + address;
                        System.out.println("Sending text to a parent" + text);
                        //SMS.sendSMS(PARENT_NUMBER, text, context);
                        return;
                    } else if (pickupConfirmed(backendRequestId)) {
                        return;
                    }
                    String addrText = USERNAME + " is drunk, please pick her up @" + address;
                    String confirmText = "Confirm Pickup: " + links.confirmLink;
                    String denyText = "Deny Pickup: " + links.denyLink;
                    String discText = "Join the Discussion: " + links.chatLink;
                    backendRequestId = links.id;
                    for (String number : numbers) {
                        System.out.println("Sending text to a friend");
                        /*SMS.sendSMS(number, addrText, context);
                        SMS.sendSMS(number, confirmText, context);
                        SMS.sendSMS(number, denyText, context);
                        SMS.sendSMS(number, discText, context);
                    }
                    HANDLER.postDelayed(this, intervalTime);
                }
            }, intervalTime);*/

    }

    public static Callback<Links> getLinksCallback = new Callback<Links>() {
        @Override
        public void success(Links lks, Response response) {
            links = lks;
            HANDLER.postDelayed(new Runnable() {
                int count = 0;
                String backendRequestId;

                @Override
                public void run() {
                    count++;
                    Context context = MyApplication.getAppContext();
                    String address = GPSTracker.getAddress(context);
                    if (count == 3 && !pickupConfirmed(backendRequestId)) {
                        String text = USERNAME + " is drunk, please pick her up @" + address;
                        System.out.println("Sending text to a parent" + text);
                        //SMS.sendSMS(PARENT_NUMBER, text, context);
                        return;
                    } else if (pickupConfirmed(backendRequestId)) {
                        return;
                    }
                    String addrText = USERNAME + " is drunk, please pick her up @" + address;
                    String confirmText = "Confirm Pickup: " + links.confirmLink;
                    String denyText = "Deny Pickup: " + links.denyLink;
                    String discText = "Join the Discussion: " + links.chatLink;
                    backendRequestId = links.id;
                    for (String number : numbers) {
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

        @Override
        public void failure(RetrofitError error) {

        }
    };

    private static void getLinks(){
        BackendRequest.getLinks(getLinksCallback);
    }
    private static boolean pickupConfirmed(String id){
        return false;
        /*Callback<Rescued> cb = new Callback<Rescued>() {
            @Override
            public void success(Rescued rescued, Response response) {
                return;
            }
            @Override
            public void failure(RetrofitError error) {
                System.out.println("f " + error);
            }
        };
        BackendRequest.isRescued(id, cb);
        return ((Rescued)cb).rescued;*/
    }
}