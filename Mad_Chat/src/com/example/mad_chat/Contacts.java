package com.example.mad_chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.xml.sax.Parser;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chatapp.model.AlphabetListAdapter;
import com.chatapp.model.AlphabetListAdapter.Item;
import com.chatapp.model.AlphabetListAdapter.Row;
import com.chatapp.model.AlphabetListAdapter.Section;
import com.chatapp.model.Get_ConteactList_Model;
import com.chatapp.model.Get_phone_ContactList_Model;
import com.chatapp.service.Get_Phone_ContactList;
import com.chatapp.service.Get_all_registered_user_through_contacts;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

@SuppressLint("DefaultLocale")
public class Contacts extends ListActivity implements OnClickListener{

	
	TextView contacts_headerText;
	ImageView bottom_chat,bottom_contect,bottom_more,contact_add;
	FrameLayout main_fragment1;
	View bottom_chat_line,bottom_chat_line_selected,bottom_contact_line,bottom_contact_line_selected,bottom_more_line,bottom_more_line_selected;

	Context ctx;
	ProgressDialog bar;
	SharedPreferences sp;
	public static ArrayList<Get_ConteactList_Model> Contact_List_arrayList1=new ArrayList<Get_ConteactList_Model>();

	public static ArrayList<Get_phone_ContactList_Model> Contact_List_arrayList=new ArrayList<Get_phone_ContactList_Model>();
	public static ArrayList<Get_phone_ContactList_Model> Temp_Contact_List_arrayList=new ArrayList<Get_phone_ContactList_Model>();

	ImageView contact_search;
	LinearLayout contacts_search_lay;
	
	Get_Phone_ContactList ContactList_adp;
	ListView Contacts_list;
	int h,w;
	int clickserach=0;
	InputMethodManager imm;
	LinearLayout contacts_cancel;
	EditText txtSearchCity;
	int index = 0;
	private ResideMenu resideMenu;
	private Contacts mContext;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemProfile;
	private ResideMenuItem itemCalendar;
	private ResideMenuItem itemSettings; 
	
	 private AlphabetListAdapter adapter = new AlphabetListAdapter();
	    private AlphabetListAdapter adapter1 = new AlphabetListAdapter();
	    private GestureDetector mGestureDetector;
	    private List<Object[]> alphabet = new ArrayList<Object[]>();
	    private HashMap<String, Integer> sections = new HashMap<String, Integer>();
	    private int sideIndexHeight;
	    private static float sideIndexX;
	    private static float sideIndexY;
	    private int indexListSize;
	    List<String> phone_name=new ArrayList<String>();
	    List<String> phone_number=new ArrayList<String>();
	    List<String> Temp_phone_name=new ArrayList<String>();
	    
	    List<String> Temp_phone_number=new ArrayList<String>();
	    
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//		getWindow().setSoftInputMode(
		//				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		//		tabProvider = new TabbarView(this);  
		//		tabView = tabProvider.getTabHost("main");
		//		tabView.setCurrentView(R.layout.contacts);  
		//		setContentView(tabView.render(1));
		setContentView(R.layout.contacts);

		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		ctx=Contacts.this;
		Display display = getWindowManager().getDefaultDisplay();
		w=display.getWidth();
		h=display.getHeight();
		

		contacts_headerText=(TextView)findViewById(R.id.contacts_headerText);

		bottom_chat=(ImageView)findViewById(R.id.bottom_chat);
		bottom_chat.setBackgroundResource(R.drawable.chat_icon);
		bottom_chat.setOnClickListener(this);



		bottom_contect=(ImageView)findViewById(R.id.bottom_contect);
		bottom_contect.setBackgroundResource(R.drawable.contact_icon_select);
		bottom_contect.setOnClickListener(this);

		bottom_more=(ImageView)findViewById(R.id.bottom_more);
		bottom_more.setBackgroundResource(R.drawable.more_icon);
		bottom_more.setOnClickListener(this);

//		Contacts_list=(ListView)findViewById(R.id.Contacts_list);

		contact_search=(ImageView)findViewById(R.id.contact_search);
		contact_search.setOnClickListener(this);

		contacts_search_lay=(LinearLayout)findViewById(R.id.contacts_search_lay);
		contacts_search_lay.setVisibility(View.GONE);

		contact_add=(ImageView)findViewById(R.id.contact_add);
		contact_add.setOnClickListener(this);

		contacts_cancel=(LinearLayout)findViewById(R.id.contacts_cancel);
		contacts_cancel.setOnClickListener(this);

		
		int i=0;
		Contacts.Contact_List_arrayList=new ArrayList<Get_phone_ContactList_Model>();
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
			String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String Number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			
			Log.d("name", name+"//"+Number);

//			Cursor cursor = getContentResolver.query(phones.CONTENT_URI, null, null, null, Phone.DISPLAY_NAME + " ASC");
			
			String str=Number.replace("(", "");
			String str1=str.replace(")", "");
			String str2=str1.replace("-", "");
			String str3=str2.replace(" ", "");
			String str4=str3.replace(",", "");
			String str5=str4.replace("+91", "");
			String str6=str5.replace("*", "");
			String str7=str6.replace("#", "");
			String str8=str7.replace("+", "").trim();
//			phone_number.add(str8);
//			phone_name.add(name);
//			if(str8.length()>9){
				if(str8.length()>0 && name.length()>0){


					if(cursor.getCount()==i){
						sb.append(name);
						sb1.append(str8);
						phone_number.add(str8);
						phone_name.add(name);
					}else{
						sb.append(name+"$#$");
						sb1.append(str8+",");
						phone_number.add(str8);
						phone_name.add(name);
					}

				}
				else if (str8.length()>0 && name.length()==0){


					if(cursor.getCount()==i){
						if (str8.length()>0 && name.length()==0){
							sb.append("no name"+"$#$");
							sb1.append(str8+",");
							phone_number.add(str8);
							phone_name.add("no name");
						}else{

							sb.append(name);
							sb1.append(str8);
							phone_number.add(str8);
							phone_name.add(name);
						}
					}else{
						sb.append("no name"+"$#$");
						sb1.append(str8+",");
						phone_number.add(str8);
						phone_name.add("no name");
					}

				}
				else {


					if(name.equalsIgnoreCase("")||name==null ){
						if(cursor.getCount()==i){
							sb.append(name);
							sb1.append(str8);
							phone_number.add(str8);
							phone_name.add(name);
						}else{
							sb.append("no name"+"$#$");
							sb1.append(str8+",");
							phone_number.add(str8);
							phone_name.add("no name");
						}
					}else{
						if(cursor.getCount()==i){
							sb.append(name);
							sb1.append(str8);
							phone_number.add(str8);
							phone_name.add(name);
						}else{
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
		
		/*if(cursor.getCount()>0){
			 new Get_all_registered_user_through_contacts(ctx, bar, sp.getString("accesstoken", ""),sp,Contacts_list,h,w,sel_cat,number_string,"Contact").execute("main");
		        
		}*/
		
	        List<Row> rows = new ArrayList<Row>();
	        List<Row> rows1 = new ArrayList<Row>();
	        int start = 0;
	        int end = 0;
	        int ii=0;
	        String previousLetter = null;
	        Object[] tmpIndexItem = null;
	        Pattern numberPattern = Pattern.compile("[0-9]");

	        for (String country : phone_name) {
	        	String Number=phone_number.get(ii);
	        	ii++;
	            String firstLetter = country.substring(0, 1);

	            // Group numbers together in the scroller
	            if (numberPattern.matcher(firstLetter).matches()) {
	                firstLetter = "#";
	            }

	            // If we've changed to a new letter, add the previous letter to the alphabet scroller
	            if (previousLetter != null && !firstLetter.equalsIgnoreCase(previousLetter)) {
	                end = rows.size() - 1;
	                tmpIndexItem = new Object[3];
	                tmpIndexItem[0] = previousLetter.toUpperCase(Locale.UK);
	                tmpIndexItem[1] = start;
	                tmpIndexItem[2] = end;
	                alphabet.add(tmpIndexItem);

	                start = end + 1;
	            }

	            // Check if we need to add a header row
	            if (!firstLetter.equalsIgnoreCase(previousLetter)) {
	            	
	                rows.add(new Section(firstLetter));
	                sections.put(firstLetter, start);
	            	
	            }  
	            String str2="0";
	            // Add the country to the list
	            
	            for(int j=0;j<Home_page.exist_phone.size();j++){
	            	Log.d("contact List", (j+1) +"//"+Home_page.exist_phone.size()+"//"+str2);
	            	if(Number.length()>9){
	            	 String str=Number.substring(Number.length()-10, Number.length());
	            	 String str1=Home_page.exist_phone.get(j).substring(Home_page.exist_phone.get(j).length()-10, Home_page.exist_phone.get(j).length());

	            	 Log.d("++++++", str+"//"+str1);
	            	 if(str.equalsIgnoreCase(str1)){
	            		 rows.add(new Item(country,Number,"1",ctx));
	                	Log.d("match", "match");
	                	str2="1";
	                	break;
	                }
	                if(j+1==Home_page.exist_phone.size()){
	                	if(!str2.equalsIgnoreCase("1")){
	                	 rows.add(new Item(country,Number,"0",ctx));
	                	 Log.d("not match", "not match");
	                	}
	                }
	            }else if(Number.length()<9){
	            	 rows.add(new Item(country,Number,"0",ctx));
	            	 break;
	            }
	            }

	           
	           
	            previousLetter = firstLetter;
	        }

	        if (previousLetter != null) {
	            // Save the last letter
	            tmpIndexItem = new Object[3];
	            tmpIndexItem[0] = previousLetter.toUpperCase(Locale.UK);
	            tmpIndexItem[1] = start;
	            tmpIndexItem[2] = rows.size() - 1;
	            alphabet.add(tmpIndexItem);
	        }

	        adapter.setRows(rows);
	        setListAdapter(adapter);

	        updateList();

		
		mContext = this;
		setUpMenu();
		
		cursor.close();
		txtSearchCity = (EditText) findViewById(R.id.contact_serach);
		txtSearchCity.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				/*Contacts.Temp_Contact_List_arrayList=new ArrayList<Get_phone_ContactList_Model>();

				for (int i = 0; i < Contacts.Contact_List_arrayList.size(); i++) {
					if (Contacts.Contact_List_arrayList
							.get(i).getName()
							.toLowerCase()
							.contains(
									txtSearchCity.getText().toString()
									.toLowerCase())) {
						//	                    	Contacts_list.setVisibility(View.VISIBLE);
						if(txtSearchCity.getText().toString().length()>0){
							Get_phone_ContactList_Model contact_list_model=new Get_phone_ContactList_Model();

							contact_list_model.setNumber(Contacts.Contact_List_arrayList
									.get(i).getNumber());
							contact_list_model.setName(Contacts.Contact_List_arrayList
									.get(i).getName());
							Contacts.Temp_Contact_List_arrayList.add(contact_list_model);
							//	             			mItems.add(object2.getString("phone"));

							ContactList_adp=new Get_Phone_ContactList(ctx, Contacts.Temp_Contact_List_arrayList,w,h,sp,"",imm);
						}
						else if(txtSearchCity.getText().toString().length()==0){
							ContactList_adp=new Get_Phone_ContactList(ctx, Contacts.Contact_List_arrayList,w,h,sp,"",imm);

						}
					}
					else{

						//	                    	Contacts_list.setVisibility(View.GONE);


					}
				}*/

//				Contacts_list.setAdapter(ContactList_adp);
				 Temp_phone_name=new ArrayList<String>();

					for (int i = 0; i < phone_name.size(); i++) {
						if (phone_name
								.get(i)
								.toLowerCase()
								.contains(
										txtSearchCity.getText().toString()
										.toLowerCase())) {
							//	                    	Contacts_list.setVisibility(View.VISIBLE);
							if(txtSearchCity.getText().toString().length()>0){
								
							Log.d("text", phone_name.get(i));
							Temp_phone_name.add( phone_name.get(i));
							Temp_phone_number.add(phone_number.get(i));
//							Collections.sort(Temp_phone_name);
//							Collections.sort(Temp_phone_number);
							 List<Row> rows = new ArrayList<Row>();
							 int start1 = 0;
						        int end1 = 0;
						        int jj=0;
						        String previousLetter = null;
						        Object[] tmpIndexItem = null;
						        Pattern numberPattern = Pattern.compile("[0-9]");

						        for (String country : Temp_phone_name) {
						        	String Number=Temp_phone_number.get(jj);
						        	jj++;
						            String firstLetter = country.substring(0, 1);

						            // Group numbers together in the scroller
						            if (numberPattern.matcher(firstLetter).matches()) {
						                firstLetter = "#";
						            }

						            // If we've changed to a new letter, add the previous letter to the alphabet scroller
						            if (previousLetter != null && !firstLetter.equalsIgnoreCase(previousLetter)) {
						                end1 = rows.size() - 1;
						                tmpIndexItem = new Object[3];
						                tmpIndexItem[0] = previousLetter.toUpperCase(Locale.UK);
						                tmpIndexItem[1] = start1;
						                tmpIndexItem[2] = end1;
						                alphabet.add(tmpIndexItem);

						                start1 = end1 + 1;
						            }

						            // Check if we need to add a header row
						            if (!firstLetter.equalsIgnoreCase(previousLetter)) {
						            	
						                rows.add(new Section(firstLetter));
						                sections.put(firstLetter, start1);
						            }
						            String str2="0";
						            // Add the country to the list
						            
						            for(int j=1;j<Home_page.exist_phone.size();j++){
						            	Log.d("contact List", (j+1) +"//"+Home_page.exist_phone.size()+"//"+str2);
						            	if(Number.length()>9){
						            	 String str=Number.substring(Number.length()-10, Number.length());
						            	 String str1=Home_page.exist_phone.get(j).substring(Home_page.exist_phone.get(j).length()-10, Home_page.exist_phone.get(j).length());

						            	 Log.d("++++++", str+"//"+str1);
						            	 if(str.equalsIgnoreCase(str1)){
						            		 rows.add(new Item(country,Number,"1",ctx));
						                	Log.d("match", "match");
						                	str2="1";
						                	break;
						                }
						                if(j+1==Home_page.exist_phone.size()){
						                	if(!str2.equalsIgnoreCase("1")){
						                	 rows.add(new Item(country,Number,"0",ctx));
						                	Log.d("not match", "not match");
						                	}
						                }
						            }else if(Number.length()<9){
						            	 rows.add(new Item(country,Number,"0",ctx));
						            	 break;
						            }
						            }
						            previousLetter = firstLetter;
						        }

						        if (previousLetter != null) {
						            // Save the last letter
						            tmpIndexItem = new Object[3];
						            tmpIndexItem[0] = previousLetter.toUpperCase(Locale.UK);
						            tmpIndexItem[1] = start1;
						            tmpIndexItem[2] = rows.size() - 1;
						            alphabet.add(tmpIndexItem);
						        }

						        adapter1.setRows(rows);
						        setListAdapter(adapter1);
						        
//						        updateList();
							}
							
						}
						else{

						}
						
					}
			}
		});

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bottom_chat:
			Intent mainIntent1 = new Intent(Contacts.this,Home_page.class);
			startActivity(mainIntent1); 
			overridePendingTransition(0, 0);
			finish();
			break;

		case R.id.bottom_contect:

			break;

		case R.id.bottom_more:
			
			break;	

		case R.id.main_fragment1:
			resideMenu.setMenuListener(menuListener);
			break;

		case R.id.contact_search:
			if(clickserach==0){
				clickserach=1;
				contacts_search_lay.setVisibility(View.VISIBLE);
			}else{
				clickserach=0;
				contacts_search_lay.setVisibility(View.GONE);
			}

			break;

		case R.id.contacts_cancel:
			if(txtSearchCity.getText().toString().trim().length()>0){
				txtSearchCity.setText("");
				imm.hideSoftInputFromWindow(txtSearchCity.getWindowToken(), 0);
			}else{
				if(clickserach==0){
					clickserach=1;
					contacts_search_lay.setVisibility(View.VISIBLE);
					
				}else{
					clickserach=0;
					contacts_search_lay.setVisibility(View.GONE);
					imm.hideSoftInputFromWindow(txtSearchCity.getWindowToken(), 0);
				}
			}
			break;

		case R.id.contact_add:
			Intent mainIntent = new Intent(Contacts.this,Add_Contact.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;

		default:
			break;
		}
	}
	 @Override
	    public boolean onTouchEvent(MotionEvent event) {
	        if (mGestureDetector.onTouchEvent(event)) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public void updateList() {
	        LinearLayout sideIndex = (LinearLayout) findViewById(R.id.sideIndex);
	        sideIndex.removeAllViews();
	        indexListSize = alphabet.size();
	        Log.d("alphabet", alphabet.size()+"");
	        if (indexListSize < 1) {
	            return;
	        }

	        int indexMaxSize = (int) Math.floor(sideIndex.getHeight() / 20);
	        int tmpIndexListSize = indexListSize;
	        while (tmpIndexListSize > indexMaxSize) {
	            tmpIndexListSize = tmpIndexListSize / 2;
	        }
	        double delta;
	        if (tmpIndexListSize > 0) {
	            delta = indexListSize / tmpIndexListSize;
	        } else {
	            delta = 1;
	        }

	        TextView tmpTV;
	        for (double i = 1; i <= indexListSize; i = i + delta) {
	            Object[] tmpIndexItem = alphabet.get((int) i - 1);
	            final String tmpLetter = tmpIndexItem[0].toString();

	            tmpTV = new TextView(this);
	            tmpTV.setText(tmpLetter);
	            tmpTV.setGravity(Gravity.CENTER);
	            tmpTV.setTextSize(12);
	            tmpTV.setTextColor(Color.parseColor("#4d97e3"));
	            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
	           
	            tmpTV.setLayoutParams(params);
	            sideIndex.addView(tmpTV);
	            tmpTV.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					
					
						/*Log.d("Text", tmpLetter);
						LayoutInflater inflater = getLayoutInflater();

						View layout = inflater.inflate(R.layout.toast,
	                               (ViewGroup) findViewById(R.id.toast_layout_root));
						TextView text = (TextView) layout.findViewById(R.id.text);
						text.setText(tmpLetter);
						Toast toast = new Toast(getApplicationContext());
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.setDuration(Toast.LENGTH_LONG);
						toast.setView(layout);
						toast.show();*/
						
					}
				});
	        }

	        
	        sideIndexHeight = sideIndex.getHeight();

	        sideIndex.setOnTouchListener(new OnTouchListener() {
	            @Override
	            public boolean onTouch(View v, MotionEvent event) {
	                // now you know coordinates of touch
	                sideIndexX = event.getX();
	                sideIndexY = event.getY();

	                // and can display a proper item it country list
	                displayListItem();

	                return false;
	            }
	        });
	    }

	    public void displayListItem() {
	        LinearLayout sideIndex = (LinearLayout) findViewById(R.id.sideIndex);
	        sideIndexHeight = sideIndex.getHeight();
	        // compute number of pixels for every side index item
	        double pixelPerIndexItem = (double) sideIndexHeight / indexListSize;

	        // compute the item index for given event position belongs to
	        int itemPosition = (int) (sideIndexY / pixelPerIndexItem);

	        // get the item (we can do it since we know item index)
	        if (itemPosition < alphabet.size()) {
	            Object[] indexItem = alphabet.get(itemPosition);
	            int subitemPosition = sections.get(indexItem[0]);
	            
	            Object[] tmpIndexItem = alphabet.get(itemPosition);
	            final String tmpLetter = tmpIndexItem[0].toString();
	            Log.d("Text", tmpLetter+"");
	            LayoutInflater inflater = getLayoutInflater();

				View layout = inflater.inflate(R.layout.toast,
                           (ViewGroup) findViewById(R.id.toast_layout_root));
				TextView text = (TextView) layout.findViewById(R.id.text);
				text.setText(tmpLetter);
				Toast toast = new Toast(getApplicationContext());
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setDuration(Toast.LENGTH_SHORT);
				toast.setView(layout);
				toast.show();
				
	            //ListView listView = (ListView) findViewById(android.R.id.list);
	            getListView().setSelection(subitemPosition);
	        }
	    }
	private void setUpMenu() {

		// attach to current activity;
		resideMenu = new ResideMenu(this);
		//        resideMenu.setBackground(R.drawable.search_box_bg);

		resideMenu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("+++++", "++++++");
				resideMenu.setMenuListener(menuListener);
			}
		});
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
				editer4.putString("Home_page", "Contacts");
				editer4.commit();
				Intent mainIntent2 = new Intent(Contacts.this,Contact_us.class);
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
				editer4.putString("Home_page", "Contacts");
				editer4.commit();
				Intent mainIntent2 = new Intent(Contacts.this,Account.class);
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
				editer4.putString("Home_page", "Contacts");
				editer4.commit();
				Intent mainIntent2 = new Intent(Contacts.this,Chat_Setting.class);
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
				editer4.putString("Home_page", "Contacts");
				editer4.commit();
				Intent mainIntent2 = new Intent(Contacts.this,Notifications.class);
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
	/* @Override
	    public boolean dispatchTouchEvent(MotionEvent ev) {
	        return resideMenu.dispatchTouchEvent(ev);
	    }*/
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
			bottom_chat.setBackgroundResource(R.drawable.chat_icon);
			bottom_contect.setBackgroundResource(R.drawable.contact_icon_select);
			bottom_more.setBackgroundResource(R.drawable.more_icon);
			resideMenu.setBackgroundColor(Color.parseColor("#ffffff"));
			/*bottom_chat_line.setVisibility(View.VISIBLE);
        		bottom_chat_line_selected.setVisibility(View.GONE);
        		bottom_contact_line.setVisibility(View.GONE);
        		bottom_contact_line_selected.setVisibility(View.VISIBLE);
        		bottom_more_line.setVisibility(View.VISIBLE);
        		bottom_more_line_selected.setVisibility(View.GONE);*/
			//	            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
		}
	};
	/*  private void changeFragment(Fragment targetFragment){
	        resideMenu.clearIgnoredViewList();
	        getSupportFragmentManager()
	                .beginTransaction()
	                .replace(R.id.main_fragment1, targetFragment, "fragment")
	                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
	                .commit();
	    }*/

	// What good method is to access resideMenu？
	public ResideMenu getResideMenu(){

		return resideMenu;
	}
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent mainIntent1 = new Intent(Contacts.this,Home_page.class);
		startActivity(mainIntent1); 
		overridePendingTransition(0, 0);
		finish();

	}

}
