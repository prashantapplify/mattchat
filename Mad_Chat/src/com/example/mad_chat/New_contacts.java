package com.example.mad_chat;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chatapp.model.Get_ContactList_Adapter;
import com.chatapp.service.Get_all_registered_user_through_contacts;

public class New_contacts extends Activity implements OnClickListener {

	ImageView newContacts_back,newContact_chat_img,newContact_group_img;
	TextView newContact_chat_text,newContact_group_text;
	ListView newContact_list;
	List<String> phone_name=new ArrayList<String>();
	Context ctx;
	SharedPreferences sp;
	int h,w;
	ProgressDialog bar;
	int click_list=0;
	LinearLayout hide_contactList;
	View view_contact;
	String[] remove_text={"a","A","b","B","c","C","d","D","e","E","f","F","g","G","i","I","j","J","k","K","h","H","m","M","n","N","o","O","p","P","q","Q","r","R","s","S","t","T","u","U","v","V","w","W","x","X","y","Y","z","Z"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_contacts);
		
		ctx=New_contacts.this;
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		Display display = getWindowManager().getDefaultDisplay();
		w=display.getWidth();
		h=display.getHeight();
		
		newContacts_back=(ImageView)findViewById(R.id.newContacts_back);
		newContacts_back.setOnClickListener(this);
		
		newContact_chat_img=(ImageView)findViewById(R.id.newContact_chat_img);
		newContact_chat_img.setOnClickListener(this);
		newContact_group_img=(ImageView)findViewById(R.id.newContact_group_img);
		newContact_group_img.setOnClickListener(this);
		newContact_chat_text=(TextView)findViewById(R.id.newContact_chat_text);
		newContact_chat_text.setOnClickListener(this);
		newContact_group_text=(TextView)findViewById(R.id.newContact_group_text);
		newContact_group_text.setOnClickListener(this);
		newContact_list=(ListView)findViewById(R.id.newContact_list);
		
		hide_contactList=(LinearLayout)findViewById(R.id.hide_contactList);
		hide_contactList.setOnClickListener(this);
		
		view_contact=(View)findViewById(R.id.view_contact);
		
		int i=0;
//		Contacts.Contact_List_arrayList=new ArrayList<Get_ConteactList_Model>();
		phone_name=new ArrayList<String>();
		@SuppressWarnings("rawtypes")
		ArrayList al = new ArrayList();
		@SuppressWarnings("rawtypes")
		ArrayList al1 = new ArrayList();
//		String[] arraryList={"7696089598","7696089598","978myNumbe"};
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		Cursor phones = getContentResolver().query(Phone.CONTENT_URI, null, null, null,"UPPER(" + ContactsContract.Contacts.DISPLAY_NAME + ") ASC");
		
//		Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
		while (phones.moveToNext())
		{
			i++;
		  String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)).trim();
		  String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			if(i==208){
				phoneNumber="12345prashant";
			}
//			String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//			String phoneNumber=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//			
//			Log.d("name", name+"//"+phoneNumber);
		  name = name.replaceAll("[^\\x00-\\x7F]", "");
//		  phoneNumber = phoneNumber.replaceAll("[^\\x00-\\x7F]", "");
			String str=phoneNumber.replace("(", "");
			String str1=str.replace(")", "");
			String str2=str1.replace("-", "");
			String str3=str2.replace(" ", "");
			String str4=str3.replace(",", "");
//			String str5=str4.replace("+91", "");
			String str6=str4.replace("*", "");
			String str9=str6.replace("#", "");  
//			String str9=str7.replace("+", "").trim();
			String str8="";
//			String str = "a12.334tyz.78x";
			str8 = str9.replaceAll("[^\\d.+]", "");
			/*for(int k=0;k<remove_text.length;k++){
				 str8=str9.replace(remove_text[k], "");
			}*/
		/*  String str=phoneNumber.replace("(", "");
		  String str1=str.replace(")", "");
		  String str2=str1.replace("-", "");
		  String str3=str2.replace(" ", "");
		  String str4=str3.replace(",", "");
//		  String str5=str4.replace("+", "");
		  String str6=str4.replace("*", "");
		  String str8=str6.replace("#", "").trim();*/
//		  String str7=arraryList[i];
		  
		  
		 /* try {
			  if(isNumeric( str7.toString())){
				  str8=str7+"";
			  }*/
//			  if(str8.length()>9){
				  if(str8.length()>0 && name.length()>0){
					 
					  Log.d("1", phones.getCount()+"//"+i);
					  if(phones.getCount()==i){
						  sb.append(name);
						  sb1.append(str8);
						  phone_name.add(name);
					  }else{
					  sb.append(name+"$#$");
					  sb1.append(str8+",");
					  phone_name.add(name);
					  }
					
				  }
				  else if (str8.length()>0 && name.length()==0){
					  
					  
					  if(phones.getCount()==i){
						  Log.d("2", "2");
						  if (str8.length()>0 && name.length()==0){
							  if(phones.getCount()==i){
								  sb.append(name);
								  sb1.append(str8);
								  phone_name.add(name);
							  }else{
							  sb.append("no name"+"$#$");
							  sb1.append(str8+",");
							  phone_name.add("no name");
							  }
							 
						  }else{
							 
							  sb.append(name);
							  sb1.append(str8);
							  phone_name.add(name);
						  }
					  }else{
						  Log.d("3", "3");
					  sb.append("no name"+"$#$");
					  sb1.append(str8+",");
					  phone_name.add("no name");
					  }
					 
				  }
				  else {
					 
					  
					  if(name.equalsIgnoreCase("")||name==null ){
						  Log.d("4", "4");
						  if(phones.getCount()==i){
							  sb.append(name);
							  sb1.append(str8);
							  phone_name.add(name);
						  }else{
						  sb.append("no name"+"$#$");
						  sb1.append(str8+",");
						  phone_name.add("no name");
						  }
					  }else{
						  Log.d("5", "5");
						  if(phones.getCount()==i){
							  sb.append(name);
							  sb1.append(str8);
							  phone_name.add(name);
						  }else{
					  sb.append(name+"$#$");
					  sb1.append(str8+",");
					  phone_name.add(name);
						  }
					  }
					  
				  }
//				  }
			
	/*	} catch (Exception e) {
			// TODO: handle exception
			Log.i("PhoneContact", "Exception: "+e);
		}*/

		  
		}
		
		String s1=",";
		String s2="$#$";
		String sel_cat = sb+"";
		String number_string=sb1+"";
//		sel_cat=removeLastChar(s1);
//		number_string=removeLastChar(s2);
//		sel_cat=sel_cat+"noName";
//		number_string=number_string+"000";
		
		Log.i("PhoneContact", "number: "+number_string);
		Log.i("PhoneContact", "name: "+sel_cat);
		
	       
	        if(phones.getCount()>0){
	        new Get_all_registered_user_through_contacts(ctx, bar, sp.getString("accesstoken", ""),sp,newContact_list,h,w,sel_cat,number_string,"NewContact").execute("main");
	        }
	        phones.close();
	        
	}
	private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
	 public static boolean isNumeric(String str)
	 {
	    return str.matches("-?\\d+(.\\d+)?");
	  }
	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.newContacts_back:
			Intent mainIntent = new Intent(New_contacts.this,Home_page.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;
			
		case R.id.newContact_group_img:
			Intent mainIntent1 = new Intent(New_contacts.this,New_Group.class);
			startActivity(mainIntent1); 
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
//			finish();
			break;
			
		case R.id.newContact_group_text:
			Intent mainIntent2 = new Intent(New_contacts.this,New_Group.class);
			startActivity(mainIntent2); 
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
//			finish();
			break;
			
		case R.id.newContact_chat_img:
			if(click_list==0){
				click_list=1;
				hide_contactList.setVisibility(View.VISIBLE);
				view_contact.setVisibility(View.GONE);
				
				Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.contact_anim);
			    animation.setDuration(500);
			    hide_contactList.setAnimation(animation);
			    hide_contactList.animate();
			    animation.start();
				
			}else{
				click_list=0;
				hide_contactList.setVisibility(View.GONE);
				view_contact.setVisibility(View.VISIBLE);
				Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.contact_anim_close);
			    animation.setDuration(500);
			    hide_contactList.setAnimation(animation);
			    hide_contactList.animate();
			    animation.start();
			}
			
			break;
			
		case R.id.newContact_chat_text:
			if(click_list==0){
				click_list=1;
				hide_contactList.setVisibility(View.VISIBLE);
				view_contact.setVisibility(View.GONE);
				Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.contact_anim);
			    animation.setDuration(500);
			    hide_contactList.setAnimation(animation);
			    hide_contactList.animate();
			    animation.start();
			}else{
				click_list=0;
				hide_contactList.setVisibility(View.GONE);
				view_contact.setVisibility(View.VISIBLE);
				Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.contact_anim_close);
			    animation.setDuration(500);
			    hide_contactList.setAnimation(animation);
			    hide_contactList.animate();
			    animation.start();
			}
			break;
			
		

		default:
			break;
		}
	}
	 public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			Intent mainIntent = new Intent(New_contacts.this,Home_page.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			
	    }
	

}
