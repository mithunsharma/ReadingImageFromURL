package retrofit;

import com.example.jsonreadapplicationfromurl.ImageDetails;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ApiCall {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("photos")
    Call<List<ImageDetails>> getPhotos();
}
