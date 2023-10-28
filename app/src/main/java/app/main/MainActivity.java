package app.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Task> taskList;
    public static final int REQUEST_CODE_ADD_TASK=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList=new LinkedList<Task>();
        taskList.add(new Task("Code app",new Date(2023-1900,9,28,8,00),false));
        taskList.add(new Task("Deploy app",new Date(2023-1900,9,28,23,50),false));

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),R.layout.task_element,taskList,getLayoutInflater());
        ListView mainListView =findViewById(R.id.mainListView);
        mainListView.setAdapter(adapter);

        Button newTaskButton = findViewById(R.id.button);
        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditTaskActivity.class);
                startActivityForResult(intent,REQUEST_CODE_ADD_TASK);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_ADD_TASK && resultCode==RESULT_OK && data!=null){
            Task newTask = (Task)data.getSerializableExtra("NEW_TASK");
            taskList.add(newTask);

            ListView mainListView =findViewById(R.id.mainListView);
            CustomAdapter adapter=(CustomAdapter)  mainListView.getAdapter();
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),"Task added successfully",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Failed to add task",Toast.LENGTH_LONG).show();
        }
    }
}