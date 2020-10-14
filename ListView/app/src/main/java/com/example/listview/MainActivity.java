package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String[] values = {"Apple", "Apricot", "Avocado", "Banana", "Blackberry",
//                "Blueberry", "Cherry", "Coconut", "Cranberry", "Grape Raisin",
//                "Honeydew", "Jackfruit", "Lemon", "Lime", "Mango", "Watermelon"};
//        //따로 xml로 만들어서 데이터를 가져오는 것이 좋다.
//
//      ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.titles, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String)getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
    }
}
