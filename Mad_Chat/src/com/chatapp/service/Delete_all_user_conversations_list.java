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
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mad_chat.R;
import com.example.mad_chat.Verify_number;

public class Delete_all_user_conversations_list extends AsyncTask<String , Integer, Void>{

	String auth1,auth2,accesstoken,groupid,chatid,Chat_Type;
	Context ctx;
	SharedPreferences preferences;
//	ProgressDialog bar;
	String responseString;
	SharedPreferences sp;
	LinearLayout leads_quate_lay;
	public Delete_all_user_conversations_list(Context ctx, String accesstoken,String chatid,String groupid,String Chat_Type, SharedPreferences sp, LinearLayout leads_quate_lay) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx;
		this.groupid=groupid;
		this.accesstoken=accesstoken;
		this.chatid=chatid;
		this.sp=sp;
		this.Chat_Type=Chat_Type;
		this.leads_quate_lay=leads_quate_lay;
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
			HttpPost httppost = new HttpPost(Constant_URL.delete_user_conversation_list);
			Log.d("Delete", accesstoken+"//"+chatid+"//"+groupid);
			try {
				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				if(Chat_Type.equalsIgnoreCase("0")){
				nameValuePairs.add(new BasicNameValuePair("accesstoken", accesstoken));
				
				nameValuePairs.add(new BasicNameValuePair("chatid", chatid));
				nameValuePairs.add(new BasicNameValuePair("groupid", ""));
			}else{
				nameValuePairs.add(new BasicNameValuePair("accesstoken", accesstoken));
				
				nameValuePairs.add(new BasicNameValuePair("chatid", ""));
				nameValuePairs.add(new BasicNameValuePair("groupid", groupid));
			}
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
			else if(object.has("log")){
				leads_quate_lay.setVisibility(View.GONE);
				/*Toast.makeText(ctx, object.getString("accesstoken"), 
						Toast.LENGTH_LONG).show();
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("userid", object.getString("userid"));
				editer4.putString("accesstoken", object.getString("accesstoken"));
				editer4.putString("phone", object.getString("phone"));
				editer4.putString("isverified", object.getString("isverified"));
				if(object.getString("name").length()>0){
				editer4.putString("name", object.getString("name"));
				}
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
