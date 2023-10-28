package app.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        findViewById(R.id.addTaskButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date date=null;
                try{
                    date = dateFormat.parse(
                            ((EditText) findViewById(R.id.deadline)).getText().toString()+' '+((EditText) findViewById(R.id.deadlineTime)).getText().toString());

                }catch(ParseException e){
                    date=new Date();
                }catch(Exception e){
                    Log.e("EXCEPTION",e.toString());
                }

                Task task = new Task(
                        ((EditText) findViewById(R.id.description2)).getText().toString(), date,false);
                intent.putExtra("NEW_TASK",task);
                setResult(RESULT_OK,intent);
                finish();

            }
        });



    }
}