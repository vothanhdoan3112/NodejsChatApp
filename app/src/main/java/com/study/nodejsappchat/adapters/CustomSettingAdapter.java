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

public class CustomSettingAdapter extends RecyclerView.Adapter<CustomSettingAdapter.ViewHolder> {
    private ArrayList<String> settings;

    public CustomSettingAdapter(ArrayList<String> settings) {
        this.settings = settings;

    }

    @NonNull
    @Override
    public CustomSettingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_setting_view,null);

        return new ViewHolder(view,this);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomSettingAdapter.ViewHolder holder, int position) {
        String msetting = settings.get(position);
        holder.setting.setText(msetting);
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView setting;
        CustomSettingAdapter customContactAdapter;
        public ViewHolder(@NonNull View itemView, CustomSettingAdapter customContactAdapter) {
            super(itemView);
            setting = itemView.findViewById(R.id.txtSettingName);
            this.customContactAdapter = customContactAdapter;
        }
    }
}
