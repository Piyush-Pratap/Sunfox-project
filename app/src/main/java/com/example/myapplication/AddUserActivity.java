package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddUserActivity extends AppCompatActivity {
    EditText editname, editbranch, editduration;
    Button editsave;
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
                editsave.setText("Update");
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
                        upadateUser(name, branch, duration);
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

    public void upadateUser(String name, String branch, String duration){
           Database db = Database.getDB(this);
           Model model = new Model(name,branch,duration);
           model.name = name;
           model.branch = branch;
           model.duration = duration;
           db.dao().update(model);
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