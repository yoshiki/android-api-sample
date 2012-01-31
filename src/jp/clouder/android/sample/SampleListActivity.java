package jp.clouder.android.sample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import jp.clouder.android.sample.fragment.ArticlesFragmentActivity;
import jp.clouder.android.sample.fragment.ListFragmentActivity;
import jp.clouder.android.sample.fragment.SimpleFragmentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        private ArrayList<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();

        public SampleList() {
            mList.add(new HashMap<String, Object>() {{
                put("Simple Fragment", SimpleFragmentActivity.class );
            }});
            mList.add(new HashMap<String, Object>() {{
                put("List Fragment", ListFragmentActivity.class);
            }});
            mList.add(new HashMap<String, Object>() {{
                put("List and detail Fragment", ArticlesFragmentActivity.class);
            }});
        }

        public String[] getSampleNameList() {
            String [] sampleNameList = new String[mList.size()];
            for (int i = 0; i < mList.size(); i++) {
                Iterator j = getSampleMap(i).keySet().iterator();
                sampleNameList[i] = (String)j.next();
            }
            return sampleNameList;
        }
        
        public HashMap<String, Object> getSampleMap(int index) {
            return (HashMap<String, Object>)mList.get(index);
        }

        public Object getSampleClass(int index) {
            HashMap<String, Object> sample = getSampleMap(index);
            Iterator i = sample.keySet().iterator();
            return sample.get((String)i.next());
        }
    }
}
