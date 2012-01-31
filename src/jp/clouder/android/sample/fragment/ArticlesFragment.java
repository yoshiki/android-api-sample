package jp.clouder.android.sample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import jp.clouder.android.sample.R;

public class ArticlesFragment extends ListFragment {
    boolean mDualPane;
    int mCurCheckPosition = 0;
    int mShownCheckPosition = -1;

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] list = { "a", "b", "c", "d", "e", "f", "g" };
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list));

        View detailView = getActivity().findViewById(R.id.details);
        mDualPane = detailView != null && detailView.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
            mShownCheckPosition = savedInstanceState.getInt("shownChoice", -1);
        }

        if (mDualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
        outState.putInt("shownChoice", mShownCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.v("fragment", "clicked!");
        showDetails(position);
    }

    public void showDetails(int index) {
        mCurCheckPosition = index;

        if (mDualPane) {
            getListView().setItemChecked(index, true);
            DetailsFragment df = (DetailsFragment)getFragmentManager().findFragmentById(R.id.details);
            if (df == null || df.getShownIndex() != index) {
                df = DetailsFragment.newInstance(index);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details, df);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                mShownCheckPosition = index;
            }
        } else {
            Intent intent = new Intent(getActivity(), DetailsFragmentActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }
}
