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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        //Get data from the shared preferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DESIGNPROJECTSEMVUSERDETAILS", MODE_PRIVATE);
        String NAME = sharedPreferences.getString("name", "");


        // Hooks
        TextView greet = parentHolder.findViewById(R.id.greet),
                name = parentHolder.findViewById(R.id.name),
                quote = parentHolder.findViewById(R.id.quote);
        LinearLayout takeAppointment = parentHolder.findViewById(R.id.take_appointment);



        int currentHour = new Date().getHours();
        String greeting = currentHour < 12 ? "Good Morning," : currentHour < 18 ? "Good Afternoon," : "Good Evening,";

        greet.setText(greeting + " !");
        name.setText(NAME);
        quote.setText(R.string.quote1);

        takeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.frameLayout, new Fragment2());
                fragmentTransaction.commit();
            }
        });

        String[] blogUrls = {"https://www.webmd.com/balance/stress-management/stress-management",
                            "https://www.gundersenhealth.org/health-wellness/be-well/6-natural-ways-to-prevent-diabetes-before-it-starts",
                            "https://blog.decathlon.in/articles/20-best-ways-to-gain-weight-fast-and-safe-in-a-week",
                            "https://www.heart.org/en/health-topics/heart-attack/about-heart-attacks",
                            "https://www.mayoclinic.org/healthy-lifestyle/adult-health/in-depth/cancer-prevention/art-20044816"};


        // Blogs event handler
        handleBlogEvents(blogUrls, parentHolder);


        // All doctors details
        ArrayList<DoctorDetails> doctorDetails = new ArrayList<>();
        doctorDetails.add(new DoctorDetails
                ("Umang Rathod", "MS", "Neurosurgeon", 10, "6353324687","6353324687", R.drawable.dp));
        doctorDetails.add(new DoctorDetails
                ("Kunal Jain", "MBBS", "Sexologist",  8, "9950826033","6353324687", R.drawable.kunal_jain));
        doctorDetails.add(new DoctorDetails
                ("Anjali Parmar", "BDS", "Gynecologist",  5, "6353324687","6353324687", R.drawable.dp));
        doctorDetails.add(new DoctorDetails
                ("Pratik Mesariya", "BAMS", "Heart Surgeon",  6, "6353324687","6353324687", R.drawable.dp));
        doctorDetails.add(new DoctorDetails
                ("Rajesh Rathod", "BHMS","Heart Surgeon",  7, "6353324687","6353324687", R.drawable.dp));
        handleDoctorCards(parentHolder, doctorDetails);


        return parentHolder;
    }


    private void handleBlogEvents(String[] blogUrls, View parentHolder) {
        ImageView blog1Img = parentHolder.findViewById(R.id.blog1_img), blog2Img = parentHolder.findViewById(R.id.blog2_img),
                blog3Img = parentHolder.findViewById(R.id.blog3_img), blog4Img = parentHolder.findViewById(R.id.blog4_img),
                blog5Img = parentHolder.findViewById(R.id.blog5_img);

        blog1Img.setImageResource(R.drawable.stress_blog);
        blog2Img.setImageResource(R.drawable.diabetes_blog);
        blog3Img.setImageResource(R.drawable.gain_weight_blog);
        blog4Img.setImageResource(R.drawable.heart_attack_blog);
        blog5Img.setImageResource(R.drawable.cancer_blog);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        blog1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Browser.class);
                intent.putExtra("URL", blogUrls[0]);
                startActivity(intent);
            }
        });
        blog2Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Browser.class);
                intent.putExtra("URL", blogUrls[1]);
                startActivity(intent);
            }
        });
        blog3Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Browser.class);
                intent.putExtra("URL", blogUrls[2]);
                startActivity(intent);
            }
        });
        blog4Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Browser.class);
                intent.putExtra("URL", blogUrls[3]);
                startActivity(intent);
            }
        });
        blog5Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Browser.class);
                intent.putExtra("URL", blogUrls[4]);
                startActivity(intent);
            }
        });

    }

    private void handleDoctorCards(View parentHolder, ArrayList<DoctorDetails> doctorDetails) {
        RelativeLayout doctor1 = parentHolder.findViewById(R.id.doctor1),
                doctor2 = parentHolder.findViewById(R.id.doctor2),
                doctor3 = parentHolder.findViewById(R.id.doctor3),
                doctor4 = parentHolder.findViewById(R.id.doctor4),
                doctor5 = parentHolder.findViewById(R.id.doctor5);

        ImageView dr1DP = parentHolder.findViewById(R.id.dr1_dp),
                dr2DP = parentHolder.findViewById(R.id.dr2_dp),
                dr3DP = parentHolder.findViewById(R.id.dr3_dp),
                dr4DP = parentHolder.findViewById(R.id.dr4_dp),
                dr5DP = parentHolder.findViewById(R.id.dr5_dp);

        TextView dr1Name = parentHolder.findViewById(R.id.dr1_name_and_degree),
                dr2Name = parentHolder.findViewById(R.id.dr2_name_and_degree),
                dr3Name = parentHolder.findViewById(R.id.dr3_name_and_degree),
                dr4Name = parentHolder.findViewById(R.id.dr4_name_and_degree),
                dr5Name = parentHolder.findViewById(R.id.dr5_name_and_degree);

        TextView dr1Speciality = parentHolder.findViewById(R.id.dr1_speciality),
                dr2Speciality = parentHolder.findViewById(R.id.dr2_speciality),
                dr3Speciality = parentHolder.findViewById(R.id.dr3_speciality),
                dr4Speciality = parentHolder.findViewById(R.id.dr4_speciality),
                dr5Speciality = parentHolder.findViewById(R.id.dr5_speciality);

        TextView dr1Experience = parentHolder.findViewById(R.id.dr1_experience),
                dr2Experience = parentHolder.findViewById(R.id.dr2_experience),
                dr3Experience = parentHolder.findViewById(R.id.dr3_experience),
                dr4Experience = parentHolder.findViewById(R.id.dr4_experience),
                dr5Experience = parentHolder.findViewById(R.id.dr5_experience);

        // Setting components
        // DP
        dr1DP.setImageResource(doctorDetails.get(0).imgid);
        dr2DP.setImageResource(doctorDetails.get(1).imgid);
        dr3DP.setImageResource(doctorDetails.get(2).imgid);
        dr4DP.setImageResource(doctorDetails.get(3).imgid);
        dr5DP.setImageResource(doctorDetails.get(4).imgid);

        // Name and degree
        dr1Name.setText(doctorDetails.get(0).name + " ( " + doctorDetails.get(0).degree + " )");
        dr2Name.setText(doctorDetails.get(1).name + " ( " + doctorDetails.get(1).degree + " )");
        dr3Name.setText(doctorDetails.get(2).name + " ( " + doctorDetails.get(2).degree + " )");
        dr4Name.setText(doctorDetails.get(3).name + " ( " + doctorDetails.get(3).degree + " )");
        dr5Name.setText(doctorDetails.get(4).name + " ( " + doctorDetails.get(4).degree + " )");

        // Speciality
        dr1Speciality.setText(doctorDetails.get(0).speciality);
        dr2Speciality.setText(doctorDetails.get(1).speciality);
        dr3Speciality.setText(doctorDetails.get(2).speciality);
        dr4Speciality.setText(doctorDetails.get(3).speciality);
        dr5Speciality.setText(doctorDetails.get(4).speciality);

        // Experience
        dr1Experience.setText(doctorDetails.get(0).experience + "Yrs+ Experience");
        dr2Experience.setText(doctorDetails.get(1).experience + "Yrs+ Experience");
        dr3Experience.setText(doctorDetails.get(2).experience + "Yrs+ Experience");
        dr4Experience.setText(doctorDetails.get(3).experience + "Yrs+ Experience");
        dr5Experience.setText(doctorDetails.get(4).experience + "Yrs+ Experience");

        //Fragment management
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Set click listeners
        doctor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DESIGNPROJECTSEMVTEMPORARYDOCTORDETAILSSAVE",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("imgid", doctorDetails.get(0).imgid);
                editor.putString("name", doctorDetails.get(0).name);
                editor.putString("degree", doctorDetails.get(0).degree);
                editor.putString("speciality", doctorDetails.get(0).speciality);
                editor.putInt("experience", doctorDetails.get(0).experience);
                editor.putString("mobile", doctorDetails.get(0).mobile);
                editor.putString("wMobile", doctorDetails.get(0).wMobile);
                editor.commit();

                fragmentTransaction.replace(R.id.frameLayout, new DoctorProfile());
                fragmentTransaction.commit();
            }
        });

        doctor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DESIGNPROJECTSEMVTEMPORARYDOCTORDETAILSSAVE",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("imgid", doctorDetails.get(1).imgid);
                editor.putString("name", doctorDetails.get(1).name);
                editor.putString("degree", doctorDetails.get(1).degree);
                editor.putString("speciality", doctorDetails.get(1).speciality);
                editor.putInt("experience", doctorDetails.get(1).experience);
                editor.putString("mobile", doctorDetails.get(1).mobile);
                editor.putString("wMobile", doctorDetails.get(1).wMobile);
                editor.commit();

                fragmentTransaction.replace(R.id.frameLayout, new DoctorProfile());
                fragmentTransaction.commit();
            }
        });

        doctor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DESIGNPROJECTSEMVTEMPORARYDOCTORDETAILSSAVE", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("imgid", doctorDetails.get(2).imgid);
                editor.putString("name", doctorDetails.get(2).name);
                editor.putString("degree", doctorDetails.get(2).degree);
                editor.putString("speciality", doctorDetails.get(2).speciality);
                editor.putInt("experience", doctorDetails.get(2).experience);
                editor.putString("mobile", doctorDetails.get(2).mobile);
                editor.putString("wMobile", doctorDetails.get(2).wMobile);
                editor.commit();

                fragmentTransaction.replace(R.id.frameLayout, new DoctorProfile());
                fragmentTransaction.commit();
            }
        });

        doctor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DESIGNPROJECTSEMVTEMPORARYDOCTORDETAILSSAVE",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("imgid", doctorDetails.get(3).imgid);
                editor.putString("name", doctorDetails.get(3).name);
                editor.putString("degree", doctorDetails.get(3).degree);
                editor.putString("speciality", doctorDetails.get(3).speciality);
                editor.putInt("experience", doctorDetails.get(3).experience);
                editor.putString("mobile", doctorDetails.get(3).mobile);
                editor.putString("wMobile", doctorDetails.get(3).wMobile);
                editor.commit();

                fragmentTransaction.replace(R.id.frameLayout, new DoctorProfile());
                fragmentTransaction.commit();
            }
        });

        doctor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DESIGNPROJECTSEMVTEMPORARYDOCTORDETAILSSAVE",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("imgid", doctorDetails.get(4).imgid);
                editor.putString("name", doctorDetails.get(4).name);
                editor.putString("degree", doctorDetails.get(4).degree);
                editor.putString("speciality", doctorDetails.get(4).speciality);
                editor.putInt("experience", doctorDetails.get(4).experience);
                editor.putString("mobile", doctorDetails.get(4).mobile);
                editor.putString("wMobile", doctorDetails.get(4).wMobile);
                editor.commit();

                fragmentTransaction.replace(R.id.frameLayout, new DoctorProfile());
                fragmentTransaction.commit();
            }
        });
    }

}

// Temporary Place :) :) :)


// ArrayList (Collection of data for doctors view)
//        ArrayList<DoctorModel> allDoctors = new ArrayList<>();
//        for (int i=1; i<=10; i++){
//            allDoctors.add(new DoctorModel(R.drawable.dp, "Umang Rathod", "MS", "Heart Surgeon", "12 Yrs+"));
//        }
//        RecyclerView doctorsView = parentHolder.findViewById(R.id.doctorsList);
//        doctorsView.setLayoutManager(new GridLayoutManager(getActivity(), 10));
//        DoctorCardAdapter adapter1 = new DoctorCardAdapter(getActivity(), allDoctors);
//        doctorsView.setAdapter(adapter1);