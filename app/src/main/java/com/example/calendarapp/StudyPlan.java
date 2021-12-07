package com.example.calendarapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudyPlan #newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudyPlan extends Fragment {
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button addEvent;
    RecyclerView eventRecView;
    ArrayList events;
    EventsRecyclerViewAdapter eventRecViewAdapter;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudyPlan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudyPlan.
     */
    // TODO: Rename and change types and number of parameters
    public static StudyPlan newInstance(String param1, String param2) {
        StudyPlan fragment = new StudyPlan();
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
        view = inflater.inflate(R.layout.fragment_study_plan, container, false);

        eventRecView = view.findViewById(R.id.study_planRecView);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        events = dataBaseHelper.getStudyplan();
        //DEBUG
        eventRecViewAdapter = new EventsRecyclerViewAdapter(events, getContext());
        eventRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventRecView.setAdapter(eventRecViewAdapter);

        addEvent=view.findViewById(R.id.btnaddstudyplan);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Add_Event.class);
                intent.putExtra("type","studyplan");
                getActivity().startActivity(intent);
            }
        });

        return view;
    }


}