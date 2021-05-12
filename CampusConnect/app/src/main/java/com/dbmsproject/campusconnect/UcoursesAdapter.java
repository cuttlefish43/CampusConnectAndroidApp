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

public class UcoursesAdapter extends RecyclerView.Adapter<UcoursesAdapter.ViewHolder> {
    List<OCourses> CoursesList;
    int userid;
    int courseid;


    public UcoursesAdapter(List<OCourses> coursesList, CurrentUser currentUser) {
        CoursesList = coursesList;
        userid= currentUser.getId(); //userid for his relation with course i.e is registered or not and isstudent or faculty


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
        //courseid=CoursesList.get(position).getCourseid(); // this was a bug due to which users could'nt register on more than one course.
        final Context context = holder.courselist.getContext();
        holder.courselist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent it= new Intent(holder.courselist.getContext(),UserCourseReg.class);
                it.putExtra("userid",userid);
                it.putExtra("courseid", courseid);

                 */
                // now start checking if user is registered to that course or not // also only userid and courseid matters
                courseid=CoursesList.get(position).getCourseid();
                DatabaseHelper dbhelper= new DatabaseHelper(context);
                int s_reg_status=dbhelper.checkStudenttoCourse(userid,courseid);
                if(s_reg_status == 1) {
                    Intent newintent = new Intent(context, StudentDashBoard.class);
                    newintent.putExtra("userid",userid);
                    newintent.putExtra("courseid",courseid);
                    context.startActivity(newintent);

                }else if(s_reg_status ==5 ){
                    Toast.makeText(context, "Cannot register this course till as faculty registration approval is pending ", Toast.LENGTH_SHORT).show();
                }else{
                    int f_reg_status=dbhelper.checkFacultytoCourse(userid,courseid);
                    if(f_reg_status == 1){
                        Intent newintent2= new Intent(context, FacultyDashboard.class);
                        newintent2.putExtra("userid",userid);
                        newintent2.putExtra("courseid",courseid);
                        context.startActivity(newintent2);

                    }else if( f_reg_status == -1 ){
                        Intent newintent3 = new Intent(context,UserCourseReg.class);
                        newintent3.putExtra("reguserid",userid);
                        newintent3.putExtra("regcourseid",courseid);
                        context.startActivity(newintent3);

                    }

                }



                //System.out.println(userid);


            }
        });
        holder.imv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, CoursesList.get(position).getDescription(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return CoursesList.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cname;
        View courselist;
        ImageView imv_info,asy;
        //Button btn_cregister;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_cname= itemView.findViewById(R.id.tv_cname);
            courselist= itemView.findViewById(R.id.courselist);
            imv_info=itemView.findViewById(R.id.imv_info);
            //btn_cregister= itemView.findViewById(R.id.btn_cregister);
        }
    }
}
