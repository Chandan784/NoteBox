package com.notebox.notebox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.notebox.notebox.Classes.MyDb;
import com.notebox.notebox.databinding.ActivityAddNoteBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {
     ActivityAddNoteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());
        Intent intent = getIntent();
        String pattern = "E, dd MMM yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        MyDb myDb = new MyDb(this);
        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.addNote(binding.etTitle.getText().toString(),binding.etDetail.getText().toString(),date);

                Intent intent = new Intent(AddNoteActivity.this,MyNoteActivity.class);
                startActivity(intent);
                finish();

            }
        });





        }


    }
