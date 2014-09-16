package com.example.mad_chat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.view.Window;

import com.chatapp.service.Login_through_accesstoken;

public class MainActivity extends Activity {
	 String result=null;
		SharedPreferences sp,w;
		String loginstatus;
		Context ctx;
		ProgressDialog bar;
		String android_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		ctx=MainActivity.this;
		android_id= Secure.getString(MainActivity.this.getContentResolver(),
	            Secure.ANDROID_ID);
		try {
			new Handler().postDelayed(new Runnable(){
				public void run() {

					
					if(sp.getString("accesstoken","").equalsIgnoreCase("") || sp.getString("accesstoken","")==null)
					{
						Intent mainIntent = new Intent(MainActivity.this,Accept_and_continue.class);
						MainActivity.this.startActivity(mainIntent); 
						overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
						MainActivity.this.finish();
					}else
					{
						new Login_through_accesstoken(ctx, bar, android_id,sp.getString("accesstoken",""),sp).execute("main");
						
						/*Intent mainIntent = new Intent(MainActivity.this,Home_page.class);
						MainActivity.this.startActivity(mainIntent); 
						MainActivity.this.finish();*/
					}
				}

			}, 3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
}
