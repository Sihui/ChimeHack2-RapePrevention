package hackmate.rapeprevention.Models;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;

public class BackendRequest {
    final static String path = "https://88d800c0.ngrok.io";
    public static void getLinks(Callback<Links> cb){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(path)
                .build();
        BackendService service = restAdapter.create(BackendService.class);
        service.getLinks(cb);
    }
}
