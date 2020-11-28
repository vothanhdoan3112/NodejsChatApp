package com.study.nodejsappchat.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.study.nodejsappchat.entities.Contact;
import com.study.nodejsappchat.entities.User;
import com.study.nodejsappchat.fragments.ContactFragment;
import com.study.nodejsappchat.fragments.GroupFragment;
import com.study.nodejsappchat.fragments.MessageFragment;
import com.study.nodejsappchat.fragments.UserFragment;

import java.util.ArrayList;

public class HomePagerAdapter extends FragmentStateAdapter {
    private User logUser;

    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity, User user) {
        super(fragmentActivity);
        this.logUser = user;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MessageFragment();
            case 1:
                return new GroupFragment();
            case 2:
                return new ContactFragment();
            default:
                return new UserFragment(logUser);
        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
