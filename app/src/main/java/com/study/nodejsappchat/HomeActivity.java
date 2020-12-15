package com.study.nodejsappchat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.study.nodejsappchat.entities.Contact;
import com.study.nodejsappchat.entities.User;
import com.study.nodejsappchat.fragments.FriendSearchFragment;
import com.study.nodejsappchat.adapters.HomePagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabHome;
    private TabLayoutMediator tabLayoutMediator;
    private User loginUser;
    private EditText txtSearchFriends;
    private FriendSearchFragment friendSearchFragment;
    private Button btnMoreAndSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        loginUser = (User) getIntent().getSerializableExtra("logUser");
       // loginUser = new User("0798369251","Doan");

        friendSearchFragment = new FriendSearchFragment();
        txtSearchFriends = findViewById(R.id.txtSearchFriends);
        viewPager = findViewById(R.id.viewPager);
        tabHome = findViewById(R.id.tabHome);
        btnMoreAndSearch = findViewById(R.id.btn_more);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }

        viewPager.setAdapter(new HomePagerAdapter(this, loginUser));
        tabLayoutMediator = new TabLayoutMediator(tabHome, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Tin Nhắn");
                        tab.setIcon(R.drawable.stencils_ic_tab_messages);
                        break;
                    case 1:
                        tab.setText("Nhóm");
                        tab.setIcon(R.drawable.stencils_ic_tab_groups);
                        break;
                    case 2:
                        tab.setText("Danh bạ");
                        tab.setIcon(R.drawable.stencils_ic_tab_contacts);
                        break;
                    case 3:
                        tab.setText("Hồ sơ");
                        tab.setIcon(R.drawable.stencils_ic_tab_discover);
                        break;
                }
            }
        });
        tabLayoutMediator.attach();

        /*******************/


        txtSearchFriends.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,friendSearchFragment).addToBackStack(null).commit();
                    btnMoreAndSearch.setBackgroundResource(R.drawable.button_search_back);
                }
                if(!hasFocus){
                    //Toast.makeText(HomeActivity.this,"no ne",Toast.LENGTH_SHORT).show();
                    FragmentManager fm = getSupportFragmentManager();


                }
            }
        });
        txtSearchFriends.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //getSupportFragmentManager().beginTransaction().replace(R.id.container,friendSearchFragment).addToBackStack(null).commit();
                    Toast.makeText(getApplicationContext(), txtSearchFriends.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        btnMoreAndSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(friendSearchFragment.isAdded()){
                    getSupportFragmentManager().beginTransaction().remove(friendSearchFragment).commit();
                    btnMoreAndSearch.setBackgroundResource(R.drawable.button_more);
                    txtSearchFriends.clearFocus();
                }
            }
        });

    }

}