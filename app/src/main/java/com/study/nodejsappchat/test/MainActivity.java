package com.study.nodejsappchat.test;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.study.nodejsappchat.R;
import com.study.nodejsappchat.adapters.CustomContactAdapter;
import com.study.nodejsappchat.entities.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CustomContactAdapter customContactAdapter;
    ArrayList<Contact> contacts;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contacts = new ArrayList<>();
        contacts.add(new Contact("doan","123465789"));
        //customContactAdapter = new CustomContactAdapter(contacts);
        recyclerView = findViewById(R.id.rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customContactAdapter);
    }
}