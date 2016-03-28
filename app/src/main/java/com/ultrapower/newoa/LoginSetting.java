package com.ultrapower.newoa;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_setting);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar == null) {
            return;
        }
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar_layout);


        //study
        TextView tv1 = (TextView)findViewById(R.id.textView1);
        tv1.setText(getResources().getString(R.string.app_name));
        tv1.setTextColor(getResources().getColor(R.color.colorAccent, getTheme()));
        ImageView iv1 = (ImageView)findViewById(R.id.imageView1);

    }
}
