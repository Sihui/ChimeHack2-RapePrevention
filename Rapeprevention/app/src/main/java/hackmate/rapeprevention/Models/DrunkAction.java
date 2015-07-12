package hackmate.rapeprevention.Models;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;

import hackmate.rapeprevention.Controller.TextMessageController;
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
    private static boolean confirmPickedup;
    private static boolean sentToParent;
    private static int count;
    final static long intervalTime = 10000; // 5min
    final static int MAX_TRIES = 3;
    final static Handler HANDLER = new Handler();
    final static String USERNAME = Model.getModel().userName.get();
    static ArrayList<ContactInfo> numbers = Model.getModel().contactInfos; // new String[]{"14157135782"};//{"18329752606", "17022907075", "18329146226"}; // set by user, get from model

    public static void drunkAction() {
        sentToParent = false;
        confirmPickedup = false;
        count = 0;
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
                    if(!confirmPickedup && !sentToParent) {
                        HANDLER.postDelayed(this, intervalTime);
                    }
                }
            }, 0);
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };

    private static Callback<Rescued> pickupConfirmedCallback = new Callback<Rescued>() {
        @Override
        public void success(Rescued rescued, Response response) {
            confirmPickedup = rescued.pickedUp;
            if(!confirmPickedup && !sentToParent) {
                count++;
                Context context = MyApplication.getAppContext();
                String address = GPSTracker.getAddress(context);
                if(count < 4){
                    for (int i = 0; i < numbers.size() - 1; i++) {
                        ContactInfo cInfo = numbers.get(i);
                        String number = cInfo.number;
                        addrText = "Hi " + cInfo.getName() + USERNAME + " is drunk, please pick her up @" + address;
                        System.out.println("Sending text to a friend " + addrText + confirmText + denyText + discText);
                        SMS.sendSMS(number, addrText, context);
                        SMS.sendSMS(number, confirmText, context);
                        SMS.sendSMS(number, denyText, context);
                        SMS.sendSMS(number, discText, context);
                    }
                } else {
                    String text = USERNAME + " is drunk, please pick her up @" + address;
                    System.out.println("Sending text to a parent " + text);
                    SMS.sendSMS(numbers.get(numbers.size() - 1).number, text, context);
                    sentToParent = true;
                }
            }else{
                TextMessageController.get().confirmPickup();
            }
        }

        @Override
        public void failure(RetrofitError error) {
            System.out.println(error);
        }
    };
}