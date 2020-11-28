package com.study.nodejsappchat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.nodejsappchat.R;
import com.study.nodejsappchat.entities.Setting;

import java.util.ArrayList;

public class CustomSettingAdapter extends RecyclerView.Adapter<CustomSettingAdapter.ViewHolder> {
    private ArrayList<Setting> settings;

    public CustomSettingAdapter(ArrayList<Setting> settings) {
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
        Setting mSetting = settings.get(position);
        holder.setting.setText(mSetting.getSettingName());
        holder.imgSetting.setImageResource(mSetting.getIcon());
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView setting;
        public ImageView imgSetting;
        CustomSettingAdapter customContactAdapter;
        public ViewHolder(@NonNull View itemView, CustomSettingAdapter customContactAdapter) {
            super(itemView);
            setting = itemView.findViewById(R.id.txtSettingName);
            imgSetting = itemView.findViewById(R.id.imgSetting);
            this.customContactAdapter = customContactAdapter;
        }
    }
}
