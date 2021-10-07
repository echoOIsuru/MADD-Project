package com.example.hireme.util.it20224370;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hireme.R;
import com.example.hireme.frontend.it20224370.IT20224370_JobRequest;
import com.example.hireme.frontend.it20224370.IT20224370_MyRequests;
import com.example.hireme.frontend.it20224370.IT20224370_Workerprofile;
import com.example.hireme.models.IT20224370_IndustrialModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class IT20224370_AdapterIndustrial extends FirebaseRecyclerAdapter<IT20224370_IndustrialModel, IT20224370_AdapterIndustrial.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public IT20224370_AdapterIndustrial(@NonNull FirebaseRecyclerOptions<IT20224370_IndustrialModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull IT20224370_IndustrialModel model) {

        if (model.getType().equals("Industrial")) {
            holder.name.setText(model.getuName());
            holder.job.setText(model.getName());
            holder.from.setText(model.getLocation());

            Glide.with(holder.img.getContext())
                    .load(model.getImage())
                    .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                    .circleCrop()
                    .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                    .into(holder.img);
        } else {
            holder.name.setVisibility(View.GONE);
            holder.job.setVisibility(View.GONE);
            holder.from.setVisibility(View.GONE);

            Glide.with(holder.img.getContext())
                    .load(model.getImage())
                    .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                    .circleCrop()
                    .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                    .into(holder.img);

            holder.l1.setLayoutParams(holder.params);

        }


        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), IT20224370_JobRequest.class);

                i.putExtra("wjob", model.getName());
                i.putExtra("wmail", model.getEmail());
                i.putExtra("wname", model.getuName());
                i.putExtra("workerpic", model.getImage());

                view.getContext().startActivity(i);

            }
        });

        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//CircleImageView Wpic = view.findViewById(R.id.workerpropic);


//                TextView name = view.findViewById(R.id.editTextTextPersonName3);
//                TextView mobile = view.findViewById(R.id.editTextPhone3);
//                TextView email = view.findViewById(R.id.editTextemail);
//                TextView des = view.findViewById(R.id.editTextTextDes);

//                Glide.with(Wpic.getContext())
//                        .load(model.getHurl())
//                        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
//                        .circleCrop()
//                        .error(R.drawable.common_google_signin_btn_icon_dark_normal)
//                        .into(Wpic);

//                name.setText(model.getName());
//                mobile.setText(model.getContact_Number());
//                email.setText(model.getEmail());
//                des.setText(model.getDescription());

                Intent i = new Intent(view.getContext(), IT20224370_Workerprofile.class);

                i.putExtra("wname", model.getuName());
                i.putExtra("wnumber", model.getContact());
                i.putExtra("wmail", model.getEmail());
                i.putExtra("wdes", model.getDescription());
                i.putExtra("workerpic", model.getImage());
                i.putExtra("workerjob", model.getName());
                i.putExtra("workerrate", model.getRate());


                view.getContext().startActivity(i);
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20224370_jobtypes_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name, from, job;
        Button btn1;
        Button btn2, Myrequests;
        LinearLayout l1;
        ViewGroup.LayoutParams params;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.HJImg01);
            name = (TextView) itemView.findViewById(R.id.HJtvName);
            from = (TextView) itemView.findViewById(R.id.HJtvLocation);
            job = (TextView) itemView.findViewById(R.id.HJtvJob);
            l1 = (LinearLayout) itemView.findViewById(R.id.jobitemID);
            params = new LinearLayout.LayoutParams(0, 0);
            btn1 = (Button) itemView.findViewById(R.id.HJbtnrequest3);
            btn2 = (Button) itemView.findViewById(R.id.HJbtnview3);
        }
    }
}
