package com.dbmsproject.campusconnect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FacultyDashAdapter extends RecyclerView.Adapter<FacultyDashAdapter.ViewHolder> {
    List<Oothers> Displaylist;
    int courseid;
    int userid;
    int purpose;
    public FacultyDashAdapter(List<Oothers> annList, int uid, int cid, int i) {

        Displaylist= annList;
        userid=uid;
        courseid=cid;
        purpose=i;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(purpose ==0){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.announcelist,parent,false);
        }else if(purpose == 1){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.attachlist,parent,false);
        }else {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.discuslist,parent,false);

        }
        FacultyDashAdapter.ViewHolder holder= new FacultyDashAdapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_mtitle.setText(Displaylist.get(position).getTitle());
        holder.tv_stitle.setText(Displaylist.get(position).getInfo());
    }

    @Override
    public int getItemCount() {
        return Displaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View listitem;
        TextView tv_mtitle,tv_stitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if(purpose == 0){ //for announcement list
                listitem= itemView.findViewById(R.id.announcelist);
                tv_mtitle=itemView.findViewById(R.id.tv_date);
                tv_stitle= itemView.findViewById(R.id.tv_announcement);
            }else if(purpose == 1){
                listitem= itemView.findViewById(R.id.attchlist);
                tv_mtitle=itemView.findViewById(R.id.tv_mtitle);
                tv_stitle= itemView.findViewById(R.id.tv_stitle);

            }else{
                listitem= itemView.findViewById(R.id.discuslist);
                tv_mtitle=itemView.findViewById(R.id.tv_sname);
                tv_stitle= itemView.findViewById(R.id.tv_message);

            }
        }
    }
}
