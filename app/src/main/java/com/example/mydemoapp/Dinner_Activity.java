package com.example.mydemoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Dinner_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);


        //Initialize Variables
        TextView dinner_day;
        boolean[] selectItems;
        ArrayList<Integer> itemList = new ArrayList<>();
        String[] itemArray ={"POHA","UPMA","DHOKLA",
                "CHOLE BHATURE","PARATHA","SANDWICH","PLAIN DHOSA"};

            //Assign variables
            dinner_day=findViewById(R.id.dinner_day);

            //Initialise selected day array
            selectItems =new boolean[itemArray.length];

            dinner_day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Initialise alert dialog
                    AlertDialog.Builder builder =new AlertDialog.Builder(
                            Dinner_Activity.this
                    );

                    //set title
                    builder.setCancelable(false);

                    builder.setMultiChoiceItems(itemArray, selectItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            //Check Condition
                            if (b){
                                //When checkbox selected
                                //Add position in day list
                                itemList.add(i);
                                //sort day list
                                Collections.sort(itemList);
                            }
                            else {
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
                            StringBuilder stringBuilder =new StringBuilder();
                            //Use for Loop
                            for (int j=0; j<itemList.size();j++){

                                //concat array value
                                stringBuilder.append(itemArray[itemList.get(j)]);

                                //check condition
                                if (j != itemList.size()-1){
                                    //when j value not equal to item list size-1
                                    //Add comma
                                    stringBuilder.append(",");
                                }

                            }
                            //set text on text view
                            dinner_day.setText(stringBuilder.toString());

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
                            for (int j=0; j<selectItems.length; j++){
                                //Remove all selection
                                selectItems[j]=false;

                                //clear item list
                                itemList.clear();
                                //clear text view value
                                dinner_day.setText("");
                            }

                        }
                    });
                    //show dialog
                    builder.show();
                }
            });
        }

    }
