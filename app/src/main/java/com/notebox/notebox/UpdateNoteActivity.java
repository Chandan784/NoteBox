package com.notebox.notebox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.notebox.notebox.Classes.MyDb;
import com.notebox.notebox.databinding.ActivityUpdateNoteBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {
ActivityUpdateNoteBinding binding;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
intent = getIntent();
MyDb mydb = new MyDb(UpdateNoteActivity.this);

            binding.etTitle.setText(intent.getStringExtra("title"));
            binding.etDetail.setText(intent.getStringExtra("detail"));
        String pattern = "E, dd MMM yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
            binding.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   mydb.updateRow(intent.getIntExtra("id",0),binding.etTitle.getText().toString(),binding.etDetail.getText().toString(),date);
                    Intent intent1 = new Intent(UpdateNoteActivity.this,MyNoteActivity.class);
                    startActivity(intent1);
                    finish();


                }
            });

}}