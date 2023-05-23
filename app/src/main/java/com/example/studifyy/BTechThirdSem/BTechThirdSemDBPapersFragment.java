package com.example.studifyy.BTechThirdSem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studifyy.BTechFirstSem.LoadMaterialModal;
import com.example.studifyy.BTechFirstSem.LoadMaterialRecycleViewAdapter;
import com.example.studifyy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BTechThirdSemDBPapersFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<LoadMaterialModal> list;

    public BTechThirdSemDBPapersFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_b_tech_third_sem_d_b_papers, container, false);
        list=new ArrayList<>();
        recyclerView=view.findViewById(R.id.DBPapersRecycleView);
        LoadMaterialRecycleViewAdapter adapter=new LoadMaterialRecycleViewAdapter(list,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        FirebaseDatabase.getInstance().getReference().child("Book").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    LoadMaterialModal modal=snapshot1.getValue(LoadMaterialModal.class);
                    list.add(modal);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}