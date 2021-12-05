package com.example.calendarapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Assignments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Assignments extends Fragment {
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView eventRecView;
    ArrayList events;
    EventsRecyclerViewAdapter eventRecViewAdapter;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Assignments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Assignments.
     */
    // TODO: Rename and change types and number of parameters
    public static Assignments newInstance(String param1, String param2) {
        Assignments fragment = new Assignments();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view = inflater.inflate(R.layout.fragment_assignments, container, false);
            }
        });
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        eventRecView = getActivity().findViewById(R.id.assignmentRecView);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        events = dataBaseHelper.getAssignments();
        //DEBUG
        eventRecViewAdapter = new EventsRecyclerViewAdapter(events, getActivity());
        eventRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventRecView.setAdapter(eventRecViewAdapter);
    }
}