package com.example.mad_chat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chatapp.service.Get_createProfile;

public class Create_profile extends Activity implements OnClickListener{

	ImageView Create_farword;
	TextView createProfile_phoneNumber,createprofile_headerText,createprofile_phoneText,createprofile_phoneText1;
	SharedPreferences sp;
	
	Context ctx;
	ProgressDialog bar;
	EditText createProfile_name;
	LinearLayout createProfile_lay;
	InputMethodManager imm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.create_profile);
		
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		ctx=Create_profile.this;
		Typeface font = Typeface.createFromAsset(getAssets(), "helveticaltstd_bold.otf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "helveticaltstd_roman.otf");
		Typeface font3 = Typeface.createFromAsset(getAssets(), "helveticaltstd_light.otf");
		
		Create_farword=(ImageView)findViewById(R.id.Create_farword);
		Create_farword.setOnClickListener(this);
		
		createProfile_phoneNumber=(TextView)findViewById(R.id.createProfile_phoneNumber);
		createProfile_phoneNumber.setText(sp.getString("PhoneNumber", ""));
		createProfile_phoneNumber.setTypeface(font3);
		createprofile_headerText=(TextView)findViewById(R.id.createprofile_headerText);
		createprofile_headerText.setTypeface(font3);
		createprofile_phoneText=(TextView)findViewById(R.id.createprofile_phoneText);
		createprofile_phoneText.setTypeface(font3);
		createprofile_phoneText1=(TextView)findViewById(R.id.createprofile_phoneText1);
		createprofile_phoneText1.setTypeface(font3);
		createProfile_name=(EditText)findViewById(R.id.createProfile_name);
		if(!sp.getString("name", "").equalsIgnoreCase("") && sp.getString("name", "")!=null){
		createProfile_name.setText(sp.getString("name", ""));
		}
		createProfile_lay=(LinearLayout)findViewById(R.id.createProfile_lay);
		createProfile_lay.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Create_farword:
			if(createProfile_name.getText().toString().trim().length()>0){
			new Get_createProfile(ctx, bar, sp.getString("accesstoken", ""),createProfile_name.getText().toString().trim(),sp).execute("main");
			}
			else{
				Toast.makeText(Create_profile.this, "Please enter your name.", 
						Toast.LENGTH_LONG).show();
			}
			
			/*Intent mainIntent = new Intent(Create_profile.this,Home_page.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();*/
			break;
			
		case R.id.createProfile_lay:
			imm.hideSoftInputFromWindow(createProfile_lay.getWindowToken(), 0);
			break;

		default:
			break;
		}
	}
	
	

}
