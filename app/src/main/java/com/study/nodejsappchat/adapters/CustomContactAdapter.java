package com.study.nodejsappchat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
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
        if (mContact.getPhoto() != null) {
            Picasso.get().load(mContact.getPhoto()).into(holder.pAvatar);
        } else {
            holder.pAvatar.setImageResource(R.drawable.icn_def_avatar);
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView pName, pNumber;
        public ImageView pAvatar;
        CustomContactAdapter customContactAdapter;
        public ViewHolder(@NonNull View itemView, CustomContactAdapter customContactAdapter) {
            super(itemView);
            pName = itemView.findViewById(R.id.txtContactName);
            pNumber = itemView.findViewById(R.id.txtPhoneNum);
            pAvatar = itemView.findViewById(R.id.img_AvataContact);
            this.customContactAdapter = customContactAdapter;
        }
    }
}
