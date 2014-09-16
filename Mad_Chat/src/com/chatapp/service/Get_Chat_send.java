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

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.chatapp.model.Get_Chat_Adapter;
import com.chatapp.model.Get_Chat_model;
import com.example.mad_chat.Chat_Room;

public class Get_Chat_send extends AsyncTask<String , Integer, Void>{

	String auth1,msg,accesstoken,To_id,responseBody;
	Context ctx;
	SharedPreferences preferences;
	Get_Chat_Adapter ChatList_adp;
	String responseString;
	SharedPreferences sp;
	ListView chat_room_list;
	int w, h;
	InputMethodManager imm;
	String Chat_Type;
	String To_name;
	LinearLayout chat_room_list1;
	public Get_Chat_send(Context ctx, String accesstoken,String To_id,String msg, SharedPreferences sp, ListView chat_room_list, int w, int h, InputMethodManager imm,String Chat_Type,String To_name, LinearLayout chat_room_list1) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx;
		this.msg=msg;
		this.accesstoken=accesstoken;
		this.To_id=To_id;
		this.sp=sp;
		this.chat_room_list=chat_room_list;
		this.h=h;
		this.w=w;
		this.imm=imm;
		this.Chat_Type=Chat_Type;
		this.To_name=To_name;
		this.chat_room_list1=chat_room_list1;
	}

	@Override
	protected void onPreExecute()
	{	
		
	}   

	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost;
			if(Chat_Type.equalsIgnoreCase("0")){
				 httppost = new HttpPost(Constant_URL.send_new_message);
			}else{
				 httppost = new HttpPost(Constant_URL.send_new_message_to_group);
			}
			

			try {
				// Add your data  
				Log.d("send message text", accesstoken+"//"+To_id+"//"+msg+"//"+Chat_Type);
				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				if(Chat_Type.equalsIgnoreCase("0")){
					nameValuePairs.add(new BasicNameValuePair("accesstoken",accesstoken ));
					nameValuePairs.add(new BasicNameValuePair("receiverid", To_id));
					nameValuePairs.add(new BasicNameValuePair("receivername", To_name));
					nameValuePairs.add(new BasicNameValuePair("message", msg));
				}else{
					nameValuePairs.add(new BasicNameValuePair("accesstoken",accesstoken ));
					nameValuePairs.add(new BasicNameValuePair("groupid", To_id));
					nameValuePairs.add(new BasicNameValuePair("message", msg));
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
			else{
				Get_Chat_model contact_list_model=new Get_Chat_model();
				contact_list_model.setMsg(msg);
				contact_list_model.setSend_id(sp.getString("userid", ""));
				contact_list_model.setSend_time(object.getString("timestamp"));
				Chat_Room.Chat_List_arrayList.add(contact_list_model);
				ChatList_adp=new Get_Chat_Adapter(ctx, Chat_Room.Chat_List_arrayList,w,h,sp,"send",imm,chat_room_list1);
				ChatList_adp.notifyDataSetChanged();
				chat_room_list.setAdapter(ChatList_adp);
				chat_room_list.setSelection(ChatList_adp.getCount() - 1);
				/*Get_Chat_model contact_list_model=new Get_Chat_model();
				contact_list_model.setMsg(msg);
				contact_list_model.setSend_id(sp.getString("userid", ""));
				Chat_Room.Chat_List_arrayList.add(contact_list_model);
				ChatList_adp=new Get_Chat_Adapter(ctx, Chat_Room.Chat_List_arrayList,w,h,sp,"send",imm);
				ChatList_adp.notifyDataSetChanged();
				chat_room_list.setAdapter(ChatList_adp);
				chat_room_list.setSelection(ChatList_adp.getCount() - 1);*/
				
				/*Toast.makeText(ctx, object.getString("accesstoken"), 
						Toast.LENGTH_LONG).show();
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("userid", object.getString("userid"));
				editer4.putString("accesstoken", object.getString("accesstoken"));
				editer4.putString("phone", object.getString("phone"));
				editer4.putString("isverified", object.getString("isverified"));
				editer4.commit();*/
				
//				Intent mainIntent1 = new Intent(ctx,Home_page.class);
//				ctx.startActivity(mainIntent1); 
//				((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
//				((Activity) ctx).finish();
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

			

		} catch (Exception e) {
			// TODO: handle exception
			Log.d("++Exception+", e+"");
//			Toast.makeText(ctx, "invalid Authorization Required.", 
//					Toast.LENGTH_LONG).show();
			
			
		}



	}
}


