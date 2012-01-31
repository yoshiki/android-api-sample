package jp.clouder.android.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();
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
