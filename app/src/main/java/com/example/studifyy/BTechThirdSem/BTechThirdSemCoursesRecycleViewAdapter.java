package com.example.studifyy.BTechThirdSem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studifyy.BTechFirstSem.BTechFirstSemCoursesRecycleViewModal;
import com.example.studifyy.R;
import java.util.ArrayList;

public class BTechThirdSemCoursesRecycleViewAdapter extends RecyclerView.Adapter<BTechThirdSemCoursesRecycleViewAdapter.viewHolder> {
    ArrayList<BTechFirstSemCoursesRecycleViewModal> list;
    Context context;
    public BTechThirdSemCoursesRecycleViewAdapter(ArrayList<BTechFirstSemCoursesRecycleViewModal> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.coursedetaillayout,parent,false);
       viewHolder viewHolder=new viewHolder(view);
       return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.course.setText(list.get(position).Course);
        int id=holder.getPosition();
        switch (id){
            case 0:
                holder.loadFragment(new BTechThirdSemDSFragment(),0);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.loadFragment(new BTechThirdSemDSFragment(),0);
                    }
                });
                break;
            case 1:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.loadFragment(new BTechThirdSemMathFragment(),0);
                    }
                });
                break;
            case 2:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.loadFragment(new BTechThirdSemDBFragment(),0);
                    }
                });
                break;
            case 3:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.loadFragment(new BTechThirdSemElectronicsFragment(),0);
                    }
                });
                break;
            case 4:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.loadFragment(new BTechThirdSemEVSFragment(),0);
                    }
                });
                break;
            case 5:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.loadFragment(new BTechThirdSemLabFragment(),0);
                    }
                });
                break;
            case 6:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.loadFragment(new BTechThirdSemPapersFragment(),0);
                    }
                });
        }

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder {
        TextView course;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            course=itemView.findViewById(R.id.course);
        }
        public void loadFragment(Fragment frag, int flag){
            FragmentManager fm=((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            if(flag==1)
                ft.add(R.id.container,frag);
            else
                ft.replace(R.id.container,frag);

            ft.commit();
        }
    }
}
