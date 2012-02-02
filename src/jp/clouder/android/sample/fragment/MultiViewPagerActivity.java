package jp.clouder.android.sample.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.clouder.android.sample.R;

public class MultiViewPagerActivity extends FragmentActivity {
    public static final int NUM_ITEMS = 10;

    private ViewPager mPager0;
    private ViewPager mPager1;
    private MyAdapter0 mAdapter0;
    private MyAdapter1 mAdapter1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_view_pager_main);

        mAdapter0 = new MyAdapter0(getSupportFragmentManager());
        mPager0 = (ViewPager)findViewById(R.id.pager1);
        mPager0.setAdapter(mAdapter0);
        mPager0.setBackgroundColor(Color.GRAY);

        mAdapter1 = new MyAdapter1(getSupportFragmentManager());
        mPager1 = (ViewPager)findViewById(R.id.pager2);
        mPager1.setAdapter(mAdapter1);
        mPager1.setBackgroundColor(Color.LTGRAY);
    }

    public static class MyAdapter0 extends FragmentPagerAdapter {
        public MyAdapter0(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment0.newInstance(position);
        }
    }

    public static class MyFragment0 extends Fragment {
        static MyFragment0 newInstance(int num) {
            MyFragment0 f = new MyFragment0();
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.multi_view_pager_pager_view, container, false);
            TextView textView = (TextView)ll.findViewById(R.id.text_view);
            textView.setText("position: " + String.valueOf(getArguments().getInt("num")));
            return ll;
        }
    }

    public static class MyAdapter1 extends FragmentPagerAdapter {
        public MyAdapter1(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment1.newInstance(position);
        }
    }

    public static class MyFragment1 extends Fragment {
        static MyFragment1 newInstance(int num) {
            MyFragment1 f = new MyFragment1();
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.multi_view_pager_pager_view, container, false);
            TextView textView = (TextView)ll.findViewById(R.id.text_view);
            textView.setText("position: " + String.valueOf(getArguments().getInt("num")));
            return ll;
        }
    }
}
