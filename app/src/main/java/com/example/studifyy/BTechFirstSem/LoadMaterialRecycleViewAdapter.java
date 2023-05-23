package com.example.studifyy.BTechFirstSem;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studifyy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoadMaterialRecycleViewAdapter extends RecyclerView.Adapter<LoadMaterialRecycleViewAdapter.ViewHolder> {
    ArrayList<LoadMaterialModal> list;
    Context context;

    public LoadMaterialRecycleViewAdapter(ArrayList<LoadMaterialModal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.loadmaterialfragmentlayout,parent,false);
        ViewHolder viewholder=new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoadMaterialModal modal=list.get(position);
        Picasso.get().load(modal.getBookcover()).into(holder.cover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseStorage storage=FirebaseStorage.getInstance();
                StorageReference storageReference=storage.getReferenceFromUrl(modal.getUrl());
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(uri,"application/pdf");
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      try{
                          context.startActivity(intent);
                      }
                      catch (ActivityNotFoundException e){
                          Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                      }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Fail!!!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        holder.bname.setText(modal.getBookname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView bname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cover=itemView.findViewById(R.id.Cover);
            bname=itemView.findViewById(R.id.BookName);

        }
    }
}
