package com.umang_rathod.hms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogoutConfirmation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogoutConfirmation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LogoutConfirmation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogoutConfirmation.
     */
    // TODO: Rename and change types and number of parameters
    public static LogoutConfirmation newInstance(String param1, String param2) {
        LogoutConfirmation fragment = new LogoutConfirmation();
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

        View parentHolder = inflater.inflate(R.layout.fragment_logout_confirmation, container, false);


        RelativeLayout cancelBtn = parentHolder.findViewById(R.id.cancel), confirmBtn = parentHolder.findViewById(R.id.confirm);


        // Cancel
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new Fragment4());
                fragmentTransaction.commit();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences settings = getActivity().getSharedPreferences("DESIGNPROJECTSEMVUSERLOGGEDINORNOT", Context.MODE_PRIVATE);
                settings.edit().clear().commit();

                Toast.makeText(getActivity(), "You have successfully logged out!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });



        return parentHolder;
    }





}