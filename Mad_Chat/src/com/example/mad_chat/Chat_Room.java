package com.example.mad_chat;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chatapp.model.Get_Chat_Adapter;
import com.chatapp.model.Get_Chat_model;
import com.chatapp.service.Delete_all_user_conversations;
import com.chatapp.service.Delete_all_user_conversations_list;
import com.chatapp.service.Get_Chat_send;
import com.chatapp.service.get_all_messages;
import com.chatapp.service.get_unread_messages;

public class Chat_Room extends Activity implements OnClickListener {

	ImageView ChatRoom_back,send_msg_btn;
	TextView ChatRoom_header_text,chat_conversation,chat_Block_conversation;
	ImageView ChatRoom_Block;
	SharedPreferences sp;
	EditText send_msg_text;
	public static ArrayList<Get_Chat_model> Chat_List_arrayList=new ArrayList<Get_Chat_model>();
	ListView chat_room_list;
	Context ctx;
	int h,w;
	private Handler handler;
	private Runnable runnable;
	InputMethodManager imm;
	Get_Chat_Adapter ChatList_adp;
	LinearLayout chat_room_list_lay1,chat_room_list1;
	int menu_click=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setSoftInputMode(
//				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//		getWindow().setSoftInputMode(EditorInfo.IME_ACTION_DONE);
		
		setContentView(R.layout.chat_room);
		
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		
		InputMethodManager im = (InputMethodManager) this.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		
		ctx=Chat_Room.this;
		Display display = getWindowManager().getDefaultDisplay();
		w=display.getWidth();
		h=display.getHeight();
		
		ChatRoom_back=(ImageView)findViewById(R.id.ChatRoom_back);
		
		ChatRoom_back.setOnClickListener(this);
		ChatRoom_header_text=(TextView)findViewById(R.id.ChatRoom_header_text);
		ChatRoom_header_text.setText(sp.getString("To_name", ""));
		ChatRoom_Block=(ImageView)findViewById(R.id.ChatRoom_Block);
		ChatRoom_Block.setOnClickListener(this);
		send_msg_btn=(ImageView)findViewById(R.id.send_msg_btn);
		send_msg_btn.setOnClickListener(this);
		send_msg_text=(EditText)findViewById(R.id.send_msg_text);
		chat_room_list=(ListView)findViewById(R.id.chat_room_list);

		chat_room_list_lay1=(LinearLayout)findViewById(R.id.chat_room_list_lay1);
		
		chat_room_list1=(LinearLayout)findViewById(R.id.chat_room_list1);
		
		LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
				(w/2)-50,h/6);
		params3.setMargins(0, w/64, w/64, 0);
		chat_room_list1.setLayoutParams(params3);
		chat_room_list1.setVisibility(View.GONE);
		chat_Block_conversation=(TextView)findViewById(R.id.chat_Block_conversation);
		chat_Block_conversation.setOnClickListener(this);
		chat_conversation=(TextView)findViewById(R.id.chat_conversation);
		chat_conversation.setOnClickListener(this);
//		chat_room_list_lay1.setLayoutParams(params3);
		
		if(sp.getString("enterIsSend", "").equalsIgnoreCase("1")){
		send_msg_text.setOnKeyListener(new OnKeyListener()
		{
		    public boolean onKey(View v, int keyCode, KeyEvent event)
		    {
		        if (event.getAction() == KeyEvent.ACTION_DOWN)
		        {
		            switch (keyCode)
		            {
		                case KeyEvent.KEYCODE_DPAD_CENTER:
		                case KeyEvent.KEYCODE_ENTER:
		                	
		                	if(send_msg_text.getText().toString().trim().length()>0){
		        				Get_Chat_model contact_list_model=new Get_Chat_model();
		        				contact_list_model.setMsg(send_msg_text.getText().toString().trim());
		        				contact_list_model.setSend_id(sp.getString("userid", ""));
		        				Chat_Room.Chat_List_arrayList.add(contact_list_model);
		        				ChatList_adp=new Get_Chat_Adapter(ctx, Chat_Room.Chat_List_arrayList,w,h,sp,"send",imm,chat_room_list1);
		        				ChatList_adp.notifyDataSetChanged();
		        				chat_room_list.setAdapter(ChatList_adp);
		        				chat_room_list.setSelection(ChatList_adp.getCount() - 1);
		        				
		        			new Get_Chat_send(ctx, sp.getString("accesstoken", ""),sp.getString("To_id", ""),send_msg_text.getText().toString(),sp,chat_room_list,w,h,imm,sp.getString("Chat_Type", ""),sp.getString("To_name", ""),chat_room_list1).execute("main");
		        			send_msg_text.setText("");
		        			}
		                	
		                    return true;
		                default:
		                    break;
		            }
		        }
		        return false;
		    }
		});
		}
		Log.d("Chat Room", sp.getString("accesstoken", "")+"//"+sp.getString("To_id", "")+"//"+sp.getString("Chat_Type", ""));
		
		new get_all_messages(ctx, sp.getString("accesstoken", ""),sp.getString("To_id", ""),sp,chat_room_list,w,h,imm,sp.getString("Chat_Type", ""),sp.getString("Chatid", ""),chat_room_list1).execute("main");
		
		handler = new Handler();
		
		runnable = new Runnable() 
		{
		    public void run() 
		    {
		    	new get_unread_messages(ctx, sp.getString("accesstoken", ""),sp.getString("To_id", ""),sp,chat_room_list,w,h,imm,sp.getString("Chat_Type", ""),chat_room_list1).execute("main");
				handler.postDelayed(this, 3000);
		    }
		};
		runnable.run();
	}
	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id.ChatRoom_back:
			handler.removeCallbacks(runnable);
//			Intent mainIntent = new Intent(Chat_Room.this,New_contacts.class);
//			startActivity(mainIntent); 
//			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;
			
		case R.id.ChatRoom_Block:
			if(menu_click==0){
				menu_click=1;	
				imm.hideSoftInputFromWindow(ChatRoom_Block.getWindowToken(), 0);
				chat_room_list1.setVisibility(View.VISIBLE);
				Animation animation   =    AnimationUtils.loadAnimation(this, R.anim.contact_anim);
			    animation.setDuration(500);
			    chat_room_list1.setAnimation(animation);
			    chat_room_list1.animate();
			    animation.start();
			}else{
				menu_click=0;
				 chat_room_list1.setVisibility(View.GONE);
				imm.hideSoftInputFromWindow(ChatRoom_Block.getWindowToken(), 0);
				
				Animation animation1   =    AnimationUtils.loadAnimation(this, R.anim.contact_anim_close);
			    animation1.setDuration(500);
			    chat_room_list1.setAnimation(animation1);
			    chat_room_list1.animate();
			    animation1.start();
			   
			}
			
			break;
			
		case R.id.send_msg_btn:
			if(send_msg_text.getText().toString().trim().length()>0){
				/*Get_Chat_model contact_list_model=new Get_Chat_model();
				contact_list_model.setMsg(send_msg_text.getText().toString().trim());
				contact_list_model.setSend_id(sp.getString("userid", ""));
				Chat_Room.Chat_List_arrayList.add(contact_list_model);*/
				
				
			new Get_Chat_send(ctx, sp.getString("accesstoken", ""),sp.getString("To_id", ""),send_msg_text.getText().toString(),sp,chat_room_list,w,h,imm,sp.getString("Chat_Type", ""),sp.getString("To_name", ""),chat_room_list1).execute("main");
			send_msg_text.setText("");
			}
			break;
			
		case R.id.chat_conversation:
		
			new AlertDialog.Builder(this)
			  .setTitle("Alert")
			    .setMessage( "Are you sure you want to clear this conversation?")
			    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // continue with delete
			        	new Delete_all_user_conversations(ctx, sp.getString("accesstoken", ""),sp.getString("Chatid", ""),sp.getString("To_id", ""),sp.getString("Chat_Type", ""),sp,chat_room_list,chat_room_list1).execute("main");
						
			        }
			     })
			    .setNegativeButton("No", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     })
			    .setIcon(android.R.drawable.ic_dialog_alert)
			     .show();
			
			break;
			
		case R.id.chat_Block_conversation:
			
			break;
		
		
		

		default:
			break;
		}
	}
	 public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			handler.removeCallbacks(runnable);
//			Intent mainIntent = new Intent(Chat_Room.this,New_contacts.class);
//			startActivity(mainIntent); 
//			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			
	    }
	
}
