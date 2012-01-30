package jp.clouder.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SampleListActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boot);
        String[] sample_list = new String[] {
            "Fragments"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample_list_item, sample_list);
        setListAdapter(adapter);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "click: " + position, Toast.LENGTH_SHORT).show();
    }
}
