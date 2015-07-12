package hackmate.rapeprevention.Models;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface BackendService {
    @GET("/getLinks")
    void getLinks(Callback<Links> cb);

    @GET("/isRescued")
    void isRescued(@Query("rescueId") String id, Callback<Rescued> cb);
}
