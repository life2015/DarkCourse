package com.twtstudio.retrox.darkcourse.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.orhanobut.hawk.Hawk;
import com.twtstudio.retrox.darkcourse.MainActivity;
import com.twtstudio.retrox.darkcourse.R;
import com.twtstudio.retrox.darkcourse.base.BaseActivity;
import com.twtstudio.retrox.darkcourse.manage.ManageActivity;

import es.dmoral.toasty.Toasty;

/**
 * Created by retrox on 12/05/2017.
 */

public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#0388D1"));
        }

        Button button = (Button) findViewById(R.id.bt_go);
        EditText editText = (EditText) findViewById(R.id.et_username);

        button.setOnClickListener(v -> {
            String sid = editText.getText().toString();
            Hawk.put("sid",sid);
            Toasty.success(this,"new student id: "+sid+" have been saved!").show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
