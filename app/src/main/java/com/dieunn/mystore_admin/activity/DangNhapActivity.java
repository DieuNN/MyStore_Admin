package com.dieunn.mystore_admin.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dieunn.mystore_admin.R;
import com.dieunn.mystore_admin.fragment.DangNhapFragment;

public class DangNhapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setDefaultFragment();
    }

    // tu du lieu activity truoc, chon activity se xuat hien
    private void setDefaultFragment() {
        Fragment fragment = new DangNhapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLoginActivity, fragment).commit();

    }
}