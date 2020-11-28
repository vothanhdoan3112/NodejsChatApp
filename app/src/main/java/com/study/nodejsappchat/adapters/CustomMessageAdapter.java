package com.study.nodejsappchat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.nodejsappchat.R;
import com.study.nodejsappchat.entities.Message;

import java.util.ArrayList;

public class CustomMessageAdapter extends RecyclerView.Adapter<CustomMessageAdapter.ViewHolder> {
    private ArrayList<Message> messages;

    public CustomMessageAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public CustomMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_message_view, null);
        return new CustomMessageAdapter.ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomMessageAdapter.ViewHolder holder, int position) {
        holder.pName.setText(messages.get(position).getName());
        holder.pMessage.setText(messages.get(position).getMesage());
        holder.pAvatar.setImageResource(R.drawable.icn_def_avatar);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pName, pMessage;
        public ImageView pAvatar;
        CustomMessageAdapter customMessageAdapter;

        public ViewHolder(@NonNull View itemView, CustomMessageAdapter customMessageAdapter) {
            super(itemView);
            pName = itemView.findViewById(R.id.txtFriendName);
            pMessage = itemView.findViewById(R.id.txtMsgPreview);
            pAvatar = itemView.findViewById(R.id.img_AvatarFriend);
            this.customMessageAdapter = customMessageAdapter;
        }
    }
}
