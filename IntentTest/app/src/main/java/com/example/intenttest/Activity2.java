package com.example.intenttest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        editText = findViewById(R.id.editText);

        TextView textView = findViewById(R.id.tvActivity2);
        final Intent intent = getIntent();
        textView.setText(intent.getStringExtra("BtnText"));

        Button b = (Button)findViewById(R.id.Button02);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("INPUT_TEXT", editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish(); //Activity2 화면이 닫힘.
            }
        });
    }
}
