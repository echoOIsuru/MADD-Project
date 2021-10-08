package com.example.hireme.util.it20231682;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.usage.NetworkStats;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hireme.R;
import com.example.hireme.models.IT20231682_feedback_model;
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

public class IT20231682_feedbackAdapter extends FirebaseRecyclerAdapter<IT20231682_feedback_model,IT20231682_feedbackAdapter.myViewHolder> {

    private NetworkStats.Bucket firebaseAuth;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public IT20231682_feedbackAdapter(@NonNull FirebaseRecyclerOptions<IT20231682_feedback_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView")final int position, @NonNull IT20231682_feedback_model model) {



        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.name.setText("Your name : " + model.getName());
        holder.email.setText("Your email : " + model.getEmail());
        holder.wname.setText("Worker name : " +model.getWname());
        holder.wemail.setText("Worker email : " +model.getWemail());
        holder.review.setText("Review : " +model.getReview());
        holder.rateCount.setText("Rating value : " +model.getRate());





        //click on edit button and after display popup update
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.it20231682_feedback_update_popup))
                        .setExpanded(true,1100)
                        .create();

                //dialogPlus.show(); //without data

                View view = dialogPlus.getHolderView();

                RatingBar ratingBar;

//                EditText name = view.findViewById(R.id.textName);
//                EditText email = view.findViewById(R.id.textEmail);
                TextView rateCount = view.findViewById(R.id.ratecount);
                EditText review = view.findViewById(R.id.textReview);
                Button btnupdate = view.findViewById(R.id.btnUpdate);
                ratingBar = (RatingBar) view.findViewById(R.id.feedrate);

                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                        float rateValue;

                        rateValue = ratingBar.getRating();// get rating number from a rating bar

                        if (rateValue<=1 && rateValue > 0)
                            rateCount.setText("Bad " + rateValue + "/5");
                        else if (rateValue<=2 && rateValue > 1)
                            rateCount.setText("Ok " + rateValue + "/5");
                        else if (rateValue<=3 && rateValue > 2)
                            rateCount.setText("Good " + rateValue + "/5");
                        else if (rateValue<=4 && rateValue > 3)
                            rateCount.setText("Very good " + rateValue + "/5");
                        else if (rateValue<=5 && rateValue > 4)
                            rateCount.setText("Best " + rateValue + "/5");
                    }
                });

//                name.setText(model.getName());
//                email.setText(model.getEmail());
                rateCount.setText(model.getRate());
                review.setText(model.getReview());

                dialogPlus.show();

                //update button
                btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();
//                        map.put("name",name.getText().toString());
//                        map.put("email",email.getText().toString());
                        map.put("rate",rateCount.getText().toString());
                        map.put("review",review.getText().toString());



                        FirebaseDatabase.getInstance("https://hireme-d66c5-default-rtdb.firebaseio.com/").getReference().child("IT20231682_feedback_model")
                                .child(getRef(position).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Update is successfully!", Toast.LENGTH_SHORT).show();
                                        //click on update button after redirect previous page (popup is closed)
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while updating !", Toast.LENGTH_SHORT).show();
                                        //click on update button after redirect previous page (popup is closed)
                                        dialogPlus.dismiss();
                                    }
                                });
                            }
                        });
                    }
                });

        // delete button
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure want to delete this?");
                builder.setMessage("Deleted data cannot be undo !");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete data from database
                        FirebaseDatabase.getInstance("https://hireme-d66c5-default-rtdb.firebaseio.com/").getReference().child("IT20231682_feedback_model")
                                .child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.name.getContext(), "Successfully deleted!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Deleted is canceled!", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20231682_onecustomer_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        //Initialize Variables
        CircleImageView img;
        TextView name,email,wname,wemail,review;
        TextView rateCount ;
        ImageView btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assign variable
            img = (CircleImageView)itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametest);
            email = (TextView) itemView.findViewById(R.id.emailtext);
            wname = (TextView) itemView.findViewById(R.id.wnametest);
            wemail = (TextView) itemView.findViewById(R.id.wemailtext);
            review = (TextView) itemView.findViewById(R.id.reviewtext);
            rateCount = (TextView) itemView.findViewById(R.id.ratingtext);
//            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
//            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            btnEdit = (ImageView) itemView.findViewById(R.id.btnEdit);
            btnDelete = (ImageView) itemView.findViewById(R.id.btnDelete);

        }

    }
}
