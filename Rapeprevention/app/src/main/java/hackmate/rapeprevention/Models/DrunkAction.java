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
    private static String backendRequestId;
    private static String addrText;
    private static String confirmText;
    private static String denyText;
    private static String discText;
    private static boolean sentToParent;
    private static int count;
    final static long intervalTime = 3000; // 5min
    final static int MAX_TRIES = 3;
    final static Handler HANDLER = new Handler();
    final static String USERNAME = "Rachel"; // get from model
    static String[] numbers = new String[]{"14157135782"};//{"18329752606", "17022907075", "18329146226"}; // set by user, get from model
    final static String PARENT_NUMBER = "14157135782";

    public static void drunkAction() {
        sentToParent = false;
        getLinks();
    }

    private static void getLinks(){
        BackendRequest.getLinks(getLinksCallback);
    }
    private static void pickupConfirmed(){
        BackendRequest.isRescued(backendRequestId, pickupConfirmedCallback);
    }

    private static Callback<Links> getLinksCallback = new Callback<Links>() {
        @Override
        public void success(Links links, Response response) {
            confirmText = "Confirm Pickup: " + links.confirmLink;
            denyText = "Deny Pickup: " + links.denyLink;
            discText = "Join the Discussion: " + links.chatLink;
            backendRequestId = links.id;
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pickupConfirmed();
                    HANDLER.postDelayed(this, intervalTime);
                }
            }, intervalTime);
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };

    private static Callback<Rescued> pickupConfirmedCallback = new Callback<Rescued>() {
        @Override
        public void success(Rescued rescued, Response response) {
            if(!rescued.rescued && !sentToParent) {
                count++;
                Context context = MyApplication.getAppContext();
                String address = GPSTracker.getAddress(context);
                addrText = USERNAME + " is drunk, please pick her up @" + address;
                if(count < 4){
                    for (String number : numbers) {
                        System.out.println("Sending text to a friend " + addrText + confirmText + denyText + discText);
                        SMS.sendSMS(number, addrText, context);
                        SMS.sendSMS(number, confirmText, context);
                        SMS.sendSMS(number, denyText, context);
                        SMS.sendSMS(number, discText, context);
                    }
                } else {
                    String text = USERNAME + " is drunk, please pick her up @" + address;
                    System.out.println("Sending text to a parent " + text);
                    SMS.sendSMS(PARENT_NUMBER, text, context);
                    sentToParent = true;
                }
            }
        }

        @Override
        public void failure(RetrofitError error) {
            System.out.println(error);
        }
    };
}