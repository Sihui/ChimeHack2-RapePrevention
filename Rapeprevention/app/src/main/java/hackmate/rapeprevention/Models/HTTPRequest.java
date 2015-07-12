package hackmate.rapeprevention.Models;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Sihui on 7/11/15.
 */
public interface HTTPRequest {
    @GET("/users/{user}/repos")
    List<String> listRepos(@Path("user") String user);
}
