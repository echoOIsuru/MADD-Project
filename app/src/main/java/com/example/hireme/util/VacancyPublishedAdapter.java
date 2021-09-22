package com.example.hireme.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hireme.R;
import com.example.hireme.database.Connection;
import com.example.hireme.models.Vacancies;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VacancyPublishedAdapter extends FirebaseRecyclerAdapter<Vacancies,VacancyPublishedAdapter.PublishedVacancyHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public VacancyPublishedAdapter(@NonNull FirebaseRecyclerOptions<Vacancies> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull PublishedVacancyHolder holder, final int position, @NonNull Vacancies model) {
        holder.title.setText(model.getJobTitle());
        holder.description.setText(model.getDescription());
        holder.salary.setText("Rs. "+model.getSalary() + " /m");

        String temp = model.getDeadline();
        holder.date.setText(temp);

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.description.getContext())
                        .setContentHolder(new ViewHolder(R.layout.it20133290_update_popup))
                        .setExpanded(true,1950)
                        .create();



                View view = dialogPlus.getHolderView();

                EditText salary = view.findViewById(R.id.etUpdateSalary);
                EditText title = view.findViewById(R.id.etUpdateJobTitle);
                EditText description = view.findViewById(R.id.etUpdateDescription);
                EditText org = view.findViewById(R.id.etUpdateOrganization);
                DatePicker datePicker = view.findViewById(R.id.dpUpdateDeadline);

                Button btnUp = view.findViewById(R.id.btnUpdateInfo);


                salary.setText(model.getSalary());
                title.setText(model.getJobTitle());
                description.setText(model.getDescription());
                org.setText(model.getOrganization());

                Date d = new Date(model.getDeadline());

                datePicker.updateDate(d.getYear()+1900,d.getMonth(),d.getDate());

                dialogPlus.show();


                btnUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Connection con = new Connection();

                        String date = ""+datePicker.getYear()+"/"+(datePicker.getMonth()+1)+"/"+datePicker.getDayOfMonth();

                        String desPattern = ".{1,200}";

                        if(!(description.getText().toString()).matches(desPattern))
                            Toast.makeText(view.getContext(), "Updated Successfully",Toast.LENGTH_LONG);

                        else{
                            Map<String,Object> map = new HashMap<>();
                            map.put("deadline",date);
                            map.put("description",description.getText().toString());
                            map.put("jobTitle",title.getText().toString());
                            map.put("organization",org.getText().toString());
                            map.put("salary",salary.getText().toString());

                            int mLastPosition = holder.getAdapterPosition();
                            con.getRef().child("Vacancies").child(getRef(mLastPosition).getKey()).updateChildren(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(holder.itemView.getContext(), "Updated Successfully",Toast.LENGTH_LONG);
                                            dialogPlus.dismiss();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            Toast.makeText(holder.itemView.getContext(), "Error While Updating",Toast.LENGTH_LONG);
                                            dialogPlus.dismiss();
                                        }
                                    });
                        }
                        }


                });

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.description.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Data will be permanently deleted");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Connection con = new Connection();
                        int mLastPosition = holder.getAdapterPosition();
                        con.getRef().child("Vacancies").child(getRef(mLastPosition).getKey()).removeValue();
                        Toast.makeText(holder.itemView.getContext(), "Deleted.",Toast.LENGTH_LONG);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.itemView.getContext(), "Cancelled.",Toast.LENGTH_LONG);

                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public PublishedVacancyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20133290_published_vacancy_block,parent,false);

        return new PublishedVacancyHolder(view);
    }

    class PublishedVacancyHolder extends RecyclerView.ViewHolder{

        TextView title, description, salary, date;
        Button btnUpdate, btnDelete;

        public PublishedVacancyHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvUpdateJobTitle);
            description = itemView.findViewById(R.id.tvUpdateJobDescription);
            salary = itemView.findViewById(R.id.tvUpdateJobSalary);
            date = itemView.findViewById(R.id.tvUpdateJobDate);
            btnUpdate = itemView.findViewById(R.id.btnVblockUpdate);
            btnDelete = itemView.findViewById(R.id.btnVblockRemove);

        }
    }

}
