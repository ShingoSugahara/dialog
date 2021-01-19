package com.example.listviewsample3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private ArrayAdapter<String> adapter;
    private List<String> menuList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button2);
        ListView lvMenu = findViewById(R.id.lvMenu);
        lvMenu.setOnItemLongClickListener(new ListItemClickListener());
        menuList = new ArrayList<>();


        menuList.add("唐揚げ定食");
        menuList.add("ハンバーグ定食");
        menuList.add("生姜焼き定食");
        menuList.add("ステーキ定食");
        menuList.add("野菜炒め定食");
        menuList.add("とんかつ定食");
        menuList.add("ミンチカツ定食");
        menuList.add("チキンカツ定食");
        menuList.add("コロッケ定食");
        menuList.add("焼き魚定食");
        menuList.add("焼き肉定食");
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, menuList);
        lvMenu.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ListView lvMenu = findViewById(R.id.lvMenu);
                EditText editText = findViewById(R.id.edText);
                String edStr = editText.getText().toString();
                menuList.add(edStr);
                /*adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, menuList);
                lvMenu.setAdapter(adapter);*/
                adapter.notifyDataSetChanged();

            }
        });



        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                String show = item;
                Toast.makeText(MainActivity.this, show, Toast.LENGTH_LONG).show();
            }
        });
        /*lvMenu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                menuList.remove(position);
                adapter.notifyDataSetChanged();

                return true;
            }
        });*/

    }
    private class ListItemClickListener implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            OrderConfirmDialogFragment dialogFragment = new OrderConfirmDialogFragment(adapter,menuList,position);
            dialogFragment.show(getSupportFragmentManager(),"OrderConfirmDialogFragment");
            return true;
        }


    }
}