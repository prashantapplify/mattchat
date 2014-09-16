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

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.chatapp.model.Get_Convercation_Adapter;
import com.chatapp.model.Get_convercation_model;
import com.example.mad_chat.Home_page;

public class Get_all_user_conversations_list extends AsyncTask<String , Integer, Void>{

	String auth1,auth2,phone_no,responseBody;
	Context ctx;
	SharedPreferences preferences;
//	ProgressDialog bar;
	String responseString;
	SharedPreferences sp;
	int h,w;
	ListView Convercation_List;
	Get_Convercation_Adapter ContactList_adp;
	LinearLayout all_lay,group_lay;
	/*public Get_all_user_conversations_list(Context ctx,ProgressDialog bar,String phone_no, SharedPreferences sp,int h, int w,ListView Convercation_List) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx;
		this.bar=bar;
		this.h=h;
		this.w=w;
		this.Convercation_List=Convercation_List;
		this.phone_no=phone_no;
		this.sp=sp;
	}*/

	

	public Get_all_user_conversations_list(Context ctx2, ProgressDialog bar2,
			String string, SharedPreferences sp2, int h2, int w2,
			ListView convercation_List2, LinearLayout all_lay, LinearLayout group_lay) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx2;
//		this.bar=bar2;
		this.h=h2;
		this.w=w2;
		this.Convercation_List=convercation_List2;
		this.phone_no=string;
		this.sp=sp2;
		this.all_lay=all_lay;
		this.group_lay=group_lay;
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

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(Constant_URL.get_all_user_conversations_list);

			try {
				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("accesstoken", phone_no));
				
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

	@Override
	protected void onPostExecute(Void result1) 
	{
		try {
			JSONObject object = (JSONObject) new JSONTokener(responseString).nextValue();
			if(object.has("error")){
				if(object.getString("error").equalsIgnoreCase("User already exists!")){
					Toast.makeText(ctx, "User already exists!", 
							Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(ctx, "Mandatory fields are not filled.", 
							Toast.LENGTH_LONG).show();
				}
				
				
			}
			else{
				JSONArray object1 = (JSONArray) new JSONTokener(object.getString("chat")).nextValue();
				int abc=object1.length();
				Home_page.Contact_List_arrayList1=new ArrayList<Get_convercation_model>();
				for(int i=0;abc>i;i++){
					all_lay.setVisibility(View.GONE);
					group_lay.setVisibility(View.VISIBLE);
					JSONObject object2 = (JSONObject) new JSONTokener(object1.get(i)+"").nextValue();
					Get_convercation_model contact_list_model=new Get_convercation_model();
					if(!object2.getString("senderid").equalsIgnoreCase("")){
						contact_list_model.setChatid(object2.getString("chatid"));
					contact_list_model.setSenderid(object2.getString("senderid"));
					contact_list_model.setSendername(object2.getString("sendername"));
					contact_list_model.setChat(object2.getString("chat"));
					contact_list_model.setChat_Type("0");
					contact_list_model.setTime(object2.getString("lasttimestamp"));
					
					}else if(!object2.getString("groupid").equalsIgnoreCase("")){
						contact_list_model.setChatid(object2.getString("chatid"));
						contact_list_model.setSenderid(object2.getString("groupid"));
						contact_list_model.setSendername(object2.getString("groupname"));
						contact_list_model.setChat(object2.getString("chat"));
						contact_list_model.setChat_Type("1");
						contact_list_model.setTime(object2.getString("lasttimestamp"));
					}
					Home_page.Contact_List_arrayList1.add(contact_list_model);
					ContactList_adp=new Get_Convercation_Adapter(ctx, Home_page.Contact_List_arrayList1,w,h,sp);
					
				}
				Convercation_List.setAdapter(ContactList_adp);
				/*Toast.makeText(ctx, object.getString("accesstoken"), 
						Toast.LENGTH_LONG).show();
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("userid", object.getString("senderid"));
				editer4.putString("accesstoken", object.getString("sendername"));
				editer4.putString("phone", object.getString("chat"));
				
				editer4.commit();
				
				Intent mainIntent1 = new Intent(ctx,Verify_number.class);
				ctx.startActivity(mainIntent1); 
				((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
				((Activity) ctx).finish();*/
			}
				/*if(object.getString("message").equalsIgnoreCase("OK")){
					String str = object.getString("response").replace("[", "");
					String str1 = str.replace("]", "");
					System.out.println(str1);
					if(str1.trim().equalsIgnoreCase("\"User name is invalid\"")){
						if(bar.isShowing()){
							bar.dismiss();
						}
						SharedPreferences.Editor editer4 = sp.edit();
						editer4.putString("username", username);
						editer4.putString("password", password);
						editer4.commit();

						Intent intent=new Intent(ctx,Invite_Screen.class);
//						Intent intent=new Intent(ctx,Create_Account.class);
						ctx.startActivity(intent);
						((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
						((Activity) ctx).finish();
					}else{
						JSONObject object1 = (JSONObject) new JSONTokener(str1).nextValue();

						if(bar.isShowing()){
							bar.dismiss();
						}
						
						
						SharedPreferences.Editor editer4 = sp.edit();
						editer4.putString("id", object1.getString("id"));
						editer4.putString("username", object1.getString("email"));
						editer4.putString("email", object1.getString("email"));
//						if(object1.has("company_name")){
//						editer4.putString("company_name", object1.getString("company_name"));
						editer4.putString("company_id", object1.getString("company_id"));
//						}
//						if(object1.has("team_name")){
//						editer4.putString("team_name", object1.getString("team_name"));
						editer4.putString("team_id", object1.getString("team_id"));
//						}
						if(object1.has("message")){
						editer4.putString("message", object1.getString("message"));
						editer4.putString("sending_time", object1.getString("sending_time"));
						editer4.putString("timebomb_message_id", object1.getString("timebomb_message_id"));
						}
						if(object1.has("task")){
						editer4.putString("task", object1.getString("task"));
						editer4.putString("timebomb_task_time", object1.getString("timebomb_task_time"));
						editer4.putString("timebomb_task_id", object1.getString("timebomb_task_id"));
						}
						editer4.putString("loginstatus", "1");
						
						
						
						editer4.commit();
						Intent intent=new Intent(ctx,Dash_Board.class);

						ctx.startActivity(intent);
						((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
						((Activity) ctx).finish();


					}
				}*/

			
			/*if(bar.isShowing()){
				bar.dismiss();
			}*/

		} catch (Exception e) {
			// TODO: handle exception
			Log.d("++Exception+", e+"");
//			Toast.makeText(ctx, "invalid Authorization Required.", 
//					Toast.LENGTH_LONG).show();
			/*if(bar.isShowing()){
				bar.dismiss();
			}*/
			
		}



	}
}
