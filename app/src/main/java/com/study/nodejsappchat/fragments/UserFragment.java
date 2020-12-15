package com.study.nodejsappchat.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.nodejsappchat.R;
import com.study.nodejsappchat.adapters.CustomSettingAdapter;
import com.study.nodejsappchat.entities.Setting;
import com.study.nodejsappchat.entities.User;

import java.util.ArrayList;


public class UserFragment extends Fragment {

    private RecyclerView recyclerView;
    private User logUser;
    private TextView username;


    public UserFragment() {
        // Required empty public constructor
    }

    public UserFragment(User user) {
        this.logUser = user;
        ((AppCompatActivity) getContext()).getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        // Inflate the layout for this fragment
        username = view.findViewById(R.id.txtLoginName);
        username.setText(logUser.getUserName());
        ArrayList<Setting> settings = new ArrayList<>();
        settings.add(new Setting("Đăng xuất", R.drawable.icn_signout));
        settings.add(new Setting("Tài khoản và bảo mật", R.drawable.icn_privacy));
        settings.add(new Setting("Quyền riêng tư", R.drawable.icn_key));
        settings.add(new Setting("Giao diện", R.drawable.icn_theme));
        settings.add(new Setting("Thông báo", R.drawable.icn_notification));

        recyclerView = view.findViewById(R.id.settingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CustomSettingAdapter(settings));
        return view;
    }
}