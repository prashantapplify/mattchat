package com.example.mad_chat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chatapp.service.Constant_URL;
import com.chatapp.service.Get_login;
import com.google.android.gcm.GCMRegistrar;
  
public class Phone_number_enter extends Activity implements OnClickListener{

	TextView country_code;
	EditText phone_number;
	ImageView phone_number_back,phone_number_farword;
	TextView phoneNumberEnter_headerText,phoneNumberEnter_tagLine,createprofile_phoneText;
	SharedPreferences sp;
	Context ctx;
	ProgressDialog bar;
	private String android_id ; 
	InputMethodManager imm;
	LinearLayout phone_lay;
	 AsyncTask<Void, Void, Void> mRegisterTask;
	 String regId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setSoftInputMode(
//				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.phone_number_enter);

		Typeface font = Typeface.createFromAsset(getAssets(), "helveticaltstd_bold.otf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "helveticaltstd_roman.otf");
		Typeface font3 = Typeface.createFromAsset(getAssets(), "helveticaltstd_light.otf");
		
		android_id= Secure.getString(Phone_number_enter.this.getContentResolver(),
	            Secure.ANDROID_ID);
		
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		ctx=Phone_number_enter.this;
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		
		TelephonyManager telephonyManager = (TelephonyManager)Phone_number_enter.this.getSystemService(Phone_number_enter.this.TELEPHONY_SERVICE);
		telephonyManager.getSimCountryIso();
		/*Toast.makeText(Phone_number_enter.this, telephonyManager.getSimCountryIso(), 
				Toast.LENGTH_LONG).show();*/
		phone_number=(EditText)findViewById(R.id.phone_number);
		country_code=(TextView)findViewById(R.id.country_code);
		phone_number_back=(ImageView)findViewById(R.id.phone_number_back);
		phone_number_back.setOnClickListener(this);
		phone_number_farword=(ImageView)findViewById(R.id.phone_number_farword);
		phone_number_farword.setOnClickListener(this);
		phone_lay=(LinearLayout)findViewById(R.id.phone_lay);
		phone_lay.setOnClickListener(this);
		
		
		phoneNumberEnter_headerText=(TextView)findViewById(R.id.phoneNumberEnter_headerText);
		phoneNumberEnter_headerText.setTypeface(font2);
		phoneNumberEnter_tagLine=(TextView)findViewById(R.id.phoneNumberEnter_tagLine);
		phoneNumberEnter_headerText.setTypeface(font2);
		GetCountryZipCode();
		PushRegistration();

	}
	 protected void PushRegistration() {
			////////////Push Notificetion//////////////////////////
//	        name=sp.getString("username", "");
//	        email=sp.getString("username", "");
	    	
	        GCMRegistrar.checkDevice(this);
	        GCMRegistrar.checkManifest(this);
	        registerReceiver(mHandleMessageReceiver, new IntentFilter(
					CommonUtilities.DISPLAY_MESSAGE_ACTION));
	        // Get GCM registration id
			 regId = GCMRegistrar.getRegistrationId(this);
			//		Toast.makeText(Dash_Board.this, "regId 1= "+regId, Toast.LENGTH_LONG).show();
			// Check if regid already presents
			if (regId.equals("")) {
				// Registration is not present, register now with GCM			
				GCMRegistrar.register(this, CommonUtilities.SENDER_ID);
				
				
			} else {
				if(Constant_URL.isNetworkAvailable(Phone_number_enter.this))
				{
//				Toast.makeText(Home_page.this, "regId = "+regId, Toast.LENGTH_LONG).show();
				
//					new Register_push_divice(Dash_Board.this, pro,regId,sp.getString("username", ""), sp.getString("username", "")).execute("main");
				
				// Device is already registered on GCM
				if (GCMRegistrar.isRegisteredOnServer(this)) {
					// Skips registration.				
					//				Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
				} else {
					// Try to register again, but not in the UI thread.
					// It's also necessary to cancel the thread onDestroy(),
					// hence the use of AsyncTask instead of a raw thread.
					final Context context = Phone_number_enter.this;
					mRegisterTask = new AsyncTask<Void, Void, Void>() {

						@Override
						protected Void doInBackground(Void... params) {
							// Register on our server
							// On server creates a new user
							ServerUtilities.register(context, sp.getString("username", ""), sp.getString("username", ""), regId, regId);
							
							return null;
						}

						@Override
						protected void onPostExecute(Void result) {
							
							mRegisterTask = null;
						}

					};
					mRegisterTask.execute(null, null, null);
				}
			}
				else{
					Toast.makeText(Phone_number_enter.this, "Network not available.", 
							Toast.LENGTH_LONG).show();
				}
			
		}
	    }
	 private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				String newMessage = intent.getExtras().getString(CommonUtilities.EXTRA_MESSAGE);
				// Waking up mobile if it is sleeping
				WakeLocker.acquire(getApplicationContext());
				
				/**
				 * Take appropriate action on this message
				 * depending upon your app requirement
				 * For now i am just displaying it on the screen
				 * */
				
				// Showing received message
				
//				Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
				
				// Releasing wake lock
				WakeLocker.release();
			}
		};
	String GetCountryZipCode(){

		String CountryID="";
		String CountryZipCode="";

		TelephonyManager manager = (TelephonyManager) this.getSystemService(Phone_number_enter.this.TELEPHONY_SERVICE);
		//getNetworkCountryIso
		CountryID= manager.getSimCountryIso().toUpperCase();
		String[] rl=this.getResources().getStringArray(R.array.CountryCodes);
		for(int i=0;i<rl.length;i++){
			String[] g=rl[i].split(",");
			if(g[1].trim().equals(CountryID.trim())){
				CountryZipCode=g[0];
				country_code.setText("+"+CountryZipCode);
				
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("country_code", country_code.getText().toString().trim());
				editer4.commit();
				
//				Toast.makeText(Phone_number_enter.this, CountryZipCode, 
//						Toast.LENGTH_LONG).show();
				break;  
			}
		}
		return CountryZipCode;

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.phone_number_back:
			Intent mainIntent = new Intent(Phone_number_enter.this,Accept_and_continue.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;

		case R.id.phone_number_farword:
			if(phone_number.getText().toString().trim().length()>9){
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("country_code", country_code.getText().toString().trim());
				editer4.putString("PhoneNumber", country_code.getText().toString()+phone_number.getText().toString().trim());
				
				editer4.commit();
				
				new Get_login(ctx, bar, regId,country_code.getText().toString()+phone_number.getText().toString().trim(),sp).execute("main");
				
			
			}
			else{
				Toast.makeText(Phone_number_enter.this, "Please enter correct phone number.", 
						Toast.LENGTH_LONG).show();
			}
			break;
			
		case R.id.phone_lay:
			imm.hideSoftInputFromWindow(phone_lay.getWindowToken(), 0);
			break;

		default:
			break;
		}

	}
	 public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			Intent mainIntent = new Intent(Phone_number_enter.this,Accept_and_continue.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			
	    }
}
