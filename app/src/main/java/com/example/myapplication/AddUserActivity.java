package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddUserActivity extends AppCompatActivity {
    EditText editname, editbranch, editduration;
    Button editsave, editupdate;
    int id ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        editname = findViewById(R.id.editname);
        editbranch = findViewById(R.id.editbranch);
        editduration = findViewById(R.id.editduration);
        editsave = findViewById(R.id.editsave);
        editupdate = findViewById(R.id.editupdate);

        editsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editname.getText().toString();
                String branch = editbranch.getText().toString();
                String duration = editduration.getText().toString();
                if(name.isEmpty() || branch.isEmpty() || duration.isEmpty()){
                    Toast.makeText(AddUserActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
               /*saveNewUser(name, branch, duration);*/
            }
        });
    }



    protected void onStart() {

        super.onStart();
            if(getIntent().getExtras()!=null) {
                editname.setText(getIntent().getExtras().getString("rname"));
                editbranch.setText(getIntent().getExtras().getString("rbranch"));
                editduration.setText(getIntent().getExtras().getString("rduration"));
                editupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        id = Integer.parseInt(getIntent().getExtras().getString("id"));
                        String name = editname.getText().toString();
                        String branch = editbranch.getText().toString();
                        String duration = editduration.getText().toString();
                        if(name.isEmpty() || branch.isEmpty() || duration.isEmpty()){
                            Toast.makeText(AddUserActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        updateUser(name,branch,duration,id);
                    }
                });
            }

            editsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = editname.getText().toString();
                    String branch = editbranch.getText().toString();
                    String duration = editduration.getText().toString();
                    if(name.isEmpty() || branch.isEmpty() || duration.isEmpty()){
                        Toast.makeText(AddUserActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                        return;
                    }
                   saveNewUser(name,branch,duration);
                }
            });

    }

    private void updateUser(String name, String branch, String duration, int id) {
        Database db = Database.getDB(this);
        db.dao().update(name, branch,duration,id);
        finish();
    }


    public void saveNewUser(String name, String branch, String duration){
        Database db = Database.getDB(this);
        Model model = new Model(name,branch,duration);
        model.name = name;
        model.branch = branch;
        model.duration = duration;
        db.dao().insert(model);

        finish();
    }
}