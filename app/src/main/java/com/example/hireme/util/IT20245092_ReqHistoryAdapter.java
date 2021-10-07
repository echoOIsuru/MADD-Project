package com.example.hireme.util;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.Transaction;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class IT20245092_ReqHistoryAdapter extends FirebaseRecyclerAdapter<IT20224370_RequestModel,IT20245092_ReqHistoryAdapter.myViewHolder> {

    public IT20245092_ReqHistoryAdapter(@NonNull FirebaseRecyclerOptions<IT20224370_RequestModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull IT20224370_RequestModel model) {
        if (model.getStatus().equals("rejected") || model.getStatus().equals("accepted")) {
            holder.job.setText(model.getSelectedJob());
            holder.date.setText(model.getDate());
            //holder.time.setText(model.getTime());
            //model.getStatus().equals("accepted");
        }else{
            holder.job.setVisibility(View.GONE);
            holder.date.setVisibility(View.GONE);
            //holder.time.setVisibility(View.GONE);
            holder.l1.setLayoutParams(holder.params);
        }
        System.out.println("mama waddec");


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20245092_requesthistory,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView job,date,time;
        LinearLayout l1;
        ViewGroup.LayoutParams params;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            job = (TextView) itemView.findViewById(R.id.RqJName);
            date = (TextView) itemView.findViewById(R.id.rqDate);
//          time = (TextView) itemView.findViewById(R.id.timetext);
            l1 = (LinearLayout) itemView.findViewById(R.id.hislinid);
            params = new LinearLayout.LayoutParams(0, 0);

        }
    }
}
