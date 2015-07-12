package hackmate.rapeprevention.Models;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface BackendService {
    @GET("/getLinks")
    void getLinks(Callback<Links> cb);

    @GET("/isRescued?rescueId={id}")
    void isRescued(@Path("id") String id, Callback<Rescued> cb);
}
