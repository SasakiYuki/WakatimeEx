package com.wacode.yuki.wakatimeex.UI.CreateTeam;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.wacode.yuki.wakatimeex.R;

import java.util.ArrayList;


/**
 * Created by Riberd on 2016/05/31.
 */
public class CreateTeamActivity extends AppCompatActivity {
    private TextView mTextViewIcon;
    private EditText mEditTextName;
    private EditText mEditTextDescription;
    private String mColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_create);
        mColor = "";

        setViews();
    }

    private void setViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        mTextViewIcon = (TextView) findViewById(R.id.textView_teamIcon);
        mEditTextName = (EditText) findViewById(R.id.editText_teamName);
        mEditTextDescription = (EditText) findViewById(R.id.editText_description);
        Button button_create = (Button) findViewById(R.id.button_create);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditTextName.addTextChangedListener(nameWatch);
        mEditTextDescription.addTextChangedListener(descriptionWatch);

        mTextViewIcon.setOnClickListener(onClick);
        button_create.setOnClickListener(onClick);
    }

    private TextWatcher nameWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            TextView textView_Name = (TextView) findViewById(R.id.textView_nameCount);
            int textColor = getResources().getColor(R.color.colorLightBlack);
            if (s.length() > 32) {
                textColor = getResources().getColor(R.color.fontColor_red);
            }
            textView_Name.setTextColor(textColor);
            textView_Name.setText(String.valueOf(s.length()) + "/32");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private TextWatcher descriptionWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            TextView textView_Description = (TextView) findViewById(R.id.textView_descriptionCount);
            int textColor = getResources().getColor(R.color.colorLightBlack);
            if (s.length() > 2048) {
                textColor = getResources().getColor(R.color.fontColor_red);
            }
            textView_Description.setTextColor(textColor);
            textView_Description.setText(String.valueOf(s.length()) + "/2048");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.textView_teamIcon:
                    setColorPickerDialog();
                    break;
                case R.id.button_create:
                    if (isValidColor() && isValidName() && isValidDescription()) {
                        createPostData();
                    }
                    break;
            }
        }
    };

    private void setColorPickerDialog() {
        final String[] color = {"#00a5bf", "#ff5722", "#1a1a1a", "#e91e63", "#5e35b1", "#009688", "#cddc39"
                , "#ffc107", "#e2041b", "#191970"};

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < color.length; i++) {
            list.add(color[i]);
        }

        View view = getLayoutInflater().inflate(R.layout.layout_colorpick_dialog, null);
        GridView gridView_colorPick = (GridView) view.findViewById(R.id.gridView_color);
        TeamIconGridAdapter adapter = new TeamIconGridAdapter(CreateTeamActivity.this, 0, list);
        gridView_colorPick.setAdapter(adapter);

        final AlertDialog dialog = new AlertDialog.Builder(CreateTeamActivity.this).setView(view).create();
        dialog.show();

        gridView_colorPick.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
                shapeDrawable.getPaint().setColor(Color.parseColor(color[position]));
                mTextViewIcon.setBackground(shapeDrawable);
                dialog.dismiss();
                mColor = color[position];
            }
        });
    }

    private Boolean isValidColor() {
        if (mColor.isEmpty()) {
            Toast.makeText(CreateTeamActivity.this, R.string.createTeam_icon_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private Boolean isValidName() {
        if (mEditTextName.getText().toString().isEmpty()) {
            mEditTextName.setError(getResources().getText(R.string.createTeam_name_error));
            return false;
        } else if (mEditTextName.getText().toString().length() > 32) {
            mEditTextName.setError(getResources().getText(R.string.createTeam_name_error));
            return false;
        }
        return true;
    }

    private Boolean isValidDescription() {
        if (mEditTextDescription.getText().toString().isEmpty()) {
            mEditTextDescription.setError(getResources().getText(R.string.createTeam_description_error));
            return false;
        } else if (mEditTextDescription.getText().toString().length() > 2048) {
            mEditTextDescription.setError(getResources().getText(R.string.createTeam_description_error));
            return false;
        }
        return true;
    }

    private void createPostData() {
        String teamIconColor = mColor;
        String teamName = mEditTextName.getText().toString();
        String teamDescription = mEditTextDescription.getText().toString();
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(CreateTeamActivity.this);
        dialog.setTitle(R.string.createTeam_backPressDialog_title);
        dialog.setMessage(R.string.createTeam_backPress_message);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.setNegativeButton("Cancel", null);
        dialog.show();
    }
}
