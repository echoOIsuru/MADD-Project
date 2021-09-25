package com.example.hireme.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
                            .setExpanded(true, 1250)
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
                    Toast.makeText(holder.fullname.getContext(), "Accepted Requests Cannot be Deleted", Toast.LENGTH_SHORT).show();
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
