package app.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Task> {

    Context context;
    int resource;
    List<Task> taskList;
    LayoutInflater layoutInflater;

    public CustomAdapter(@NonNull Context context, int resource, List<Task> taskList, LayoutInflater layoutInflater) {
        super(context, resource,taskList);
        this.context=context;
        this.resource=resource;
        this.taskList=taskList;
        this.layoutInflater=layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource,parent,false);

        Task taskElement = taskList.get(position);

        TextView descriptionTV=view.findViewById(R.id.description);
        descriptionTV.setText(taskElement.description);
        TextView dateTimeTV=view.findViewById(R.id.date_time);
        String dateTimeText = new SimpleDateFormat("dd/MM/yyy HH:mm").format(taskElement.deadline);
        dateTimeTV.setText(dateTimeText);
        Switch finishedSwitch=view.findViewById(R.id.finished);
        finishedSwitch.setChecked(taskElement.finished);
        finishedSwitch.setText(taskElement.finished? "Completed" : "Incomplete");
        int dateComparisonResult = (taskElement.deadline).compareTo(new Date());
        if(dateComparisonResult<0)//deadline exceeded
            dateTimeTV.setTextColor(Color.RED);
        else
            dateTimeTV.setTextColor(Color.rgb(0,100,0));
        finishedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                taskElement.finished=!taskElement.finished;
                finishedSwitch.setText(taskElement.finished? "Completed" : "Incomplete");
                dateTimeTV.setTextColor(taskElement.finished? Color.BLACK : dateComparisonResult>=0 ? Color.rgb(0,100,0): Color.RED);
            }
        });




        return view;
    }
}
