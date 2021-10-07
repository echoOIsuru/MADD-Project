package com.example.hireme.util.it20231682;

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
import com.example.hireme.frontend.it20231682.IT20231682_give_feedback;
import com.example.hireme.models.IT20224370_RequestModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class IT20231682_feedbackUserActvityAdapter extends FirebaseRecyclerAdapter<IT20224370_RequestModel,IT20231682_feedbackUserActvityAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public IT20231682_feedbackUserActvityAdapter(@NonNull FirebaseRecyclerOptions<IT20224370_RequestModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull IT20224370_RequestModel model) {


        if (model.getStatus().equals("accepted")) {

            Glide.with(holder.img.getContext())
                    .load(model.getWorkerPhoto())
                    .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                    .circleCrop()
                    .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                    .into(holder.img);

            holder.wname.setText(model.getWorkerName());
            holder.wemail.setText(model.getWorkerMail());
            holder.wjob.setText(model.getSelectedJob());

            holder.btnfeedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //launch the give feedback activity
                    Intent i = new Intent(view.getContext(), IT20231682_give_feedback.class);
                    i.putExtra("workerMail",model.getWorkerMail());
                    i.putExtra("workerName",model.getWorkerName());
                    view.getContext().startActivity(i);

                }
            });

        }else{


            holder.img.setVisibility(View.GONE);
            holder.wname.setVisibility(View.GONE);
            holder.wemail.setVisibility(View.GONE);
            holder.wjob.setVisibility(View.GONE);
            holder.btnfeedback.setVisibility(View.GONE);
            holder.l1.setLayoutParams(holder.params);

        }




//        Glide.with(holder.img.getContext())
//                .load(model.getWorkerPhoto())
//                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
//                .circleCrop()
//                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
//                .into(holder.img);
//
//        holder.wname.setText(model.getWorkerName());
//        holder.wemail.setText(model.getWorkerMail());
//        holder.wjob.setText(model.getSelectedJob());



//        holder.btnfeedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //launch the give feedback activity
//                Intent i = new Intent(view.getContext(), IT20231682_give_feedback.class);
//                i.putExtra("workerMail",model.getWorkerMail());
//                view.getContext().startActivity(i);
//
//            }
//        });

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20231682_feedback_worker_item,parent,false);
        return new myViewHolder(view);
    }



    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView wname,wemail,wjob;
        Button btnfeedback;
        LinearLayout l1;
        ViewGroup.LayoutParams params;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            wname = (TextView) itemView.findViewById(R.id.wnametest);
            wemail = (TextView) itemView.findViewById(R.id.wemailtext);
            wjob = (TextView) itemView.findViewById(R.id.wjobtext);
            btnfeedback = (Button) itemView.findViewById(R.id.btnfeedback);
            l1 = (LinearLayout) itemView.findViewById(R.id.l1);
            params = new LinearLayout.LayoutParams(0,0);


        }

    }

}
