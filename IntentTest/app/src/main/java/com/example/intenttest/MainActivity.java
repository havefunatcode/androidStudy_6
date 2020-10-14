package com.example.intenttest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int GET_STRING = 1;
    TextView tvReturn;
    String strText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);

        tvReturn = findViewById(R.id.tvReturn);
        Button b = (Button)findViewById(R.id.Button01);
        final String text = b.getText().toString();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("BtnText", text);  //이벤트처리객체 안에서 외부에 있는 인스턴스를 호출할 때 개체의 값이 변하면 안되기 때문에 final로 선언해준다.
                //startActivity(intent); //화면 전환이 이 지점에서 일어난다.
                startActivityForResult(intent, GET_STRING);
            }
        });
        if(savedInstanceState != null)  {
            strText = savedInstanceState.getString("strText");
            tvReturn.setText(strText);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.messagemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())   {
            case R.id.actionsend:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms","(+82)10-1234-5678", null));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_STRING)   {
            if(resultCode == RESULT_OK) {
                strText = data.getStringExtra("INPUT_TEXT");
                tvReturn.setText(strText);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("strText", strText);
    }
}
