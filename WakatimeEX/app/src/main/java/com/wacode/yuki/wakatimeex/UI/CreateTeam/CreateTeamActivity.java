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
import android.util.Log;
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
    private TextView mTextView_icon;
    private EditText mEditText_Name;
    private EditText mEditText_Description;
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
        mTextView_icon = (TextView) findViewById(R.id.textView_teamIcon);
        mEditText_Name = (EditText) findViewById(R.id.editText_teamName);
        mEditText_Description = (EditText) findViewById(R.id.editText_description);
        Button button_create = (Button) findViewById(R.id.button_create);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditText_Name.addTextChangedListener(nameWatch);
        mEditText_Description.addTextChangedListener(descriptionWatch);

        mTextView_icon.setOnClickListener(onClick);
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
                mTextView_icon.setBackground(shapeDrawable);
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
        if (mEditText_Name.getText().toString().isEmpty()) {
            mEditText_Name.setError(getResources().getText(R.string.createTeam_name_error));
            return false;
        } else if (mEditText_Name.getText().toString().length() > 32) {
            mEditText_Name.setError(getResources().getText(R.string.createTeam_name_error));
            return false;
        }
        return true;
    }

    private Boolean isValidDescription() {
        if (mEditText_Description.getText().toString().isEmpty()) {
            mEditText_Description.setError(getResources().getText(R.string.createTeam_description_error));
            return false;
        } else if (mEditText_Description.getText().toString().length() > 2048) {
            mEditText_Description.setError(getResources().getText(R.string.createTeam_description_error));
            return false;
        }
        return true;
    }

    private void createPostData() {
        String teamIconColor = mColor;
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
