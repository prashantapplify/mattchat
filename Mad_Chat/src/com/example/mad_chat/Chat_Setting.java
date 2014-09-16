package com.example.mad_chat;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Chat_Setting extends Activity implements OnClickListener{

	ImageView chatSetting_back,enterIsSend_image,ChatBackup_image,TextSize_image,emailConversation_image,deleteAllConversation_image,saveIncommingMedia_image;
	TextView enterIsSend_text,ChatBackup_text,TextSize_text,emailConversation_text,deleteAllConversation_text,saveIncommingMedia_text;
	Context ctx;
	SharedPreferences sp;
	int check=0;
	int saveIncommingMedia_int=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat_setting);
		
		ctx=Chat_Setting.this;
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "helveticaltstd_bold.otf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "helveticaltstd_roman.otf");
		Typeface font3 = Typeface.createFromAsset(getAssets(), "helveticaltstd_light.otf");
		chatSetting_back=(ImageView)findViewById(R.id.chatSetting_back);
		chatSetting_back.setOnClickListener(this);
		enterIsSend_text=(TextView)findViewById(R.id.enterIsSend_text);
		enterIsSend_image=(ImageView)findViewById(R.id.enterIsSend_image);
		enterIsSend_image.setOnClickListener(this);
		/*ChatBackup_text=(TextView)findViewById(R.id.ChatBackup_text);
		ChatBackup_image=(ImageView)findViewById(R.id.ChatBackup_image);
		ChatBackup_image.setOnClickListener(this);
		TextSize_text=(TextView)findViewById(R.id.TextSize_text);
		TextSize_image=(ImageView)findViewById(R.id.TextSize_image);
		TextSize_image.setOnClickListener(this);*/
		emailConversation_text=(TextView)findViewById(R.id.emailConversation_text);
		emailConversation_image=(ImageView)findViewById(R.id.emailConversation_image);
		emailConversation_image.setOnClickListener(this);
		deleteAllConversation_text=(TextView)findViewById(R.id.deleteAllConversation_text);
		deleteAllConversation_image=(ImageView)findViewById(R.id.deleteAllConversation_image);
		deleteAllConversation_image.setOnClickListener(this);
		saveIncommingMedia_text=(TextView)findViewById(R.id.saveIncommingMedia_text);
		saveIncommingMedia_image=(ImageView)findViewById(R.id.saveIncommingMedia_image);
		saveIncommingMedia_image.setOnClickListener(this);
		if(sp.getString("enterIsSend", "").equalsIgnoreCase("1")){
			check=1;
			enterIsSend_image.setBackgroundResource(R.drawable.check_box);
		}else{
			check=0;
			enterIsSend_image.setBackgroundResource(R.drawable.uncheck_box);
		}
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.chatSetting_back:
			if(sp.getString("Home_page", "").equals("Home")){
//				Intent mainIntent = new Intent(Chat_Setting.this,Home_page.class);
//				startActivity(mainIntent); 
//				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
				}
			else{
//				Intent mainIntent = new Intent(Chat_Setting.this,Contacts.class);
//				startActivity(mainIntent); 
//				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
			}
			break;
			
		case R.id.enterIsSend_image:
			if(check==0){
				check=1;
				enterIsSend_image.setBackgroundResource(R.drawable.check_box);
				SharedPreferences.Editor editor=sp.edit();
				
				editor.putString("enterIsSend","1");
			
				
				editor.commit();
			}else{
				check=0;
				enterIsSend_image.setBackgroundResource(R.drawable.uncheck_box);
				SharedPreferences.Editor editor=sp.edit();
				editor.putString("enterIsSend","0");
				editor.commit();
			}
			break;
			
		/*case R.id.ChatBackup_image:
			
			break;
			
		case R.id.TextSize_image:
			
			break;*/
			
		case R.id.emailConversation_image:
			
			break;
			
		case R.id.deleteAllConversation_image:
			
			break;
			
		case R.id.saveIncommingMedia_image:
			if(saveIncommingMedia_int==0){
				saveIncommingMedia_int=1;
				saveIncommingMedia_image.setBackgroundResource(R.drawable.on_btn);
			}else{
				saveIncommingMedia_int=0;
				saveIncommingMedia_image.setBackgroundResource(R.drawable.off_btn);
			}
			break;

		default:
			break;
		}
	}
	
	

}
