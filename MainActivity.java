package com.froilanhuar.listaapp_2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

        private Button button1, button2, button3, button4 , button5, button6, button7;
    private TextView outputTextView;
    private SQLiteDatabase database;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);





        outputTextView = findViewById(R.id.outputTextView);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getReadableDatabase();
        dbHelper.checkVersion(this);
        fetchData();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTable1();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTable2();
            }
        });
    }

    private void displayTable1() {
        // Retrieve data from the database (e.g., "SELECT * FROM table1") and display in outputTextView
        Cursor cursor = database.rawQuery("SELECT * FROM polerasCerrada", null);
        displayData(cursor);
    }

    private void displayTable2() {
        // Retrieve data from the database (e.g., "SELECT * FROM table2") and display in outputTextView
        Cursor cursor = database.rawQuery("SELECT * FROM poloColegioColor_Color", null);
        displayData(cursor);
    }

    private void displayData(Cursor cursor) {
        if (cursor.moveToFirst()) {
            StringBuilder data = new StringBuilder();
            do {
                // Process and append data to the StringBuilder
                //String name = cursor.getString(cursor.getColumnIndex("name"));

                String ResultQuery = cursor.getString(0) + " | " + cursor.getString(1) + " | " + cursor.getString(2) ;
                //String ResultQuery = cursor.getString(0);//el resultado consiste de una lista de un solo indice.
                data.append(ResultQuery).append("\n");





            } while (cursor.moveToNext());

            outputTextView.setText(data.toString());
        } else {
            outputTextView.setText("No data found.");
        }
        cursor.close();
    }
    public void fetchData()
    {
        // Before fetching the data
        // directly from the database.
        // first we have to creates an empty
        // database on the system and
        // rewrites it with your own database.
        // Then we have to open the
        // database to fetch the data from it.
        db = new DatabaseHelper(this);
        try {

            db.createDataBase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }




    }

}
