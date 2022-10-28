package com.umang_rathod.hms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class Fragment1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Fragment1() {}

    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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
        View parentHolder = inflater.inflate(R.layout.fragment_1, container, false);

        //Fragment management
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        // ArrayList (Collection of data for doctors view)
        ArrayList<DoctorModel> allDoctors = new ArrayList<>();
        for (int i=1; i<=10; i++){
            allDoctors.add(new DoctorModel(R.drawable.dp, "Umang Rathod", "MS", "Heart Surgeon", "12 Yrs+"));
        }
        RecyclerView doctorsView = parentHolder.findViewById(R.id.doctorsList);
        doctorsView.setLayoutManager(new GridLayoutManager(getActivity(), 10));
        DoctorCardAdapter adapter1 = new DoctorCardAdapter(getActivity(), allDoctors);
        doctorsView.setAdapter(adapter1);


        // Hooks
        TextView greet = parentHolder.findViewById(R.id.greet),
                name = parentHolder.findViewById(R.id.name),
                quote = parentHolder.findViewById(R.id.quote);
        LinearLayout takeAppointment = parentHolder.findViewById(R.id.take_appointment);



        int currentHour = new Date().getHours();
        String greeting = currentHour < 12 ? "Good Morning," : currentHour < 18 ? "Good Afternoon," : "Good Evening,";

        greet.setText(greeting);
        name.setText("Umang Rathod!");
        quote.setText(R.string.quote1);

        takeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.frameLayout, new Fragment2());
                fragmentTransaction.commit();
            }
        });




        return parentHolder;
    }
}