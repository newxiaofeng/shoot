package com.feng.shoot.activity;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.feng.shoot.R;
import com.feng.shoot.bridge.Controller;
import com.feng.shoot.common.dao.user.UserDao;
import com.feng.shoot.common.database.AppDatabase;

import wendu.dsbridge.DWebView;

public class ControllerActivity extends AppCompatActivity {

    private DWebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        mWebview = (DWebView) findViewById(R.id.webview);

        DWebView.setWebContentsDebuggingEnabled(true);

        mWebview.addJavascriptObject((new Controller(this)), null);
        // mWebview.addJavascriptObject(new JsApi(), null);
        // mWebview.addJavascriptObject(new JsEchoApi(),"echo");
        mWebview.loadUrl("file:///android_asset/index.html");


        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ControllerActivity.this,MainActivity.class);
                startActivity(intent);
                ControllerActivity.this.finish();
            }
        },3000);*/

        /*AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "AppDatabase").build();

        UserDao userDao = db.getUserDao();*/
    }


}
