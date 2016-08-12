package com.vince.nowait.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vince.nowait.MainActivity;
import com.vince.nowait.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantViewFragment extends Fragment {


    public RestaurantViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_restaurant_view, container, false);

        TextView title = (TextView) fragmentLayout.findViewById(R.id.viewRestaurantTitle);
        TextView message = (TextView) fragmentLayout.findViewById(R.id.viewRestaurantMessage);
        ImageView icon = (ImageView) fragmentLayout.findViewById(R.id.viewRestaurantIcon);

        Intent intent = getActivity().getIntent();

        title.setText(intent.getExtras().getString(MainActivity.RESTAURANT_TITLE_EXTRA));
        message.setText(intent.getExtras().getString(MainActivity.RESTAURANT_MESSAGE_EXTRA));

        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.RESTAURANT_CATEGORY_EXTRA);
        icon.setImageResource(Note.categoryToDrawable(noteCat));
        // Inflate the layout for this fragment
        return fragmentLayout;
    }

}
