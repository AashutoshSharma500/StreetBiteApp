package com.example.mydemoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Lunch_Activity extends AppCompatActivity {
    Button btn_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        btn_book=findViewById(R.id.btn_book);

        //Initialize Variables
        TextView lunchDay;
        boolean[] selectItems;
        ArrayList<Integer> itemList = new ArrayList<>();
        String[] itemArray ={"CHICKEN ROLL","DOSA","VADA PAV",
                "KULCHAS","PAV BHAJI","KEBAB","DAL BHATHI"};



            //Assign variables
            lunchDay = findViewById(R.id.Table_Choice);

            //Initialise selected day array
            selectItems = new boolean[itemArray.length];

            lunchDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Initialise alert dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            Lunch_Activity.this
                    );

                    //set title

                    builder.setTitle("Select Your Craving ");
                    builder.setCancelable(false);

                    builder.setMultiChoiceItems(itemArray, selectItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            //Check Condition
                            if (b) {
                                //When checkbox selected
                                //Add position in day list
                                itemList.add(i);
                                //sort day list
                                Collections.sort(itemList);
                            } else {
                                //when checkbox unselected
                                //Remove position from day list
                                itemList.remove(i);
                            }

                        }
                    });
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Initialise string builder
                            StringBuilder stringBuilder = new StringBuilder();
                            //Use for Loop
                            for (int j = 0; j < itemList.size(); j++) {

                                //concat array value
                                stringBuilder.append(itemArray[itemList.get(j)]);

                                //check condition
                                if (j != itemList.size() - 1) {
                                    //when j value not equal to item list size-1
                                    //Add comma
                                    stringBuilder.append(",");
                                }

                            }
                            //set text on text view
                            lunchDay.setText(stringBuilder.toString());

                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            //Dismiss dialog
                            dialogInterface.dismiss();

                        }
                    });
                    builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //use for loop
                            for (int j = 0; j < selectItems.length; j++) {
                                //Remove all selection
                                selectItems[j] = false;

                                //clear item list
                                itemList.clear();
                                //clear text view value
                                lunchDay.setText("");
                            }

                        }
                    });
                    //show dialog
                    builder.show();
                }
            });

        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Lunch_Activity.this, "Will Wait For You", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Lunch_Activity.this, Conclusion_Activity.class);
                startActivity(intent);

            }
        });
        }
}