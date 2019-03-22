package com.example.jsonreadapplicationfromurl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import mvp.ImageDetailView;
import presenter.ImageDetailsPresenter;
import retrofit2.Response;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ImageDetailFragment extends Fragment implements ImageDetailView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ImageAdapter adapter;
    private ImageDetailsPresenter mPresenter;
    List<ImageDetails> imageDetailsList;
    private Drawable yourDrawable;
    ImageView imageView;
    Uri url;

    public static ImageDetailFragment newInstance() {
        final ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
        return imageDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment, container, false);
        ButterKnife.bind(this, view);
        imageDetailsList = new ArrayList<>();
        mPresenter = new ImageDetailsPresenter(this);
        mPresenter.getImagesDetails();
        return view;
    }

    @Override
    public void showImageDetails(Response<List<ImageDetails>> response) {
        List<ImageDetails> imageDetailsList = response.body();
        String[] images = new String[imageDetailsList.size()];
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        for (int i = 0; i < imageDetailsList.size(); i++) {
            images[i] = imageDetailsList.get(i).getThumbnailUrl();
        }
        adapter = new ImageAdapter(imageDetailsList, getContext(), new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Uri uri) {
                onCreateDialog(uri);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void onCreateDialog(Uri uri) {
        AlertDialog.Builder alertadd = new AlertDialog.Builder(getContext());
        alertadd.setTitle("Image Dialog");
        LayoutInflater factory = LayoutInflater.from(getContext());
        final View view = factory.inflate(R.layout.image_layout, null);
        ImageView imageView = view.findViewById(R.id.image);
        Picasso.get().load(uri).into(imageView);
        alertadd.setView(view);
        alertadd.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {

            }
        });

        alertadd.show();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Error is loading the images", Toast.LENGTH_SHORT).show();
    }
}
