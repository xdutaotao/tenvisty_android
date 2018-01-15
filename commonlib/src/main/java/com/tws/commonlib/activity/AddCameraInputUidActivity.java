package com.tws.commonlib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.activity.CaptureActivity;
import com.tws.commonlib.R;
import com.tws.commonlib.base.A2bigA;
import com.tws.commonlib.base.TwsTools;
import com.tws.commonlib.bean.TwsDataValue;
import com.tws.commonlib.controller.NavigationBar;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2018/1/15.
 */

public class AddCameraInputUidActivity extends BaseActivity {
    EditText edtUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_camera_input_uid);
        setTitle(getResources().getString(R.string.title_add_camera));
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        NavigationBar title = (NavigationBar) findViewById(R.id.title_top);
        title.setTitle(this.getTitle().toString());
        title.setButton(NavigationBar.NAVIGATION_BUTTON_LEFT);
        title.setButton(NavigationBar.NAVIGATION_BUTTON_RIGHT);
        title.setNavigationButtonLeftListner(new NavigationBar.NavigationBarButtonListener() {

            @Override
            public void OnNavigationButtonClick(int which) {
                switch (which) {
                    case NavigationBar.NAVIGATION_BUTTON_LEFT:
                        finish();
                        break;
                    case NavigationBar.NAVIGATION_BUTTON_RIGHT:
                        confirmUID();
                        break;
                }
            }
        });
        edtUID = (EditText) findViewById(R.id.edtUID);
        edtUID.setTransformationMethod(new A2bigA());
    }

    private void confirmUID() {
        String uid = edtUID.getText().toString();
        if(uid.isEmpty()){
            showAlert(getString(R.string.alert_input_camera_uid));
            return;
        }
        uid = TwsTools.takeInnerUid(uid);
        if (uid == null) {
            showAlert(getString(R.string.alert_invalid_camera_uid));
        } else {
            Intent intent = this.getIntent();
            intent.putExtra(TwsDataValue.EXTRA_KEY_UID, uid);
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    public  void  clickLine(View view){
        ((LinearLayout) view).getChildAt(1).requestFocus();
    }
}