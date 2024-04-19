package com.example.sqlite;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.browser_tabs), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database dtb = new database(this);
//        dtb.addContact("Ram", "7894561230");    dtb.addContact("Shyam", "85459456");
//        dtb.addContact("Vishal", "789447851230"); dtb.addContact("Ritu", "78489103");

        dtb.delete(3);

        ArrayList<modelcontact> arrayList = dtb.fetchdata();
        for (modelcontact contact : arrayList) {
            Log.d("Contact Info", "ID: " + contact.id + ", Name: " + contact.name + ", Phone Number: " + contact.phone_number);
        }

        modelcontact contact = new modelcontact();
        contact.id = 3;
        contact.phone_number = "221133445566";

        dtb.update(contact);

    }
}