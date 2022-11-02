package com.umang_rathod.hms;


import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorProfile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DoctorProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoctorProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorProfile newInstance(String param1, String param2) {
        DoctorProfile fragment = new DoctorProfile();
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
        View parentHolder = inflater.inflate(R.layout.fragment_doctor_profile, container, false);

        ImageView profilePicture = parentHolder.findViewById(R.id.profile_picture);
        TextView name = parentHolder.findViewById(R.id.name),
                degreeAndSpeciality = parentHolder.findViewById(R.id.degree_and_speciality),
                experience = parentHolder.findViewById(R.id.experience);

        RelativeLayout call = parentHolder.findViewById(R.id.call), message = parentHolder.findViewById(R.id.message);

        //Shared Preferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DESIGNPROJECTSEMVTEMPORARYDOCTORDETAILSSAVE", MODE_PRIVATE);


        // Data's From another screen
        String drName = sharedPreferences.getString("name", ""),
                drDegree = sharedPreferences.getString("degree", ""),
                drSpeciality = sharedPreferences.getString("speciality", ""),
                drMobile = sharedPreferences.getString("mobile", ""),
                drWMobile = sharedPreferences.getString("wMobile", "");
        int imgid = sharedPreferences.getInt("imgid", 0),
                drExperience = sharedPreferences.getInt("experience", 0);

        // Setting data
        profilePicture.setImageResource(imgid);
        name.setText("Dr. " + drName);
        degreeAndSpeciality.setText(drDegree + " | " + drSpeciality );
        experience.setText(drExperience + " Yrs+ Experience");

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CALL_PHONE }, 10);
                }
                else{
                    Toast.makeText(getActivity(), "Calling to " + drName, Toast.LENGTH_SHORT).show();
                }
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + drMobile));
                startActivity(callIntent);
            }
        });


        return parentHolder;
    }
}