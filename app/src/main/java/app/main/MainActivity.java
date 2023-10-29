package app.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    public static final int REQUEST_CODE_EDIT_TASK=101;
    public static final String NEW_TASK="NEW_TASK";
    public static final String TASK_TO_BE_EDITED="TASK_TO_BE_EDITED";
    public static final String ITEM_POSITION="ITEM_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList=new LinkedList<Task>();
        taskList.add(new Task("Code app",new Date(2023-1900,9,28,8,00),false));
        taskList.add(new Task("Deploy app",new Date(2023-1900,10,28,23,50),false));

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),R.layout.task_element,taskList,getLayoutInflater());
        ListView mainListView =findViewById(R.id.mainListView);


        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),EditTaskActivity.class);
                intent.putExtra(TASK_TO_BE_EDITED,taskList.get(position));
                intent.putExtra(ITEM_POSITION,position);
                startActivityForResult(intent,REQUEST_CODE_EDIT_TASK);
            }
        });

        mainListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(MainActivity.this).
                        setTitle("Confirm deleting task?")
                        .setMessage("Are you sure you want to delete task : "+taskList.get(position).description+"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                taskList.remove(position);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Task deleted", Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.cancel();
                            }
                        })
                        .create().show();
                return true;
            }
        });

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

        ListView mainListView =findViewById(R.id.mainListView);
        CustomAdapter adapter=(CustomAdapter)  mainListView.getAdapter();

        if(requestCode==REQUEST_CODE_ADD_TASK && resultCode==RESULT_OK && data!=null){
            Task newTask = (Task)data.getSerializableExtra(NEW_TASK);
            taskList.add(newTask);

            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),"Task added successfully",Toast.LENGTH_LONG).show();
        }else if(requestCode==REQUEST_CODE_EDIT_TASK && resultCode==RESULT_OK && data!=null){
                Task newTask = (Task)data.getSerializableExtra(NEW_TASK);
                int position = (int)data.getSerializableExtra(ITEM_POSITION);
                if(newTask!=null){
                    taskList.set(position,newTask);
                    adapter.notifyDataSetChanged();
                }

        } else{
            Toast.makeText(getApplicationContext(),"Failed to add task",Toast.LENGTH_LONG).show();
        }
    }
}