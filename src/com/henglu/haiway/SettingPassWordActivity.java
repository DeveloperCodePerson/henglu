package com.henglu.haiway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SettingPassWordActivity extends Activity{
	private TextView logininto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settingpwd);
		initView();
	}
	private void initView(){
		logininto = (TextView) findViewById(R.id.logininto);
		logininto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent  =new Intent (SettingPassWordActivity.this,LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}
}
