package com.example.hireme.util.it20231682;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hireme.R;
import com.example.hireme.models.IT20231682_feedback_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class IT20231682_feedback_viewAdapter extends FirebaseRecyclerAdapter<IT20231682_feedback_model,IT20231682_feedback_viewAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public IT20231682_feedback_viewAdapter(@NonNull FirebaseRecyclerOptions<IT20231682_feedback_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull IT20231682_feedback_model model) {

        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.name.setText("User name : " +model.getName());
        holder.email.setText("User name : " +model.getEmail());
        holder.wemail.setText("Worker email : " +model.getWemail());
        holder.review.setText("Review : " +model.getReview());
        holder.rateCount.setText("Rate : " +model.getRate());


    }

    @NonNull
    @Override       //bind recycle view and feedback view all item
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20231682_feedback_viewall_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        //Initialize Variables
        CircleImageView img;
        TextView name,email,wemail,review;
        TextView rateCount ;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assign variable
            img = (CircleImageView)itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametest);
            email = (TextView) itemView.findViewById(R.id.emailtext);
            wemail = (TextView) itemView.findViewById(R.id.wemailtext);
            review = (TextView) itemView.findViewById(R.id.reviewtext);
            rateCount = (TextView) itemView.findViewById(R.id.ratingtext);


        }

    }


}
