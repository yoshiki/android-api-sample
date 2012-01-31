package jp.clouder.android.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import jp.clouder.android.sample.R;

public class ListFragmentActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fragment_main);
    }

    public static class MyListFragment extends ListFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.list_fragment_list, container, false);
            return ll;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            String[] list = { "a", "b", "c", "d", "e", "f", "g" };
            ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
            setListAdapter(adapter);
        }

        @Override
        public void onListItemClick (ListView l, View v, int position, long id) {
            Toast.makeText(getActivity(), "Tap " + position, Toast.LENGTH_SHORT).show();
        }
    }
}
