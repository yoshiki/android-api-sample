package jp.clouder.android.sample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import jp.clouder.android.sample.fragment.ListFragmentWithDetailsActivity;
import jp.clouder.android.sample.fragment.ListFragmentActivity;
import jp.clouder.android.sample.fragment.SimpleFragmentActivity;
import jp.clouder.android.sample.fragment.SimpleViewPagerActivity;

import java.util.*;

public class SampleListActivity extends ListActivity {
    private SampleList mSampleList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boot);
        mSampleList = new SampleList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.sample_list_item, mSampleList.getSampleNameList());
        setListAdapter(adapter);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, (Class)mSampleList.getSampleClass(position));
        startActivity(intent);
    }

    public class SampleList {
        private LinkedHashMap<String, Object> mSampleList = new LinkedHashMap<String, Object>();

        public SampleList() {
            mSampleList.put("Simple Fragment", SimpleFragmentActivity.class );
            mSampleList.put("List Fragment", ListFragmentActivity.class);
            mSampleList.put("List and detail Fragment", ListFragmentWithDetailsActivity.class);
            mSampleList.put("Simple ViewPager", SimpleViewPagerActivity.class);
        }

        public String[] getSampleNameList() {
            return mSampleList.keySet().toArray(new String[0]);
        }

        public Object getSampleClass(int index) {
            Iterator iter = mSampleList.keySet().iterator();
            for (int i = 0; i < index; i++) {
                iter.next();
            }
            return mSampleList.get(iter.next());
        }
    }
}
