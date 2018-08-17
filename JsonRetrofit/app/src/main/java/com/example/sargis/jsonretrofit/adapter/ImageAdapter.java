package com.example.sargis.jsonretrofit.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sargis.jsonretrofit.fragment.ImageDialogFragment;
import com.example.sargis.jsonretrofit.R;
import com.example.sargis.jsonretrofit.api.Result;
import com.example.sargis.jsonretrofit.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    public static final String FR_KEY = "fr_key";
    private static final String FR_TAG = "tag";
    private List<Result> list = Collections.emptyList();
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context)
                .inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {
        holder.imageTitle.setText(list.get(position).getTitle());
        Picasso.get().load(list.get(position).getThumbnailUrl()).into(holder.smallImage);

        holder.smallImage.setOnClickListener(new View.OnClickListener() {
            private final FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();

            @Override
            public void onClick(View v) {
                final Bundle bundle = new Bundle();
                bundle.putInt(FR_KEY, holder.getAdapterPosition());
                final ImageDialogFragment imageDialogFragment = new ImageDialogFragment();
                imageDialogFragment.setArguments(bundle);
                imageDialogFragment.show(fragmentManager, FR_TAG);
                imageDialogFragment.getList(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Result> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView smallImage;
        private TextView imageTitle;

        ImageViewHolder(View itemView) {
            super(itemView);
            smallImage = itemView.findViewById(R.id.small_image);
            imageTitle = itemView.findViewById(R.id.image_title);
        }
    }
}
