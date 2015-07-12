package hackmate.rapeprevention.Models;

import retrofit.Callback;
import retrofit.RestAdapter;

public class BackendRequest {
    final static String path = "https://bc3a58a9.ngrok.io";//"https://88d800c0.ngrok.io";//"https://17476614.ngrok.io";//
    public static void getLinks(Callback<Links> cb){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(path)
                .build();
        BackendService service = restAdapter.create(BackendService.class);
        service.getLinks(cb);
    }

    public static void isRescued(String id, Callback<Rescued> cb){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(path)
                .build();
        BackendService service = restAdapter.create(BackendService.class);
        service.isRescued(id, cb);
    }
}
