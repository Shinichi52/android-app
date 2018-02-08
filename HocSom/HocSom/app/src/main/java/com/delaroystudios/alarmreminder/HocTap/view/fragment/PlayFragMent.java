package com.delaroystudios.alarmreminder.HocTap.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delaroystudios.alarmreminder.R;

/**
 * Created by MyPC on 19/01/2018.
 */

public class PlayFragMent extends Fragment {



    public static PlayFragMent newInstance(String param1, String param2) {
        PlayFragMent fragment = new PlayFragMent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play,container,false);
        return view;
    }


}