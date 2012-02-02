package jp.clouder.android.sample.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import jp.clouder.android.sample.R;

public class SimpleDialogFragmentActivity extends FragmentActivity {
    private Button mShowDialogOnCreateDialog;
    private Button mShowDialogOnCreateView;
    private int mStackLevel = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_dialog_fragment_main);

        mShowDialogOnCreateDialog = (Button)findViewById(R.id.show_dialog_on_create_dialog);
        mShowDialogOnCreateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogOnCreateDialog();
            }
        });

        mShowDialogOnCreateView = (Button)findViewById(R.id.show_dialog_on_create_view);
        mShowDialogOnCreateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogOnCreateView();
            }
        });
    }

    private void showDialogOnCreateDialog() {
        mStackLevel++;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        MyCreateDialogFragment newFragment = MyCreateDialogFragment.newInstance(mStackLevel);
        newFragment.show(ft, "dialog");
    }

    private void showDialogOnCreateView() {
        mStackLevel++;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        MyCreateViewFragment newFragment = MyCreateViewFragment.newInstance(mStackLevel);
        newFragment.show(ft, "dialog");
    }

    public void doPositiveClick() {
        Toast.makeText(this, "positive clicked", Toast.LENGTH_SHORT).show();
    }

    public void doNegativeClick() {
        Toast.makeText(this, "negative clicked", Toast.LENGTH_SHORT).show();
    }

    public static class MyCreateDialogFragment extends DialogFragment {
        static MyCreateDialogFragment newInstance(int num) {
            MyCreateDialogFragment f = new MyCreateDialogFragment();
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle("Simple Dialog Fragment")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ((SimpleDialogFragmentActivity) getActivity()).doPositiveClick();
                                }
                            }
                    )
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ((SimpleDialogFragmentActivity) getActivity()).doNegativeClick();
                                }
                            }
                    )
                    .create();
        }
    }

    public static class MyCreateViewFragment extends DialogFragment {
        static MyCreateViewFragment newInstance(int num) {
            MyCreateViewFragment f = new MyCreateViewFragment();
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            int style = DialogFragment.STYLE_NORMAL;
            int theme = android.R.style.Theme_Dialog;
            setStyle(style, theme);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return (View)inflater.inflate(R.layout.simple_dialog_fragment_view, container, false);
        }
    }
}
