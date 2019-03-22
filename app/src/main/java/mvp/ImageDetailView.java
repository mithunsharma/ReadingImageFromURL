package mvp;

import com.example.jsonreadapplicationfromurl.ImageDetails;

import java.util.List;

public interface ImageDetailView {
    void showImageDetails(retrofit2.Response<List<ImageDetails>> response);

    void showError();
}
