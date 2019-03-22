package com.example.jsonreadapplicationfromurl;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Uri uri1);
    }

    private final OnItemClickListener listener;

    private List<ImageDetails> imageDetailsList;
    private Context context;
    Uri uri1,uri2;

    private static int currentPosition = 0;

    public ImageAdapter(List<ImageDetails> imageDetailsList, Context context, OnItemClickListener listener) {
        this.imageDetailsList = imageDetailsList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_image, parent, false);
        return new ImageViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, final int position) {
        ImageDetails imageDetails = imageDetailsList.get(position);
        uri1 = Uri.parse(imageDetails.getThumbnailUrl());
        uri2= Uri.parse(imageDetails.getUrl());
        imageViewHolder.bind(uri2, listener);
        Glide.with(context).load(uri1).thumbnail(0.1f).into(imageViewHolder.imageViewURL);

        imageViewHolder.linearLayout.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return imageDetailsList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        ImageView imageViewURL;
        ImageView imageViewShowURL;
        LinearLayout linearLayout;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageViewURL = itemView.findViewById(R.id.textViewURL);
            imageViewShowURL = itemView.findViewById(R.id.imageView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
        }

        public void bind(final Uri uri2, final OnItemClickListener listener) {
            imageViewURL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(uri2);
                }
            });
        }
    }
}
