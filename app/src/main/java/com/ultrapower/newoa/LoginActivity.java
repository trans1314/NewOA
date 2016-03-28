package com.ultrapower.newoa;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ultrapower.newoa.tools.ImageTool;
import com.ultrapower.newoa.widget.XEditText;

public class LoginActivity extends AppCompatActivity {

    private boolean eyeOpen;
    private long exitTime = 0l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView logoOA = (ImageView)findViewById(R.id.lognewoa);
        logoOA.setImageBitmap(ImageTool.decodeSampledBitmapFromResource(getResources(), R.drawable.logonewoa, 150, 100));
        EditText et_username = (EditText)findViewById(R.id.username);
        XEditText et_userpwd = (XEditText)findViewById(R.id.userpwd);
        Drawable drawable1 = getResources().getDrawable(R.drawable.loguser);
        drawable1.setBounds(0, 0, 60, 60);
        et_username.setCompoundDrawables(drawable1, null, null, null);
        Drawable drawable2 = getResources().getDrawable(R.drawable.logpwd);
        drawable2.setBounds(0, 0, 60, 60);
        Drawable drawable3 = getResources().getDrawable(R.drawable.pwdeyeclose);
        drawable3.setBounds(0, 0, 70, 40);
        et_userpwd.setCompoundDrawables(drawable2, null, drawable3, null);
        eyeOpen = false;
        et_userpwd.setDrawableRightListener(new XEditText.DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                XEditText editText = (XEditText)view;
                Drawable drawable2 = getResources().getDrawable(R.drawable.logpwd);
                drawable2.setBounds(0, 0, 60, 60);
                Drawable drawable = null;
                if(eyeOpen) {
                    drawable = getResources().getDrawable(R.drawable.pwdeyeclose);
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    drawable = getResources().getDrawable(R.drawable.pwdeyeopen);
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                drawable.setBounds(0, 0, 70,40);
                editText.setCompoundDrawables(drawable2, null, drawable, null);
                editText.postInvalidate();
                eyeOpen = !eyeOpen;
            }
        });
    }

    /**
     * 点击登录
     * @param view
     */
    public void login(View view) {
        EditText et_username = (EditText)findViewById(R.id.username);
        EditText et_password = (EditText)findViewById(R.id.userpwd);
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        if(username == null || "".equals(username.trim())
                || password == null || "".equals(password.trim())) {
            Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
        } else {
            Button btn_login = (Button)view;
            btn_login.setText("登录中...");
            btn_login.setEnabled(false);
            et_username.setEnabled(false);
            et_password.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if(System.currentTimeMillis() - exitTime > 1000l) {
                Toast.makeText(LoginActivity.this, "连续摁两次返回退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 登录设置
     * @param view
     */
    public void loginSetting(View view) {
        Intent intent = new Intent(LoginActivity.this, LoginSetting.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
