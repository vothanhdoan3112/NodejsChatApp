package com.study.nodejsappchat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.nodejsappchat.R;
import com.study.nodejsappchat.adapters.CustomMessageAdapter;
import com.study.nodejsappchat.entities.Message;

import java.util.ArrayList;

public class MessageFragment extends Fragment {
    private RecyclerView rcvMessage;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("Hậu", "Đi họp kìa"));
        rcvMessage = view.findViewById(R.id.rcvMessages);
        rcvMessage.setAdapter(new CustomMessageAdapter(messages));
        rcvMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}