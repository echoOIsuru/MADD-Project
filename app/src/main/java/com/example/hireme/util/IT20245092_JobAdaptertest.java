package com.example.hireme.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hireme.R;
import com.example.hireme.models.IT20245092_JobModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class IT20245092_JobAdaptertest extends FirebaseRecyclerAdapter<IT20245092_JobModel, IT20245092_JobAdaptertest.myViewHolder> {

    public IT20245092_JobAdaptertest(@NonNull FirebaseRecyclerOptions<IT20245092_JobModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull IT20245092_JobAdaptertest.myViewHolder holder, int position, @NonNull IT20245092_JobModel model) {
        holder.tv1.setText(model.getName());
        holder.tv2.setText(model.getRate());

        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark_normal)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
    }

    @NonNull
    @Override
    public IT20245092_JobAdaptertest.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20245092_job,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView tv1,tv2;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            tv1 = (TextView)itemView.findViewById(R.id.txt1);
            tv2 = (TextView)itemView.findViewById(R.id.txt2);
        }
    }
}
