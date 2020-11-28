package com.study.nodejsappchat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.nodejsappchat.R;
import com.study.nodejsappchat.entities.Contact;

import java.util.ArrayList;

public class CustomContactAdapter extends RecyclerView.Adapter<CustomContactAdapter.ViewHolder> {
    private ArrayList<Contact> contacts;

    public CustomContactAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public CustomContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_contact_view,null);

        return new ViewHolder(view,this);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomContactAdapter.ViewHolder holder, int position) {
        Contact mContact = contacts.get(position);
        holder.pName.setText(mContact.getName());
        holder.pNumber.setText(mContact.getNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView pName,pNumber;
        CustomContactAdapter customContactAdapter;
        public ViewHolder(@NonNull View itemView, CustomContactAdapter customContactAdapter) {
            super(itemView);
            pName = itemView.findViewById(R.id.txtSettingName);
            pNumber = itemView.findViewById(R.id.txtPnumber);
            this.customContactAdapter = customContactAdapter;
        }
    }
}
