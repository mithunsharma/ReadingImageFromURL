package presenter;

import com.example.jsonreadapplicationfromurl.ImageDetails;
import mvp.ImageDetailView;
import retrofit.ApiCall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class ImageDetailsPresenter {

    private ImageDetails imageDetails;
    private ImageDetailView mView;

    public ImageDetailsPresenter(ImageDetailView mView) {
        this.imageDetails = new ImageDetails();
        this.mView = mView;
    }

    public void getImagesDetails(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiCall.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCall apiCall = retrofit.create(ApiCall.class);
        Call<List<ImageDetails>> call = apiCall.getPhotos();

        call.enqueue(new Callback<List<ImageDetails>>() {
            @Override
            public void onResponse(Call<List<ImageDetails>> call, retrofit2.Response<List<ImageDetails>> response) {
                mView.showImageDetails(response);
            }

            @Override
            public void onFailure(Call<List<ImageDetails>> call, Throwable t) {
                mView.showError();
            }
        });
    }
}
