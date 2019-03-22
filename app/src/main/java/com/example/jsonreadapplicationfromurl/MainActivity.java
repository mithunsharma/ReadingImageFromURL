package com.example.jsonreadapplicationfromurl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageDetailFragment mImageDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        mImageDetailFragment = ImageDetailFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, mImageDetailFragment)
                .commit();

    }
}
