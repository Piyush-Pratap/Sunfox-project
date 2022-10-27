package com.example.myapplication;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button Adduser , editdelete;
    private UserListAdapter userListAdapter;
    RecyclerView recyclerView;
    int id;
/*

*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Adduser  = findViewById(R.id.Adduser);
        Adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(MainActivity.this, AddUserActivity.class);
               startActivity(i);
            }
        });
    }


     private void initRecyclerView(){
         recyclerView = findViewById(R.id.recid);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));

         DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
         recyclerView.addItemDecoration(dividerItemDecoration);
         userListAdapter = new UserListAdapter(this);
         recyclerView.setAdapter(userListAdapter);
     }

    private void loadUserList(){
        Database db = Database.getDB(this.getApplicationContext());
        ArrayList<Model> userList = (ArrayList<Model>) db.dao().getAlldetails();
        userListAdapter.setUserList(userList,this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100){
             loadUserList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    protected void onStart() {

        super.onStart();
        initRecyclerView();
        loadUserList();
        /*if(getIntent().getExtras()!=null) {
            id = Integer.parseInt(getIntent().getExtras().getString("id"));
            editdelete = findViewById(R.id.editdelete);
            editdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deletedata(id);
                    use
                }
            });

        }*/
    }

    /*private void deletedata(int id){
        Database db = Database.getDB(this);
        db.dao().delete(id);
    }*/

 }
