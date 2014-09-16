package com.chatapp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.chatapp.model.Get_ContactList_Adapter;
import com.chatapp.model.Get_ConteactList_Model;
import com.example.mad_chat.Contacts;
import com.example.mad_chat.Home_page;

public class Get_all_registered_user_through_contacts extends AsyncTask<String , Integer, Void>{

	String auth1,auth2,accesstoken,responseBody;
	Context ctx;
	
//	ProgressDialog bar;
	String responseString;
	SharedPreferences sp;
	
	Get_ContactList_Adapter ContactList_adp;
	ListView contacts_list;
	int h, w;
	String sel_cat,number_string;
//	private ArrayList<String> mItems;
	String TypeOf_Contact;
	public Get_all_registered_user_through_contacts(Context ctx,ProgressDialog bar, String accesstoken, SharedPreferences sp, ListView contacts_list, int h, int w,String sel_cat, String number_string,String TypeOf_Contact) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx;
//		this.bar=bar;
		this.accesstoken=accesstoken;
		this.TypeOf_Contact=TypeOf_Contact;
		this.sp=sp;
		this.contacts_list=contacts_list;
		this.h=h;
		this.w=w;
		
		this.sel_cat=sel_cat;
		this.number_string=number_string;
	}

	@Override
	protected void onPreExecute()
	{	
		/*bar = new ProgressDialog(ctx);
		bar.setMessage("Loading...");
		bar.setIndeterminate(true);     
		bar.show();*/
	}   

	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			
			Log.d("get_all_register", accesstoken+"//"+sp.getString("country_code", ""));
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(Constant_URL.get_all_registered_user_through_contacts);

			try {
				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("accesstoken", accesstoken));
				nameValuePairs.add(new BasicNameValuePair("contacts", number_string));
				nameValuePairs.add(new BasicNameValuePair("contactnames", sel_cat));
				nameValuePairs.add(new BasicNameValuePair("countrycode", sp.getString("country_code", "")));
//				nameValuePairs.add(new BasicNameValuePair("countrycode", "+91"));
				httppost.setEntity(new  UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				responseString = new BasicResponseHandler().handleResponse(response);
				System.out.println(responseString);
  
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}

		}catch(Exception e){
			Log.d("+++", e+"");
		}
		return null;
	}

	@SuppressLint("NewApi")
	@Override
	protected void onPostExecute(Void result1) 
	{
		try {
			JSONObject object = (JSONObject) new JSONTokener(responseString).nextValue();
			if(object.has("error")){
				if(object.getString("error").equalsIgnoreCase("Invalid access token.")){
					Toast.makeText(ctx, "Invalid access token.", 
							Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(ctx, "Mandatory fields are not filled.", 
							Toast.LENGTH_LONG).show();
				}
				
				
			}
			else if(object.has("log")){
				Toast.makeText(ctx, "No registered user yet.", 
						Toast.LENGTH_LONG).show();
			}
			else{  
				JSONArray object1 = (JSONArray) new JSONTokener(object.getString("contacts")).nextValue();
				if(TypeOf_Contact.equalsIgnoreCase("NewContact")){
				int abc=object1.length();
//				JSONArray object1= new JSONArray(object.getString("contacts"));
				Contacts.Contact_List_arrayList1=new ArrayList<Get_ConteactList_Model>();
//				 mItems = new ArrayList<String>();
				for(int i=0;abc>i;i++){
					String str=object1.get(i)+"";
					String str1=object1.getString(i);
					JSONObject object2 = (JSONObject) new JSONTokener(object1.get(i)+"").nextValue();
				
					
//					Toast.makeText(ctx, object2.getString("userid"), 
//						Toast.LENGTH_LONG).show();
					Get_ConteactList_Model contact_list_model=new Get_ConteactList_Model();
					contact_list_model.setUserid(object2.getString("userid"));
					contact_list_model.setPhone(object2.getString("phone"));
					contact_list_model.setSignupname(object2.getString("signupname"));
					if(object2.has("name")){
						contact_list_model.setName(object2.getString("name"));
					}else{
					contact_list_model.setName(object2.getString("phone"));
					}
					Contacts.Contact_List_arrayList1.add(contact_list_model);
//					mItems.add(object2.getString("phone"));
					
					ContactList_adp=new Get_ContactList_Adapter(ctx, Contacts.Contact_List_arrayList1,w,h,sp,"");
					
				}
				
				
				contacts_list.setAdapter(ContactList_adp);
				}
				else if(TypeOf_Contact.equalsIgnoreCase("Contact")){
					int abc=object1.length();
					Home_page.exist_phone=new ArrayList<String>();
					for(int i=0;abc>i;i++){
						JSONObject object2 = (JSONObject) new JSONTokener(object1.get(i)+"").nextValue();
						Home_page.exist_phone.add(object2.getString("phone"));
					}
					
				}
			}
				

		} catch (Exception e) {
			// TODO: handle exception
			Log.d("++Exception+", e+"");

		}



	}
	
	
}

