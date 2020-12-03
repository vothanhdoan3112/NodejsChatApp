package com.study.nodejsappchat.fragments;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.study.nodejsappchat.HomeActivity;
import com.study.nodejsappchat.R;
import com.study.nodejsappchat.adapters.CustomContactAdapter;
import com.study.nodejsappchat.entities.Contact;
import com.study.nodejsappchat.entities.User;

import java.util.ArrayList;


public class ContactFragment extends Fragment {


    private RecyclerView listContact;
    private User loginUser;
    private CustomContactAdapter customContactAdapter;
    private Button syncContact;
    private ArrayList<Contact> contacts;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    public ContactFragment(User user) {
        this.loginUser = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        contacts = new ArrayList<>();
        listContact = view.findViewById(R.id.listContact);
        syncContact = view.findViewById(R.id.btnGetContacts);

        syncContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
                } else {
                    contacts.clear();
                    contacts = getContacts();
                    customContactAdapter = new CustomContactAdapter(contacts, loginUser);
                    listContact.setAdapter(customContactAdapter);
                    listContact.setLayoutManager(new LinearLayoutManager(getActivity()));

                }
            }
        });
        return view;
    }

    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> contactList = new ArrayList<>();
        ContentResolver cr = getActivity().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while (cur != null && cur.moveToNext()) {
            String id = cur.getString(
                    cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));

            String name = cur.getString(
                    cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String photo = cur.getString(
                    cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

            if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                Cursor pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                pCur.moveToNext();
                String phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Contact contact = new Contact(name, phone, photo);
                contactList.add(contact);
            }
        }
        if(cur!=null){
            cur.close();
        }
        return contactList;
    }

}