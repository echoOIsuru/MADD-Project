package com.example.hireme.util;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hireme.R;
import com.example.hireme.models.IT20224370_RequestModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class IT20245092_RequestAdapter extends FirebaseRecyclerAdapter<IT20224370_RequestModel,IT20245092_RequestAdapter.myViewHolder> {

    public IT20245092_RequestAdapter(@NonNull FirebaseRecyclerOptions<IT20224370_RequestModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull IT20224370_RequestModel model) {
        if (model.getStatus().equals("pending")) {
                holder.job.setText(model.getSelectedJob());
                holder.date.setText(model.getDate());
                holder.time.setText(model.getTime());
                //!model.getStatus().equals("accepted")
        }else{
            holder.job.setVisibility(View.GONE);
            holder.date.setVisibility(View.GONE);
            holder.time.setVisibility(View.GONE);
            holder.l1.setLayoutParams(holder.params);
        }

        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status;
                status = model.getStatus();
                System.out.println(status + "before change");

                Map<String,Object> map = new HashMap<>();
                map.put("status","accepted");
                map.put("address",model.getAddress());
                map.put("date",model.getDate());
                map.put("fullName",model.getFullName());
                map.put("mobileNumber",model.getMobileNumber());
                map.put("selectedJob", model.getSelectedJob());
                map.put("time", model.getTime());
                map.put("userMail", model.getUserMail());
                map.put("workerMail",model.getWorkerMail());
                map.put("workerName",model.getWorkerName());
                map.put("workerPhoto",model.getWorkerPhoto());


                FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/").getReference().child("requests").child(getRef(position).getKey()).updateChildren(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.btnAccept.getContext(),"accepted",Toast.LENGTH_SHORT).show();
                                System.out.println(model.getStatus());
                                System.out.println(position + "adooooooooooooooooooooooooooooo");
                                System.out.println(getRef(position).getKey() + "ad");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.btnAccept.getContext(),"error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status;
                status = model.getStatus();

                Map<String,Object> map = new HashMap<>();
                map.put("status","rejected");


                FirebaseDatabase.getInstance("https://fir-demo-734c3-default-rtdb.firebaseio.com/").getReference().child("requests")
                        .child(getRef(position).getKey()).updateChildren(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.btnDecline.getContext(),"Declined",Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.btnDecline.getContext(),"error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20245092_request,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView job,date,time;
        LinearLayout l1;
        ViewGroup.LayoutParams params;
        Button btnAccept,btnDecline;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            job = (TextView) itemView.findViewById(R.id.jobtext);
            date = (TextView) itemView.findViewById(R.id.datetext);
            time = (TextView) itemView.findViewById(R.id.timetext);
            l1 = (LinearLayout) itemView.findViewById(R.id.linearid);
            params = new LinearLayout.LayoutParams(0, 0);
            btnAccept = (Button) itemView.findViewById(R.id.btnAccept);
            btnDecline = (Button) itemView.findViewById(R.id.btnDecline);
        }
    }

}
