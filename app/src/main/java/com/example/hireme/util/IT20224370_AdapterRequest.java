package com.example.hireme.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hireme.R;
import com.example.hireme.models.IT20224370_RequestModel;
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

public class IT20224370_AdapterRequest extends FirebaseRecyclerAdapter<IT20224370_RequestModel, IT20224370_AdapterRequest.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public IT20224370_AdapterRequest(@NonNull FirebaseRecyclerOptions<IT20224370_RequestModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull IT20224370_RequestModel model) {

        //get values from the model and assign them to text fields

        holder.fullname.setText(model.getFullName());
        holder.mobileNumber.setText(model.getMobileNumber());
        holder.SelectedJob.setText(model.getSelectedJob());
        holder.status.setText(model.getStatus());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());


        //Update button onclick function

        holder.UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String status = model.getStatus();
                if (status.equals("accepted")  || status.equals("rejected")) {
                    Toast.makeText(holder.fullname.getContext(), "Accepted or Rejected Requests Cannot be Updated", Toast.LENGTH_SHORT).show();
                } else {

                    final DialogPlus dialogPlus = DialogPlus.newDialog(holder.fullname.getContext())      //Dialog box
                            .setContentHolder(new ViewHolder(R.layout.it20224370_update_popup))
                            .setExpanded(true, 1770)
                            .create();

                    //dialogPlus.show();

                    View view = dialogPlus.getHolderView();
                    EditText name = view.findViewById(R.id.UpdateName);
                    EditText mobile = view.findViewById(R.id.UpdateMobile);
                    EditText address = view.findViewById(R.id.UpdateAddress);
                    EditText date = view.findViewById(R.id.UpdateDate);
                    EditText time = view.findViewById(R.id.UpdateTime);

                    Button btnUpdate = view.findViewById(R.id.reqUpButton);         //Reference to update button in request_item.xml

                    name.setText(model.getFullName());                              //Get data from models and set them to text fields
                    mobile.setText(model.getMobileNumber());
                    address.setText(model.getAddress());
                    date.setText(model.getDate());
                    time.setText(model.getTime());

                    dialogPlus.show();                                             //display the dialog box

                    btnUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String NamePattern = ".{1,50}"; // creating pattern for name
                            String AddressPattern = ".{1,200}"; // creating pattern for address
                            String DatePattern = ("[0-3][0-1]/[0-1][0-9]/[0-2][0][0-2][0-2]");
                            String Time = ("([01]?[0-9]|2[0-3]):[0-5][0-9]");

                            //nested else if to clarify whether user enter inputs to text fields, if not shows a toast
                            try {
                                if (TextUtils.isEmpty(name.getText().toString()))
                                    Toast.makeText(holder.fullname.getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();

                                else if (!(name.getText().toString()).matches(NamePattern))
                                    Toast.makeText(holder.fullname.getContext(), "The Name You have Entered has Exceeded the Limit", Toast.LENGTH_SHORT).show();

                                else if (TextUtils.isEmpty(mobile.getText().toString()))
                                    Toast.makeText(holder.fullname.getContext(), "Please enter mobile number", Toast.LENGTH_SHORT).show();

                                else if (mobile.length() != 10 )

                                    mobile.setError("Mobile Number should have 10 digits");

                                else if (TextUtils.isEmpty(date.getText().toString()) )

                                    Toast.makeText(holder.fullname.getContext(), "Please enter Appointment Date", Toast.LENGTH_SHORT).show();

                                else if (!(date.getText().toString()).matches(DatePattern))

                                    Toast.makeText(holder.fullname.getContext(), "Entered Data Pattern is incorrect", Toast.LENGTH_SHORT).show();

                                else if (TextUtils.isEmpty(time.getText().toString()))
                                    Toast.makeText(holder.fullname.getContext(), "Please enter Appointment Time", Toast.LENGTH_SHORT).show();

                                else if (!(time.getText().toString()).matches(Time))
                                    Toast.makeText(holder.fullname.getContext(), "You have Entered an Incorrect Type Time", Toast.LENGTH_SHORT).show();

                                else if (TextUtils.isEmpty(address.getText().toString()) )
                                    Toast.makeText(holder.fullname.getContext(), "Please enter Address", Toast.LENGTH_SHORT).show();

                                else if (!(address.getText().toString()).matches(AddressPattern))
                                    Toast.makeText(holder.fullname.getContext(), "The Address You have Entered has Exceeded the Limit", Toast.LENGTH_SHORT).show();

                                else {
                            Map<String, Object> map = new HashMap<>();              //Map Values to the firebase object
                            map.put("fullName", name.getText().toString());
                            map.put("mobileNumber", mobile.getText().toString());
                            map.put("date", date.getText().toString());
                            map.put("time", time.getText().toString());
                            map.put("address", address.getText().toString());

                            FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/").getReference().child("requests")
                                    .child(getRef(position).getKey()).updateChildren(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(holder.fullname.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();       //Toast Message
                                            dialogPlus.dismiss();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            Toast.makeText(holder.fullname.getContext(), "Error Occurred While Updating", Toast.LENGTH_SHORT).show();    //Toast Message
                                            dialogPlus.dismiss();
                                        }
                                    });
                                }

                            } catch (Exception e) {

                            }
                        }
                    });

                }
            }

        });

        //Delete button onclick fuction

        holder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String status = model.getStatus();
                if (status.equals("rejected")) {
                    Toast.makeText(holder.fullname.getContext(), "Rejected Requests Cannot be Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.fullname.getContext());            //Dialog box
                    builder.setTitle("Are you Sure?");
                    builder.setMessage("After delete the data it cannot be undo");

                    builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {                //if user select delete option
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseDatabase.getInstance("https://hireme-2753d-default-rtdb.firebaseio.com/").getReference().child("requests")
                                    .child(getRef(position).getKey()).removeValue();
                            Toast.makeText(holder.fullname.getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();  //Toast Message
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {                //if user selects cancel option
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(holder.fullname.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();  //Toast Message
                        }
                    });
                    builder.show();
                    //view.getContext().startActivity(new Intent(view.getContext(), IT20224370_Workerprofile.class));
                }
            }
        });



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20224370_request_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView SelectedJob,fullname,mobileNumber,status,date,time;

        Button UpdateBtn;
        ImageButton DeleteBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            fullname = (TextView)itemView.findViewById(R.id.RqName);
            time = (TextView)itemView.findViewById(R.id.RqTime);
            date = (TextView)itemView.findViewById(R.id.RqDate);
            status = (TextView)itemView.findViewById(R.id.RqStatus);
            mobileNumber = (TextView)itemView.findViewById(R.id.RqMNumber);
            SelectedJob = (TextView)itemView.findViewById(R.id.ReqJob);

            UpdateBtn=(Button)itemView.findViewById(R.id.ReqUPbutton4);
            DeleteBtn = (ImageButton)itemView.findViewById(R.id.ReqDelimageView5);
        }
    }
}
