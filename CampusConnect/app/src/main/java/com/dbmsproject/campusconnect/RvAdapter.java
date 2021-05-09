package com.dbmsproject.campusconnect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    List<OCourses> CoursesList;

    public RvAdapter(List<OCourses> coursesList) {
        CoursesList = coursesList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.courselist,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_cname.setText(CoursesList.get(position).getCoursename());

    }

    @Override
    public int getItemCount() {
        return CoursesList.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cname;
        Button btn_cregister;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cname= itemView.findViewById(R.id.tv_cname);
            btn_cregister= itemView.findViewById(R.id.btn_cregister);
        }
    }
}
