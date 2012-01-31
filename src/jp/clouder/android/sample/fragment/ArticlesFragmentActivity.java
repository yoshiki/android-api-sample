package jp.clouder.android.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import jp.clouder.android.sample.R;

public class ArticlesFragmentActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articles_fragment_main);
    }
}
