package jp.clouder.android.sample.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragmentActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            MyDetailsFragment myDetails = new MyDetailsFragment();
            myDetails.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, myDetails).commit();
        }
    }

    public static class MyDetailsFragment extends Fragment {
        public static MyDetailsFragment newInstance(int index) {
            MyDetailsFragment f = new MyDetailsFragment();
            Bundle args = new Bundle();
            args.putInt("index", index);
            f.setArguments(args);
            return f;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (container == null) return null;

            TextView detailTextView = new TextView(getActivity());
            detailTextView.setText("You tapped:" + getArguments().getInt("index", 0));
            return detailTextView;
        }

        public int getShownIndex() {
            return getArguments().getInt("index", 0);
        }
    }
}
