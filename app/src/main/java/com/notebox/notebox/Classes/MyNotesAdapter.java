package com.notebox.notebox.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.notebox.notebox.AddNoteActivity;
import com.notebox.notebox.R;
import com.notebox.notebox.UpdateNoteActivity;

import java.util.ArrayList;

public class MyNotesAdapter extends RecyclerView.Adapter<MyNotesAdapter.ViewHolder> {
    Context context;

    RecyclerView recyclerView;

    ArrayList<MyNotesModel> list;

    public MyNotesAdapter(Context context, ArrayList<MyNotesModel> list, RecyclerView recyclerView) {
        this.context = context;
        this.list = list;
        this.recyclerView = recyclerView;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_single_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.details.setText(list.get(position).getDetails());
        holder.date.setText(list.get(position).getDate());

        holder.ib_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = list.get(position).getId();
                Toast.makeText(view.getContext(), String.valueOf(id), Toast.LENGTH_LONG).show();
                Intent intent  = new Intent(view.getContext(), UpdateNoteActivity.class);
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("detail",list.get(position).getDetails());
                intent.putExtra("date",list.get(position).getDate());
                intent.putExtra("id",id);
                view.getContext().startActivity(intent);
                ((Activity)context).finish();







            }
        });

        holder.ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDb db = new MyDb(view.getContext());
                int id = list.get(position).getId();
                //  Toast.makeText(view.getContext(), String.valueOf(id), Toast.LENGTH_LONG).show();
                if (db.deleteRow(id)) {

                    ArrayList<MyNotesModel> list = new ArrayList<>();
                    list = db.getNotes();
                    MyNotesAdapter adapter = new MyNotesAdapter(context, list, recyclerView);

                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, details, date;
        ImageButton ib_delete, ib_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_tv);
            details = itemView.findViewById(R.id.desc_tv);
            date = itemView.findViewById(R.id.date_tv);
            ib_delete = itemView.findViewById(R.id.delete_ib);
            ib_edit = itemView.findViewById(R.id.edit_ib);


        }
    }
}
