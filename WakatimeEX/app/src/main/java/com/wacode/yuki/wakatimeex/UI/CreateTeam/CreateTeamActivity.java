package com.wacode.yuki.wakatimeex.UI.CreateTeam;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wacode.yuki.wakatimeex.R;


/**
 * Created by Riberd on 2016/05/31.
 */
public class CreateTeamActivity extends AppCompatActivity {
    private ImageView mImageView_Icon;
    private EditText mEditText_Name;
    private EditText mEditText_Description;
    private Uri mUri;
    private String mBase64;
    private static final int REQUEST_CHOOSER = 1000;
    public static final int REQUEST_CODE = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_create);

        mBase64 = "";
        setViews();
    }

    private void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        mImageView_Icon = (ImageView) findViewById(R.id.imageView_teamIcon);
        mEditText_Name = (EditText) findViewById(R.id.editText_teamName);
        mEditText_Description = (EditText) findViewById(R.id.editText_description);
        Button button_create = (Button) findViewById(R.id.button_create);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditText_Name.addTextChangedListener(nameWatch);
        mEditText_Description.addTextChangedListener(descriptionWatch);

        mImageView_Icon.setOnClickListener(onClick);
        button_create.setOnClickListener(onClick);
    }

    private TextWatcher nameWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            TextView textView_Name = (TextView)findViewById(R.id.textView_nameCount);
            int textColor = getResources().getColor(R.color.colorLightBlack);
            if (s.length() > 32){
                textColor = getResources().getColor(R.color.fontColor_red);
            }
            textView_Name.setTextColor(textColor);
            textView_Name.setText(String.valueOf(s.length()) +"/32");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
        @Override
        public void afterTextChanged(Editable s) {}
    };

    private TextWatcher descriptionWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            TextView textView_Description = (TextView)findViewById(R.id.textView_descriptionCount);
            int textColor = getResources().getColor(R.color.colorLightBlack);
            if (s.length() > 2048){
                textColor = getResources().getColor(R.color.fontColor_red);
            }
            textView_Description.setTextColor(textColor);
            textView_Description.setText(String.valueOf(s.length())+"/2048");
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
        @Override
        public void afterTextChanged(Editable s) {}
    };

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageView_teamIcon:
                    showGallery();
                    break;
                case R.id.button_create:
                    if (isValidIcon() && isValidName() && isValidDescription()){
                        createPostData();
                    }
                    break;
            }
        }
    };

    private void showGallery() {
        String photoName = System.currentTimeMillis() + ".jpg";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, photoName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        mUri = getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, mUri);

        Intent intentGallery;
        if (Build.VERSION.SDK_INT < 19) {
            intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
            intentGallery.setType("image/*");
        } else {
            intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
            intentGallery.setType("image/jpeg");
        }
        Intent intent = Intent.createChooser(intentCamera, "画像の選択");
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intentGallery});
        startActivityForResult(intent, REQUEST_CHOOSER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHOOSER) {
            if (resultCode != RESULT_OK) {
                return;
            }
            Uri resultUri = (data != null ? data.getData() : mUri);
            if (resultUri == null) {
                return;
            }
            Intent intent = new Intent(CreateTeamActivity.this,TrimBitmapActivity.class);
            intent.setData(resultUri);
            startActivityForResult(intent,REQUEST_CODE);
        }else if (requestCode == REQUEST_CODE){
            SharedPreferences sp = getSharedPreferences(TrimBitmapActivity.SP_MODE,MODE_PRIVATE);
            mImageView_Icon.setImageBitmap(getBitmapFromBase64(sp.getString(TrimBitmapActivity.SP_BITMAP,"")));
            mBase64 = sp.getString(TrimBitmapActivity.SP_BITMAP,"");
        }
    }

    private Bitmap getBitmapFromBase64(String base64){
        byte[] bytes = Base64.decode(base64.getBytes(),Base64.DEFAULT);
        Bitmap icon = null;
        if (bytes != null){
            icon = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        }
        return icon;
    }

    private Boolean isValidIcon(){
        if (mBase64.isEmpty()){
            Toast.makeText(CreateTeamActivity.this,R.string.createTeam_icon_error,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private Boolean isValidName(){
        if (mEditText_Name.getText().toString().isEmpty()){
            mEditText_Name.setError(getResources().getText(R.string.createTeam_name_error));
            return false;
        }else if (mEditText_Name.getText().toString().length() > 32){
            mEditText_Name.setError(getResources().getText(R.string.createTeam_name_error));
            return false;
        }
        return true;
    }

    private Boolean isValidDescription(){
        if (mEditText_Description.getText().toString().isEmpty()){
            mEditText_Description.setError(getResources().getText(R.string.createTeam_description_error));
            return false;
        }else if(mEditText_Description.getText().toString().length() > 2048){
            mEditText_Description.setError(getResources().getText(R.string.createTeam_description_error));
            return false;
        }
        return true;
    }

    private void createPostData(){
        String base64 = mBase64;
        String teamName = mEditText_Name.getText().toString();
        String teamDescription = mEditText_Description.getText().toString();

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = true;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
    }

}
