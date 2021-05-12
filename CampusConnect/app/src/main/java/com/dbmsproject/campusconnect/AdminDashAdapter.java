package com.dbmsproject.campusconnect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminDashAdapter extends RecyclerView.Adapter<AdminDashAdapter.ViewHolder>{
    List<Ousers> Displaylist;
    List<OCourses> Cisplaylist;
    List<Approval> Approvlist;
    int purpose;
    public AdminDashAdapter(List<?> Passedlist, int i) {
        purpose=i;
        if(purpose == 0) {
            Displaylist = (List<Ousers>) Passedlist;
        }else if(purpose == 1){
            Cisplaylist = (List<OCourses>) Passedlist;
        }
        else{
            Approvlist= (List<Approval>) Passedlist;
        }


    }


    @NonNull
    @Override
    public AdminDashAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(purpose ==0){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adminlistview,parent,false);
        }else if(purpose == 1){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adminlistview,parent,false);
        }
        else {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.approvallist,parent,false);
        }
        AdminDashAdapter.ViewHolder holder = new AdminDashAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDashAdapter.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        if(purpose == 0){
            holder.id1.setText(Integer.toString(Displaylist.get(position).getId()));
            holder.title1.setText(Displaylist.get(position).getUsername());
            holder.imv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    int status = databaseHelper.deleteUser(Displaylist.get(position).getId());
                    if(status ==1){
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(purpose == 1){
            holder.id1.setText(Integer.toString(Cisplaylist.get(position).getCourseid()));
            holder.title1.setText(Cisplaylist.get(position).getCoursename());
            holder.imv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    int status = databaseHelper.deleteCourse(Cisplaylist.get(position).getCourseid());
                    if(status ==1){
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            holder.imv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(context,Adannounce.class);
                    it.putExtra("cid", Cisplaylist.get(position).getCourseid());
                    Toast.makeText(context, "me clicked", Toast.LENGTH_SHORT).show();
                    context.startActivity(it);
                }
            });
        }
        else{
            holder.id1.setText(Integer.toString(Approvlist.get(position).getUserid()));
            holder.id2.setText(Integer.toString(Approvlist.get(position).getCourseid()));
            holder.title1.setText(Approvlist.get(position).getUsername());
            holder.title2.setText(Approvlist.get(position).getCoursename());
            holder.imv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    int status = databaseHelper.rejectFaculty(Approvlist.get(position).getUserid(),Approvlist.get(position).getCourseid());
                    if(status ==1){
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            holder.imv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(context);
                    int status = databaseHelper.acceptFaculty(Approvlist.get(position).getUserid(),Approvlist.get(position).getCourseid());
                    if(status ==1){
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        if(purpose ==0){
            return Displaylist.size();
        }else if(purpose ==1){
            return Cisplaylist.size();
        }
        else{
            return Approvlist.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id1,id2,title1,title2;
        ImageView imv_del,imv_edit,imv_accept;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_accept= itemView.findViewById(R.id.imv_accept);
            id1=itemView.findViewById(R.id.tv_id);
            id2=itemView.findViewById(R.id.tv_id2);
            title1=itemView.findViewById(R.id.tv_objtitle);
            title2=itemView.findViewById(R.id.tv_objtitle2);
            imv_del=itemView.findViewById(R.id.imv_del);
            imv_edit=itemView.findViewById(R.id.imv_accept);

        }
    }
    //work is going on.
}
