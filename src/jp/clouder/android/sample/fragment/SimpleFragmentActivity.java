package jp.clouder.android.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import jp.clouder.android.sample.R;

public class SimpleFragmentActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_fragment_main);
    }

    public static class FragmentSimple extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.simple_fragment_text_view, container, false);
            return ll;
        }
    }
}
