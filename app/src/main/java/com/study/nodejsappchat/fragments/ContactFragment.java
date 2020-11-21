package com.study.nodejsappchat.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.study.nodejsappchat.HomeActivity;
import com.study.nodejsappchat.R;
import com.study.nodejsappchat.entities.Contact;

import java.util.ArrayList;


public class ContactFragment extends Fragment {
    private Context context;
    private ListView listContact;
    private ArrayAdapter arrayAdapter;
    private ArrayList<Contact> contacts;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    public ContactFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showContacts();
            } else {
                Toast.makeText(context, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            listContact.findViewById(R.id.listContact);
            contacts = getContacts();
            contacts.add(new Contact("doan","0798369251"));
            arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, contacts);
            listContact.setAdapter(arrayAdapter);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> contactList = new ArrayList<>();
        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,
                ContactsContract.CommonDataKinds.Phone.NUMBER);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                String id  = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name  = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String number  = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                if (number.length()>0){
                    Cursor numCursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"=?",new String []{id},null);
                    if (numCursor.getCount()>0){
                        while (numCursor.moveToNext()){
                            String phoneNumberVal = numCursor.getString(numCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            Contact contact = new Contact(name,phoneNumberVal);
                            contactList.add(contact);
                        }
                    }
                    numCursor.close();
                }
            }
        }else{
            Toast.makeText(getContext(),"Khong co sdt trong danh ba",Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        return contactList;
    }
}