package com.example.hireme.util;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hireme.R;
import com.example.hireme.frontend.it20133290.IT20133290_LatestVacancy;
import com.example.hireme.frontend.it20133290.IT20133290_Popup_apply_form;
import com.example.hireme.models.Vacancies;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VacancyAdapter extends FirebaseRecyclerAdapter<Vacancies,VacancyAdapter.vacancyViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public VacancyAdapter(@NonNull FirebaseRecyclerOptions<Vacancies> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull vacancyViewHolder holder, int position, @NonNull Vacancies model) {

        holder.title.setText(model.getJobTitle());
        holder.description.setText(model.getDescription());
        holder.salary.setText("Rs. "+model.getSalary() + " /m");

        String temp = model.getDeadline();

//        String day = Integer.toString(temp.getDate());
//        String month = Integer.toString(model.getDeadline().getMonth()+1);
//        String year = Integer.toString(model.getDeadline().getYear());
//
//        String date = day;
        holder.date.setText(temp);



        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popUp = new Intent(holder.itemView.getContext(),IT20133290_Popup_apply_form.class);
                popUp.putExtra("gmail",model.getEmail());
                holder.itemView.getContext().startActivity(popUp);

            }
        });

    }

    @NonNull
    @Override
    public vacancyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.it20133290_vacancy_block,parent,false);
        return new vacancyViewHolder(view);
    }

    class vacancyViewHolder extends RecyclerView.ViewHolder{
        TextView title, description, salary, date;
        Button btn;

        public vacancyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvVacancyTitle);
            description = itemView.findViewById(R.id.tvVacancyDescription);
            salary = itemView.findViewById(R.id.tvVacancySalary);
            date = itemView.findViewById(R.id.tvVacancyDeadline);
            btn = itemView.findViewById(R.id.btnApply);
        }
    }


}
