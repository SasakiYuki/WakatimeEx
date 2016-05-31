package com.wacode.yuki.wakatimeex.UI.CreateTeam;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.isseiaoki.simplecropview.CropImageView;
import com.wacode.yuki.wakatimeex.R;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Riberd on 2016/05/31.
 */
public class TrimBitmapActivity extends AppCompatActivity {
    private CropImageView mCropImageView;
    public static final String SP_MODE = "sp_mode";
    public static final String SP_BITMAP = "sp_bitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trime_image);

        setViews();
    }

    private void setViews() {
        mCropImageView = (CropImageView) findViewById(R.id.cropImageView);
        ImageView imageView_left = (ImageView) findViewById(R.id.imageView_leftRota);
        ImageView imageView_right = (ImageView) findViewById(R.id.imageView_rightRota);
        Button button_crop = (Button) findViewById(R.id.button_crop);

        mCropImageView.setImageBitmap(convertImageToByte());
        imageView_left.setOnClickListener(onClick);
        imageView_right.setOnClickListener(onClick);
        button_crop.setOnClickListener(onClick);
    }

    private Bitmap convertImageToByte() {
        byte[] data;
        Uri uri = getIntent().getData();
        Bitmap icon = null;
        try {
            ContentResolver cr = getBaseContext().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);
            data = baos.toByteArray();

            if (data != null) {
                icon = BitmapFactory.decodeByteArray(data, 0, data.length);
            }
            return icon;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return icon;
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageView_leftRota:
                    mCropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
                    break;
                case R.id.imageView_rightRota:
                    mCropImageView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
                    break;
                case R.id.button_crop:
                    setBitmapToSp();
                    setResult(CreateTeamActivity.REQUEST_CODE);
                    finish();
                    break;
            }
        }
    };

    private void setBitmapToSp(){
        Bitmap icon = mCropImageView.getCroppedBitmap();
        icon = Bitmap.createScaledBitmap(icon,100,100,false);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String bitmapString = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);

        SharedPreferences sharedPreferences = getSharedPreferences(SP_MODE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SP_BITMAP,bitmapString);
        editor.commit();
    }


}
