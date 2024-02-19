package com.example.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button buttonadd;
    private Button buttondel;

    //creating array list
    ArrayList<String> addArray = new ArrayList<String>();
    EditText userText; //user input
    ListView items; //list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userText = (EditText) findViewById(R.id.inputText); //edit text
        items = (ListView) findViewById(R.id.listView); //list view
        buttonadd = (Button) findViewById(R.id.button2); //button to add
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, addArray);

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = userText.getText().toString();

                if(addArray.contains(getInput)){
                    Toast.makeText(getBaseContext(), "Item already added", Toast.LENGTH_SHORT).show();
                }else if(getInput == null || getInput.trim().equals("")){
                    Toast.makeText(getBaseContext(), "Input field is empty", Toast.LENGTH_SHORT).show();
                }else{
                    addArray.add(getInput);

                    items.setAdapter(adapter);
                    ((EditText)findViewById(R.id.inputText)).setText(" ");
                }
            }
        });

        //deleting the thing
        items.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                addArray.remove(which_item);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null).show();
                return true;
            }
        });
    }
}