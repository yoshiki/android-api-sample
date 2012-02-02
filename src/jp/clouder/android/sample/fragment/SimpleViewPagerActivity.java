package jp.clouder.android.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import jp.clouder.android.sample.R;

public class SimpleViewPagerActivity extends FragmentActivity {
    public static final int NUM_ITEMS = 5;

    MyAdapter mAdapter;
    ViewPager mPager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_view_pager_main);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        Button first = (Button)findViewById(R.id.goto_first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(0);
            }
        });

        Button prev = (Button)findViewById(R.id.goto_prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPager.getCurrentItem() > 0)
                    mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                else
                    Toast.makeText(SimpleViewPagerActivity.this, "No more previous page", Toast.LENGTH_SHORT).show();
            }
        });

        Button next = (Button)findViewById(R.id.goto_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPager.getCurrentItem() < NUM_ITEMS - 1)
                    mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                else
                    Toast.makeText(SimpleViewPagerActivity.this, "No more next page", Toast.LENGTH_SHORT).show();
            }
        });

        Button last = (Button)findViewById(R.id.goto_last);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(NUM_ITEMS - 1);
            }
        });
    }

    public static class MyAdapter extends FragmentPagerAdapter
    {
        public MyAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public int getCount()
        {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position)
        {
            return MyFragment.newInstance(position);
        }
    }

    public static class MyFragment extends Fragment
    {
        static MyFragment newInstance(int num)
        {
            MyFragment f = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.simple_view_pager_pagerview, container, false);
            TextView textView = (TextView)ll.findViewById(R.id.textview);
            textView.setText("position: " + String.valueOf(getArguments().getInt("num")));
            return ll;
        }
    }
}
