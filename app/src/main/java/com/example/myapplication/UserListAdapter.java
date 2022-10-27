package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Model> userList;
    EditText editname, editbranch, editduration;
    Button editsave , editdelete;
    LinearLayout linearLayout;
    int id;


    public UserListAdapter(Context context){
        this.context = context;

    }

    public void setUserList(ArrayList<Model> userList,Context context){
        this.userList = userList;
        this.context=context;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.MyViewHolder holder,int position) {
        holder.rname.setText(this.userList.get(position).name);
        holder.rbranch.setText(this.userList.get(position).branch);
        holder.rduration.setText(this.userList.get(position).duration);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,AddUserActivity.class);
                i.putExtra("id", String.valueOf(userList.get(position).id));
                i.putExtra("rname",userList.get(position).name);
                i.putExtra("rbranch",userList.get(position).branch);
                i.putExtra("rduration",userList.get(position).duration);
                context.startActivity(i);

            }
        });
        holder.editdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletedata(userList.get(position).id);
                userList.remove(position);
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return this.userList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rname;
        TextView rbranch;
        TextView rduration;
        RecyclerView recyclerView ;
        LinearLayout linearLayout;
        Button editdelete;


        public MyViewHolder(View view){
            super(view);
             rname = view.findViewById(R.id.rname);
             rbranch  = view.findViewById(R.id.rbranch);
             rduration = view.findViewById(R.id.rduration);
             recyclerView = view.findViewById(R.id.recid);
             linearLayout = view.findViewById(R.id.llrow);
            editdelete = view.findViewById(R.id.editdelete);
        }

    }
    private void deletedata(int id){
        Database db = Database.getDB(context.getApplicationContext());
        db.dao().delete(id);
    }

}
