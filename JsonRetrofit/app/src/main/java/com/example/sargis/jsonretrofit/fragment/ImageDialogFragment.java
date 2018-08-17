package com.example.sargis.jsonretrofit.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sargis.jsonretrofit.R;
import com.example.sargis.jsonretrofit.api.Result;
import com.example.sargis.jsonretrofit.adapter.ImageAdapter;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class ImageDialogFragment extends DialogFragment {
    private List<Result> list = Collections.emptyList();

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.image_df, container, false);
        final ImageView bigImage = view.findViewById(R.id.image_fragment);
        if (getArguments() != null) {
            int position = getArguments().getInt(ImageAdapter.FR_KEY);
            Picasso.get().load(list.get(position).getUrl()).into(bigImage);
        }
        return view;
    }

    public void getList(List<Result> list) {
        this.list = list;
    }
}
