package com.example.hireme.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.hireme.R;
import com.example.hireme.models.JobsModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class IT20245092_JobAdapter extends FirebaseRecyclerAdapter<JobsModel, IT20245092_JobAdapter.myViewHolder> {

    public IT20245092_JobAdapter(@NonNull FirebaseRecyclerOptions<JobsModel> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull IT20245092_JobAdapter.myViewHolder holder
            , @SuppressLint("RecyclerView") final int position, @NonNull JobsModel model) {
        holder.tv1.setText(model.getName());
        holder.tv2.setText(model.getRate() +"/= per hour");

        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark_normal)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.editjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.it20245092_editjobpopup))
                        .setExpanded(true,1500)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.editname);
                EditText rate = view.findViewById(R.id.editrate);
                EditText image = view.findViewById(R.id.editimage);
                EditText description = view.findViewById(R.id.editdescription);

                Button jobUpdate = view.findViewById(R.id.jobupdate);

                name.setText(model.getName());
                rate.setText(model.getRate());
                image.setText(model.getImage());
                description.setText(model.getDescription());

                dialogPlus.show();

                jobUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("Name",name.getText().toString());
                        map.put("Rate",rate.getText().toString());
                        map.put("Description",description.getText().toString());
                        map.put("Image",image.getText().toString());

                        FirebaseDatabase.getInstance("https://fir-demo-734c3-default-rtdb.firebaseio.com/").getReference().child("Jobs")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.tv1.getContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.tv1.getContext(),"Error while updating",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });
            }
        });

        holder.deletejob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv1.getContext());
                builder.setTitle("Are you sure wan to delete ?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance("https://fir-demo-734c3-default-rtdb.firebaseio.com/").getReference().child("Jobs")
                                .child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.tv1.getContext(),"Successfully deleted",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.tv1.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public IT20245092_JobAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20245092_job,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView tv1,tv2;

        Button editjob,deletejob;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            tv1 = (TextView)itemView.findViewById(R.id.txt1);
            tv2 = (TextView)itemView.findViewById(R.id.txt2);

            editjob = (Button) itemView.findViewById(R.id.editJob);
            deletejob = (Button) itemView.findViewById(R.id.deleteJob);
        }
    }
}
