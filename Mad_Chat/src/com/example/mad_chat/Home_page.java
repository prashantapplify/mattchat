package com.example.mad_chat;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chatapp.model.Get_ContactList_Adapter;
import com.chatapp.model.Get_Convercation_Adapter;
import com.chatapp.model.Get_convercation_model;
import com.chatapp.service.Constant_URL;
import com.chatapp.service.Get_all_registered_user_through_contacts;
import com.chatapp.service.Get_all_user_conversations_list;
import com.google.android.gcm.GCMRegistrar;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class Home_page extends FragmentActivity implements OnClickListener{

	public static ArrayList<Get_convercation_model> Contact_List_arrayList1=new ArrayList<Get_convercation_model>();
		//	private TabHostProvider tabProvider;
//	private TabView tabView;
	LinearLayout top_header_lay;
	int header_click=1;
	TextView homepage_edit,homepage_all,homepage_groups,homepage_compose;
	ImageView homepage_newChat;
	LinearLayout group_lay,all_lay;
	ImageView bottom_chat,bottom_contect,bottom_more;
	View bottom_chat_line,bottom_chat_line_selected,bottom_contact_line,bottom_contact_line_selected,bottom_more_line,bottom_more_line_selected;
	static Context ctx;
	static SharedPreferences sp;
	static int h;
	static int w;
	static ListView Convercation_List;
	ProgressDialog bar;
	
	
	int click_edit=0;
	
	 private ResideMenu resideMenu;
	    private Home_page mContext;
	    private ResideMenuItem itemHome;
	    private ResideMenuItem itemProfile;
	    private ResideMenuItem itemCalendar;
	    private ResideMenuItem itemSettings;
	    
	    AsyncTask<Void, Void, Void> mRegisterTask;
	    public static String name;
		public static String email;
		
		 List<String> phone_name=new ArrayList<String>();
		    List<String> phone_number=new ArrayList<String>();
		    public static List<String> exist_phone=new ArrayList<String>();
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
////		tabProvider = new TabbarView(this);  
////		tabView = tabProvider.getTabHost("main");
////		tabView.setCurrentView(R.layout.home_page);  
////		setContentView(tabView.render(0));
//		setContentView(R.layout.home_page);
		
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.home_page);
			
			ctx=Home_page.this;
			sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
			Display display = getWindowManager().getDefaultDisplay();
			w=display.getWidth();
			h=display.getHeight();
			
			SharedPreferences.Editor editor=sp.edit();
			editor.putString("Conversation_Delete","0");
			editor.commit();
			
			Typeface font = Typeface.createFromAsset(getAssets(), "helveticaltstd_bold.otf");
			Typeface font2 = Typeface.createFromAsset(getAssets(), "helveticaltstd_roman.otf");
			Typeface font3 = Typeface.createFromAsset(getAssets(), "helveticaltstd_light.otf");
		  
//		top_header_lay=(LinearLayout)findViewById(R.id.top_header_lay);
//		top_header_lay.setOnClickListener(this);
		homepage_edit=(TextView)findViewById(R.id.homepage_edit);
		homepage_edit.setOnClickListener(this);
		homepage_newChat=(ImageView)findViewById(R.id.homepage_newChat);
		homepage_newChat.setOnClickListener(this);
//		homepage_all=(TextView)findViewById(R.id.homepage_all);
//		homepage_all.setOnClickListener(this);
//		homepage_all.setTypeface(font3);
//		homepage_groups=(TextView)findViewById(R.id.homepage_groups);
//		homepage_groups.setOnClickListener(this);
//		homepage_groups.setTypeface(font3);
		homepage_compose=(TextView)findViewById(R.id.homepage_compose);
		homepage_compose.setOnClickListener(this);
		homepage_compose.setTypeface(font3);
		all_lay=(LinearLayout)findViewById(R.id.all_lay);
		group_lay=(LinearLayout)findViewById(R.id.group_lay);
		
		
		bottom_chat=(ImageView)findViewById(R.id.bottom_chat);
		bottom_chat.setBackgroundResource(R.drawable.chat_icon_select);
		bottom_chat.setOnClickListener(this);
		
		bottom_contect=(ImageView)findViewById(R.id.bottom_contect);
		bottom_contect.setBackgroundResource(R.drawable.contact_icon);
		bottom_contect.setOnClickListener(this);
		  
		bottom_more=(ImageView)findViewById(R.id.bottom_more);
		bottom_more.setBackgroundResource(R.drawable.more_icon);
		bottom_more.setOnClickListener(this);
		Convercation_List=(ListView)findViewById(R.id.Convercation_List);
		
		/*bottom_chat_line=(View)findViewById(R.id.bottom_chat_line);
		bottom_chat_line.setVisibility(View.GONE);
		bottom_chat_line_selected=(View)findViewById(R.id.bottom_chat_line_selected);
		bottom_chat_line_selected.setVisibility(View.VISIBLE);
		bottom_contact_line=(View)findViewById(R.id.bottom_contact_line);
		bottom_contact_line.setVisibility(View.VISIBLE);
		bottom_contact_line_selected=(View)findViewById(R.id.bottom_contact_line_selected);
		bottom_contact_line_selected.setVisibility(View.GONE);
		bottom_more_line=(View)findViewById(R.id.bottom_more_line);
		bottom_more_line.setVisibility(View.VISIBLE);
		bottom_more_line_selected=(View)findViewById(R.id.bottom_more_line_selected);
		bottom_more_line_selected.setVisibility(View.GONE);*/
		GetCountryZipCode();
		
//		if(sp.getString("back_on_home", "").equalsIgnoreCase("1") || sp.getString("back_on_home", "")==null){
			SharedPreferences.Editor editor1=sp.edit();
			editor1.putString("back_on_home","0");
			editor1.commit();
			int i=0;
		phone_name=new ArrayList<String>();
		phone_number=new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		Cursor cursor = getContentResolver().query(Phone.CONTENT_URI, null, null, null,"UPPER(" + ContactsContract.Contacts.DISPLAY_NAME + ") ASC");
//		Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,
//				   ContactsContract.Contacts.HAS_PHONE_NUMBER + " = 1", 
//				   null, 
//				   "UPPER(" + ContactsContract.Contacts.DISPLAY_NAME + ") ASC");
		while (cursor.moveToNext())
		{
			i++;
			String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String Number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			
			Log.d("name", name+"//"+Number);
			 name = name.replaceAll("[^\\x00-\\x7F]", "");
//			Cursor cursor = getContentResolver.query(phones.CONTENT_URI, null, null, null, Phone.DISPLAY_NAME + " ASC");
			
			String str=Number.replace("(", "");
			String str1=str.replace(")", "");
			String str2=str1.replace("-", "");
			String str3=str2.replace(" ", "");
			String str4=str3.replace(",", "");
//			String str5=str4.replace("+91", "");
			String str6=str4.replace("*", "");
			String str9=str6.replace("#", "");
			String str8 = str9.replaceAll("[^\\d-+.]", "");
//			String str8=str7.replace("+", "").trim();
//			phone_number.add(str8);
//			phone_name.add(name);
//			if(str8.length()>9){
			Log.d("1", cursor.getCount()+"//"+i);
				if(str8.length()>0 && name.length()>0){


					if(cursor.getCount()==i){
						Log.d("1", "1");
						sb.append(name);
						sb1.append(str8);
						phone_number.add(str8);
						phone_name.add(name);
					}else{
						Log.d("2", "2");
						sb.append(name+"$#$");
						sb1.append(str8+",");
						phone_number.add(str8);
						phone_name.add(name);
					}

				}
				else if (str8.length()>0 && name.length()==0){


					if(cursor.getCount()==i){
						if (str8.length()>0 && name.length()==0){
							Log.d("3", "3");
							sb.append("no name"+"$#$");
							sb1.append(str8+",");
							phone_number.add(str8);
							phone_name.add("no name");
						}else{
							Log.d("4", "4");
							sb.append(name);
							sb1.append(str8);
							phone_number.add(str8);
							phone_name.add(name);
						}
					}else{
						Log.d("5", "5");
						sb.append("no name"+"$#$");
						sb1.append(str8+",");
						phone_number.add(str8);
						phone_name.add("no name");
					}

				}
				else {


					if(name.equalsIgnoreCase("")||name==null ){
						if(cursor.getCount()==i){
							Log.d("6", "6");
							sb.append(name);
							sb1.append(str8);
							phone_number.add(str8);
							phone_name.add(name);
						}else{
							Log.d("7", "7");
							sb.append("no name"+"$#$");
							sb1.append(str8+",");
							phone_number.add(str8);
							phone_name.add("no name");
						}
					}else{
						if(cursor.getCount()==i){
							Log.d("8", "8");
							sb.append(name);
							sb1.append(str8);
							phone_number.add(str8);
							phone_name.add(name);
						}else{
							Log.d("9", "9");
							sb.append(name+"$#$");
							sb1.append(str8+",");
							phone_number.add(str8);
							phone_name.add(name);
						}
					}

				}
				
		}
		String sel_cat = sb+"";
		String number_string=sb1+"";
		
		
		Log.i("PhoneContact", "number: "+number_string);
		Log.i("PhoneContact", "name: "+sel_cat);
//		sel_cat=sel_cat+"noName";
//		number_string=number_string+"000";
		if(cursor.getCount()>0){
			 new Get_all_registered_user_through_contacts(ctx, bar, sp.getString("accesstoken", ""),sp,Convercation_List,h,w,sel_cat,number_string,"Contact").execute("main");
		        
		}
//		}
		 mContext = this;
		 setUpMenu();
		 PushRegistration();
	       
//	        changeFragment(new Home_page());
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bottom_chat:
			
			break;
			
		case R.id.bottom_contect:
			Intent mainIntent1 = new Intent(Home_page.this,Contacts.class);
			startActivity(mainIntent1); 
			overridePendingTransition(0, 0);
			finish();
			break;
			
		case R.id.bottom_more:
			 
			break;	
			
		/*case R.id.homepage_all:
			if(header_click==0){
				header_click=1;
				all_lay.setVisibility(View.VISIBLE);
				group_lay.setVisibility(View.GONE);
				homepage_all.setTextColor(Color.parseColor("#004a80"));
				homepage_groups.setTextColor(Color.parseColor("#FFFFFF"));
				top_header_lay.setBackgroundResource(R.drawable.select1);
			}
			
			
			break;*/
			
		case R.id.homepage_newChat:
			
			Intent mainIntent = new Intent(Home_page.this,New_contacts.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;
			
		case R.id.homepage_edit:
			if(click_edit==0){
				click_edit=1;
				SharedPreferences.Editor editor=sp.edit();
			editor.putString("Conversation_Delete","1");
			editor.commit();
			Convercation_List.setAdapter(new Get_Convercation_Adapter(ctx, Home_page.Contact_List_arrayList1,w,h,sp));
			}
			else{
				click_edit=0;
				SharedPreferences.Editor editor=sp.edit();
				editor.putString("Conversation_Delete","0");
				editor.commit();
				Convercation_List.setAdapter(new Get_Convercation_Adapter(ctx, Home_page.Contact_List_arrayList1,w,h,sp));
				
			}
			break;
			
		/*case R.id.homepage_groups:
			if(header_click==1){
				header_click=0;
				all_lay.setVisibility(View.GONE);
				group_lay.setVisibility(View.VISIBLE);
				homepage_all.setTextColor(Color.parseColor("#FFFFFF"));
				homepage_groups.setTextColor(Color.parseColor("#004a80"));
				top_header_lay.setBackgroundResource(R.drawable.select2);
			}
			
			break;*/
			
		case R.id.homepage_compose:  
			Intent mainIntent2 = new Intent(Home_page.this,New_contacts.class);
			startActivity(mainIntent2); 
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;

		default:
			break;
		}
	}
	String GetCountryZipCode(){

		String CountryID="";
		String CountryZipCode="";

		TelephonyManager manager = (TelephonyManager) this.getSystemService(Home_page.this.TELEPHONY_SERVICE);
		//getNetworkCountryIso
		CountryID= manager.getSimCountryIso().toUpperCase();
		String[] rl=this.getResources().getStringArray(R.array.CountryCodes);
		for(int i=0;i<rl.length;i++){
			String[] g=rl[i].split(",");
			if(g[1].trim().equals(CountryID.trim())){
				CountryZipCode=g[0];
				
				
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("country_code", "+"+CountryZipCode);
				editer4.commit();
				
//				Toast.makeText(Phone_number_enter.this, CountryZipCode, 
//						Toast.LENGTH_LONG).show();
				break;  
			}
		}
		return CountryZipCode;

	}
	private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
//        resideMenu.setBackground(R.drawable.search_box_bg);
       
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.5f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.help_icon,     "");
        itemProfile  = new ResideMenuItem(this, R.drawable.account_icon,  "");
        itemCalendar = new ResideMenuItem(this, R.drawable.chatsetting_icon, "");
        itemSettings = new ResideMenuItem(this, R.drawable.notification_icon, "");

        itemHome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("Home_page", "Home");
				editer4.commit();
				Intent mainIntent2 = new Intent(Home_page.this,Contact_us.class);
				startActivity(mainIntent2); 
//				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				overridePendingTransition(0, 0);
//				finish();
			}
		});
        itemProfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("Home_page", "Home");
				editer4.commit();
				Intent mainIntent2 = new Intent(Home_page.this,Account.class);
				startActivity(mainIntent2); 
//				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				overridePendingTransition(0, 0);
//				finish();
			}
		});
        itemCalendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("Home_page", "Home");
				editer4.commit();
				Intent mainIntent2 = new Intent(Home_page.this,Chat_Setting.class);
				startActivity(mainIntent2); 
//				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				overridePendingTransition(0, 0);
//				finish();
			}
		});
        itemSettings.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("Home_page", "Home");
				editer4.commit();
				Intent mainIntent2 = new Intent(Home_page.this,Notifications.class);
				startActivity(mainIntent2); 
//				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				overridePendingTransition(0, 0);
//				finish();
			}
		});

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_RIGHT);

        // You can disable a direction by setting ->
         resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
         resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        /*findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });*/
        findViewById(R.id.bottom_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }
//	 @Override
//	    public boolean dispatchTouchEvent(MotionEvent ev) {
//	        return resideMenu.dispatchTouchEvent(ev);
//	    }
	 private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
	        @Override
	        public void openMenu() {  
	        	try {
	        		bottom_chat.setBackgroundResource(R.drawable.chat_icon);
	        		bottom_contect.setBackgroundResource(R.drawable.contact_icon);
	        		bottom_more.setBackgroundResource(R.drawable.more_icon_select);
	        		 resideMenu.setBackgroundColor(Color.parseColor("#6EB4FD"));
	        		/*bottom_chat_line.setVisibility(View.VISIBLE);
	        		bottom_chat_line_selected.setVisibility(View.GONE);
	        		bottom_contact_line.setVisibility(View.VISIBLE);
	        		bottom_contact_line_selected.setVisibility(View.GONE);
	        		bottom_more_line.setVisibility(View.GONE);
	        		bottom_more_line_selected.setVisibility(View.VISIBLE);*/
//	 	            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					// TODO: handle exception
				}
	        	
	        }

	        @Override
	        public void closeMenu() {
	        	bottom_chat.setBackgroundResource(R.drawable.chat_icon_select);
        		bottom_contect.setBackgroundResource(R.drawable.contact_icon);
        		bottom_more.setBackgroundResource(R.drawable.more_icon);
        		 resideMenu.setBackgroundColor(Color.parseColor("#ffffff"));
        		/*bottom_chat_line.setVisibility(View.GONE);
        		bottom_chat_line_selected.setVisibility(View.VISIBLE);
        		bottom_contact_line.setVisibility(View.VISIBLE);
        		bottom_contact_line_selected.setVisibility(View.GONE);
        		bottom_more_line.setVisibility(View.VISIBLE);
        		bottom_more_line_selected.setVisibility(View.GONE);*/
//	            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
	        }
	    };
	   /* private void changeFragment(Fragment targetFragment){
	        resideMenu.clearIgnoredViewList();
	        getSupportFragmentManager()
	                .beginTransaction()
	                .replace(R.id.main_fragment, targetFragment, "fragment")
	                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
	                .commit();
	    }*/

	    // What good method is to access resideMenu？
	    public ResideMenu getResideMenu(){
	    	
	        return resideMenu;
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
			final String regId = GCMRegistrar.getRegistrationId(this);
			//		Toast.makeText(Dash_Board.this, "regId 1= "+regId, Toast.LENGTH_LONG).show();
			// Check if regid already presents
			if (regId.equals("")) {
				// Registration is not present, register now with GCM			
				GCMRegistrar.register(this, CommonUtilities.SENDER_ID);
				
				
			} else {
				if(Constant_URL.isNetworkAvailable(Home_page.this))
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
					final Context context = Home_page.this;
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
					Toast.makeText(Home_page.this, "Network not available.", 
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
					
//					Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
					
					// Releasing wake lock
					WakeLocker.release();
				}
			};
			
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		SharedPreferences.Editor editor=sp.edit();
		editor.putString("back_on_home","1");
		editor.commit();
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
		finish();
	}
			/*@Override
			public boolean onKeyDown(int keyCode, KeyEvent event)  {
				if(keyCode == KeyEvent.KEYCODE_BACK)
			    {
			        Log.d("Test", "Back button pressed!");
			    }
			    else if(keyCode == KeyEvent.KEYCODE_HOME)
			    {
			        Log.d("Test", "Home button pressed!");
			    }
			    return super.onKeyDown(keyCode, event);

			    
			}*/
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new Get_all_user_conversations_list(ctx, bar,sp.getString("accesstoken",""),sp,h,w,Convercation_List,all_lay,group_lay).execute("main");
		
	}
	
	

}
