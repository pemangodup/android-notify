package com.example.notify;


import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder>{

    private List<NoteModelClass> modelClassList;          //1

    public NoteAdapter(List<NoteModelClass> modelClassList) {           //2
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {    //6

        final int id = modelClassList.get(position).getId();
        System.out.println("id: "+id);
        String title = modelClassList.get(position).getTitle();
        String note = modelClassList.get(position).getNote();
        holder.setData(title, note);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), profile.class);
                intent.putExtra("position", id);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();       //8
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {          //3
        private TextView title;
        private TextView note;
        public View mView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);            //5
            title = itemView.findViewById(R.id.title_show);
            note = itemView.findViewById(R.id.date);
            mView = itemView;
        }

        public void setData(String note_title, String note_note) {
            title.setText(note_title);                     //7
            note.setText(note_note);
        }
    }
}
