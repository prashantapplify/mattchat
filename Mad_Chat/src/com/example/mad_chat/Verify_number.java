package com.example.mad_chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Verify_number extends Activity implements OnClickListener{

	ImageView Verify_back,Verify_farword;
	TextView verify_header_text;
	InputMethodManager imm;
	LinearLayout verify_lay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.verify_number);
		
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "helveticaltstd_bold.otf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "helveticaltstd_roman.otf");
		Typeface font3 = Typeface.createFromAsset(getAssets(), "helveticaltstd_light.otf");
		
		verify_header_text=(TextView)findViewById(R.id.verify_header_text);
		verify_header_text.setTypeface(font3);		
		Verify_back=(ImageView)findViewById(R.id.Verify_back);
		Verify_back.setOnClickListener(this);
		Verify_farword=(ImageView)findViewById(R.id.Verify_farword);
		Verify_farword.setOnClickListener(this);
		verify_lay=(LinearLayout)findViewById(R.id.verify_lay);
		verify_lay.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Verify_back:
			Intent mainIntent1 = new Intent(Verify_number.this,Phone_number_enter.class);
			startActivity(mainIntent1); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;

		case R.id.Verify_farword:
			Intent mainIntent = new Intent(Verify_number.this,Create_profile.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;
			
		case R.id.verify_lay:
			imm.hideSoftInputFromWindow(verify_lay.getWindowToken(), 0);
			break;
		default:
			break;
		}
		
	}
	
	 public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			Intent mainIntent1 = new Intent(Verify_number.this,Phone_number_enter.class);
			startActivity(mainIntent1); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			
	    }

}
