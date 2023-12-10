package app.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class EditTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Intent intent=getIntent();

        EditText descriptionEditText= findViewById(R.id.description2);
        EditText deadlineEditText= findViewById(R.id.deadline);
        EditText deadlineTimeEditText= findViewById(R.id.deadlineTime);
        Button addTaskButton = findViewById(R.id.addTaskButton);

        if(intent.hasExtra(MainActivity.TASK_TO_BE_EDITED)){
            Task editedTask = (Task)intent.getSerializableExtra(MainActivity.TASK_TO_BE_EDITED);

            descriptionEditText.setText(editedTask.description);
            deadlineEditText.setText(new SimpleDateFormat("dd/MM/yyyy").format(editedTask.deadline));
            deadlineTimeEditText.setText(new SimpleDateFormat("HH:mm").format(editedTask.deadline));
            addTaskButton.setText("Edit task");
        }else{
            Date newTime = new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(newTime);
            calendar.add(Calendar.HOUR_OF_DAY,24);
            newTime=calendar.getTime();
            deadlineEditText.setText(new SimpleDateFormat("dd/MM/yyyy").format(newTime));
            deadlineTimeEditText.setText(new SimpleDateFormat("HH:mm").format(newTime));
        }

        findViewById(R.id.addTaskButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date date=null;
                try{
                    date = dateFormat.parse(deadlineEditText.getText().toString()+' '+deadlineTimeEditText.getText().toString());
                }catch(ParseException e){
                    date=new Date();
                }catch(Exception e){
                    Log.e("EXCEPTION",e.toString());
                }
                Task task = new Task(descriptionEditText.getText().toString(), date,false);
                intent.putExtra(MainActivity.NEW_TASK,task);
                setResult(RESULT_OK,intent);
                finish();

            }
        });



    }
}