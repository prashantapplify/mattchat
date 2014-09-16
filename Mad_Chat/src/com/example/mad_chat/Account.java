package com.example.mad_chat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Account extends Activity implements OnClickListener{

	ImageView account_back;
	TextView account_privacy,account_changMyNumber,account_deleteMyAccount,account_name,account_numer;
	Context ctx;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.account);
		
		ctx=Account.this;
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "helveticaltstd_bold.otf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "helveticaltstd_roman.otf");
		Typeface font3 = Typeface.createFromAsset(getAssets(), "helveticaltstd_light.otf");
		account_back=(ImageView)findViewById(R.id.account_back);
		account_back.setOnClickListener(this);
		account_privacy=(TextView)findViewById(R.id.account_privacy);
		account_privacy.setOnClickListener(this);
		account_changMyNumber=(TextView)findViewById(R.id.account_changMyNumber);
		account_changMyNumber.setOnClickListener(this);
		account_deleteMyAccount=(TextView)findViewById(R.id.account_deleteMyAccount);
		account_deleteMyAccount.setOnClickListener(this);
		account_name=(TextView)findViewById(R.id.account_name);
		account_name.setText(sp.getString("name", ""));
		account_numer=(TextView)findViewById(R.id.account_numer);
		account_numer.setText(sp.getString("PhoneNumber", ""));
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.account_back:
			if(sp.getString("Home_page", "").equals("Home")){
//				Intent mainIntent = new Intent(Account.this,Home_page.class);
//				startActivity(mainIntent); 
//				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
				}
			else{
//				Intent mainIntent = new Intent(Account.this,Contacts.class);
//				startActivity(mainIntent); 
//				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
			}
			break;
			
		case R.id.account_privacy:
			
			break;
			
		case R.id.account_changMyNumber:
			new AlertDialog.Builder(this)
		    .setTitle("Alert")
		    .setMessage("Are you sure you want to change your number ?")
		    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // continue with delete
		        	Intent mainIntent = new Intent(Account.this,Accept_and_continue.class);
					startActivity(mainIntent); 
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
					finish();
		        }
		     })
		    .setNegativeButton("No", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // do nothing
		        }
		     })
		    .setIcon(android.R.drawable.ic_dialog_alert)
		     .show();
			
			break;
			
		case R.id.account_deleteMyAccount:
			
			break;

		default:
			break;
		}
	}
	
	

}
