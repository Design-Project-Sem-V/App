package com.umang_rathod.hms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoctorCardAdapter extends RecyclerView.Adapter<DoctorCardAdapter.ViewHolder> {

    Context context;
    ArrayList<DoctorModel> doctorsList;
    DoctorCardAdapter(Context context, ArrayList<DoctorModel> doctorsList){
        this.context = context;
        this.doctorsList = doctorsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.doctor_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.doctorImg.setImageResource(doctorsList.get(position).img);
        holder.doctorNameAndDegree.setText(doctorsList.get(position).name + " (" +
                                            doctorsList.get(position).degree + " )");
        holder.doctorSpeciality.setText(doctorsList.get(position).speciality);
        holder.doctorExperience.setText(doctorsList.get(position).experience);
    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView doctorImg;
        TextView doctorNameAndDegree, doctorSpeciality, doctorExperience;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorImg = itemView.findViewById(R.id.doctorImg);
            doctorNameAndDegree = itemView.findViewById(R.id.doctorNameAndDegree);
            doctorSpeciality = itemView.findViewById(R.id.doctorSpeciality);
            doctorExperience = itemView.findViewById(R.id.doctorExperience);

        }
    }
}
