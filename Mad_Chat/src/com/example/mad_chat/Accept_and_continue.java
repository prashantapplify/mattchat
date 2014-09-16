package com.example.mad_chat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Accept_and_continue extends Activity implements OnClickListener{

	ImageView term_privacy;
	TextView accept_and_continue,acceptAndContinue_aapname,acceptAndContinue_tagname;
	Accept_and_continue ctx;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.accept_and_continue);
		
		ctx=Accept_and_continue.this;
		term_privacy=(ImageView)findViewById(R.id.term_privacy);
		term_privacy.setOnClickListener(this);
		
		accept_and_continue=(TextView)findViewById(R.id.accept_and_continue);
		accept_and_continue.setOnClickListener(this);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "helveticaltstd_light.otf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "helveticaltstd_roman.otf");
		
//		acceptAndContinue_aapname=(TextView)findViewById(R.id.acceptAndContinue_aapname);
//		acceptAndContinue_aapname.setTypeface(font);
//		acceptAndContinue_tagname=(TextView)findViewById(R.id.acceptAndContinue_tagname);
//		acceptAndContinue_aapname.setTypeface(font);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.term_privacy:
//			Toast.makeText(Accept_and_continue.this, "Email already exist.", 
//					Toast.LENGTH_LONG).show();
			
			
			break;
			
		case R.id.accept_and_continue:
			Intent mainIntent = new Intent(Accept_and_continue.this,Phone_number_enter.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;

		default:
			break;
		}
	}
	
	

}
