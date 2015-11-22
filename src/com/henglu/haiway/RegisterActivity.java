package com.henglu.haiway;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends Activity implements OnClickListener{
	private TextView logininto;
	private Button check;
	private TimeCount time;
	private Button getCode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
	}
	private void initView(){
		time = new TimeCount(60000*1, 1000);
		logininto = (TextView) findViewById(R.id.logininto);
		getCode = (Button) findViewById(R.id.getCode);
		getCode.setOnClickListener(this);
		logininto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent  =new Intent (RegisterActivity.this,LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		check = (Button) findViewById(R.id.check);
		check.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent  =new Intent (RegisterActivity.this,SettingPassWordActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.getCode:
			//获取验证码
			time.start();
			break;

		default:
			break;
		}
	}
	//验证码倒计时
	class TimeCount extends CountDownTimer{

		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			getCode.setClickable(false);
			getCode.setText("重发" + "(" + millisUntilFinished / 1000 + ")");
		}

		@Override
		public void onFinish() {
			getCode.setText("重新获取");
			getCode.setClickable(true);
			getCode.setEnabled(true);
		}
		
	}
}
