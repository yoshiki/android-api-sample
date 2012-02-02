package jp.clouder.android.sample.media;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import jp.clouder.android.sample.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SimpleCameraActivity extends Activity {
    public static final String TAG = SimpleCameraActivity.class.getSimpleName();

    private Button mShowCamera;
    private ImageView mImageView;
    
    private Uri mImageUri;
    private Bitmap mBitmap;

    public static final int REQUEST_CODE_CAMERA = 1; 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_camera);
        
        mImageView = (ImageView)findViewById(R.id.image_view);

        mShowCamera = (Button)findViewById(R.id.show_camera);
        mShowCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String filename = System.currentTimeMillis() + ".jpg";
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, filename);
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                mImageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                */
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            // bitmapは必ず空に（メモリリーク対応
            if (mBitmap != null) {
                mBitmap.recycle();
            }

            switch (reqCode) {
                case REQUEST_CODE_CAMERA:
                    if (mImageUri != null && mImageUri.toString().length() > 0) {
                        Log.v(TAG, "get from mImageUri");
                        mBitmap = uriToBitmap(mImageUri);
                    }
                    else if (intent != null) {
                        if (intent.getData() != null) {
                            Log.v(TAG, "get from intent.getData()");
                            mImageUri = intent.getData();
                            mBitmap = uriToBitmap(mImageUri);
                        }
                        else if (intent.getExtras() != null && intent.getExtras().get("data") != null) {
                            Log.v(TAG, "get from intent.getExtras().get(\"data\")");
                            mBitmap = (Bitmap)intent.getExtras().get("data");
                        }
                    }
                    mImageView.setImageBitmap(mBitmap);
                    
                    break;
                default:
                    break;
            }
        }
    }

    public Bitmap uriToBitmap(Uri uri) {
        InputStream in = null;
        try {
            in = getContentResolver().openInputStream(uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(in, null, options);
        }
        catch (FileNotFoundException e) {
            try {
                if (in!=null) in.close();
            } catch(IOException e2) {}
            return null;
        }
    }
}
