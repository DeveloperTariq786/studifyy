package com.example.studifyy.BTechFourthSem;

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

public class BTechFourthSemCoursesRecycleViewAdapter extends RecyclerView.Adapter<BTechFourthSemCoursesRecycleViewAdapter.viewHolder>{
    ArrayList<BTechFirstSemCoursesRecycleViewModal> list;
    Context context;

    public BTechFourthSemCoursesRecycleViewAdapter(ArrayList<BTechFirstSemCoursesRecycleViewModal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.coursedetaillayout,parent,false);
       return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.course.setText(list.get(position).Course);
        int id=holder.getPosition();
        switch (id){
            case 0:
                holder.loadFragment(new BTechFourthSemAlgorithmFragment(),0);
                holder.itemView.setOnClickListener(view -> holder.loadFragment(new BTechFourthSemAlgorithmFragment(),0));
                break;
            case 1:
                holder.itemView.setOnClickListener(view -> holder.loadFragment(new BTechFourthSemMath4Fragment(),0));
                break;
            case 2:
                holder.itemView.setOnClickListener(view -> holder.loadFragment(new BTechFourthSemDiscretFragment(),0));
                break;
            case 3:
                holder.itemView.setOnClickListener(view -> holder.loadFragment(new BTechFourthSemDigitalElectronicFragment(),0));
                break;
            case 4:
                holder.itemView.setOnClickListener(view -> holder.loadFragment(new BTechFourthSemEconomicsFragment(),0));
                break;
            case 5:
                holder.itemView.setOnClickListener(view -> holder.loadFragment(new BTechFourthSemPapersFragment(),0));
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
            course = itemView.findViewById(R.id.course);
        }

        public void loadFragment(Fragment frag, int flag) {
            FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (flag == 1)
                ft.add(R.id.container, frag);
            else
                ft.replace(R.id.container, frag);

            ft.commit();
        }
    }

}
