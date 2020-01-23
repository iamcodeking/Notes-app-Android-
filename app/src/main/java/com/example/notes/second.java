package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class second extends AppCompatActivity {
    int q;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        q=getIntent().getIntExtra("data",-1);
        editText=findViewById(R.id.edittext);
        if(q!= -1)
        editText.setText(MainActivity.arrayList.get(q));
        else{
            MainActivity.arrayList.add("");
            q=MainActivity.arrayList.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();

        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.arrayList.set(q,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> hashSet=new HashSet<String>(MainActivity.arrayList);
                sharedPreferences.edit().putStringSet("notes",hashSet).apply();



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


}
