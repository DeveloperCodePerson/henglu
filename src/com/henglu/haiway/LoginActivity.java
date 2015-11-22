package com.henglu.haiway;

import com.henglu.haiway.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener {
	private TextView createnewuser,forgetPwd;
	private EditText user, password;
	private Button login;
	private String telephone, pwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		createnewuser = (TextView) findViewById(R.id.createnewuser);
		createnewuser.setOnClickListener(this);
		forgetPwd= (TextView) findViewById(R.id.forgetPwd);
		forgetPwd.setOnClickListener(this);
		user = (EditText) findViewById(R.id.telephone);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(this);
		telephone= user.getText().toString().trim();
		pwd = password.getText().toString().trim();
		if (Util.isBlank(telephone)||Util.isBlank(pwd)){
			login.setTextColor(getResources().getColor(R.color.login_btn_state));
			login.setEnabled(false);
		}else{
			login.setTextColor(getResources().getColor(R.color.white));
			login.setEnabled(true);
			
		}
		MyWatcher myWatcher = new MyWatcher();
		user.addTextChangedListener(myWatcher);
		password.addTextChangedListener(myWatcher);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.createnewuser:
			Intent intent = new Intent(LoginActivity.this,
					RegisterActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;

		case R.id.login:

			break;
		case R.id.forgetPwd:
			Intent intent1 = new Intent(LoginActivity.this,
					RegisterActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			break;
		}
	}
	class MyWatcher implements TextWatcher{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			telephone= user.getText().toString().trim();
			pwd = password.getText().toString().trim();
			if (Util.isBlank(telephone)||Util.isBlank(pwd)){
				login.setTextColor(getResources().getColor(R.color.login_btn_state));
				login.setEnabled(false);
			}else{
				login.setTextColor(getResources().getColor(R.color.white));
				login.setEnabled(true);
				
			}
		
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
