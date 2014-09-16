package com.example.mad_chat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Notifications extends Activity implements OnClickListener{

	ImageView notification_back,messageAlert_image,groupAlert_image,inAppVibrate_image,inAppSound_image;
	TextView newMessage_text,newMessage_tone,messageAlert_text,group_text,groupMessage_text,group_tone,groupAlerts_text;
	TextView inAppVibrate_text,inAppSound_text,resetAllNotification;
	Context ctx;
	SharedPreferences sp;
	int msg_alet_int=0,group_alert_int=0,inApp_sound_int=0,inApp_vibret_int;
	int ACTIVITY_SET_RINGTONE=0;
	String Tone_Selected="0";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.notifications);
		
		ctx=Notifications.this;
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "helveticaltstd_bold.otf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "helveticaltstd_roman.otf");
		Typeface font3 = Typeface.createFromAsset(getAssets(), "helveticaltstd_light.otf");
		
		notification_back=(ImageView)findViewById(R.id.notification_back);
		notification_back.setOnClickListener(this);
		newMessage_text=(TextView)findViewById(R.id.newMessage_text);
		newMessage_tone=(TextView)findViewById(R.id.newMessage_tone);
		newMessage_tone.setOnClickListener(this);
		messageAlert_text=(TextView)findViewById(R.id.messageAlert_text);
		messageAlert_image=(ImageView)findViewById(R.id.messageAlert_image);
		messageAlert_image.setOnClickListener(this);
		group_text=(TextView)findViewById(R.id.group_text);
		groupMessage_text=(TextView)findViewById(R.id.groupMessage_text);
		group_tone=(TextView)findViewById(R.id.group_tone);
		group_tone.setOnClickListener(this);
		groupAlerts_text=(TextView)findViewById(R.id.groupAlerts_text);
		
		groupAlert_image=(ImageView)findViewById(R.id.groupAlert_image);
		groupAlert_image.setOnClickListener(this);
		inAppVibrate_text=(TextView)findViewById(R.id.inAppVibrate_text);
		inAppVibrate_image=(ImageView)findViewById(R.id.inAppVibrate_image);
		inAppVibrate_image.setOnClickListener(this);
		inAppSound_text=(TextView)findViewById(R.id.inAppSound_text);
		inAppSound_image=(ImageView)findViewById(R.id.inAppSound_image);
		inAppSound_image.setOnClickListener(this);
		resetAllNotification=(TextView)findViewById(R.id.resetAllNotification);
		resetAllNotification.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.notification_back:
			if(sp.getString("Home_page", "").equals("Home")){
//			Intent mainIntent = new Intent(Notifications.this,Home_page.class);
//			startActivity(mainIntent); 
//			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			}
			else{
//				Intent mainIntent = new Intent(Notifications.this,Contacts.class);
//				startActivity(mainIntent); 
//				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
			}
			break;
			
		case R.id.messageAlert_image:
			if(msg_alet_int==0){
				msg_alet_int=1;
				messageAlert_image.setBackgroundResource(R.drawable.on_btn);
				
			}
			else{
				msg_alet_int=0;
				messageAlert_image.setBackgroundResource(R.drawable.off_btn);
			}
			break;
			
		case R.id.groupAlert_image:
			if(group_alert_int==0){
				group_alert_int=1;
				groupAlert_image.setBackgroundResource(R.drawable.on_btn);
				
			}
			else{
				group_alert_int=0;
				groupAlert_image.setBackgroundResource(R.drawable.off_btn);
			}
			break;
			
		case R.id.inAppVibrate_image:
			if(inApp_vibret_int==0){
				inApp_vibret_int=1;
				inAppVibrate_image.setBackgroundResource(R.drawable.on_btn);
				
			}
			else{
				inApp_vibret_int=0;
				inAppVibrate_image.setBackgroundResource(R.drawable.off_btn);
			}
			break;
			
		case R.id.inAppSound_image:
			if(inApp_sound_int==0){
				inApp_sound_int=1;
				inAppSound_image.setBackgroundResource(R.drawable.on_btn);
				
			}
			else{
				inApp_sound_int=0;
				inAppSound_image.setBackgroundResource(R.drawable.off_btn);
			}
			break;
			
		case R.id.resetAllNotification:
			
			break;
			
		case R.id.newMessage_tone:
			Tone_Selected="1";
			Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
			startActivityForResult(intent, ACTIVITY_SET_RINGTONE);
			break;
			
		case R.id.group_tone:
			Tone_Selected="2";
			Intent intent1 = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
			startActivityForResult(intent1, ACTIVITY_SET_RINGTONE);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data )
	{
	    switch (resultCode) {
	        case RESULT_OK:
	        	if(Tone_Selected.equalsIgnoreCase("1")){
	            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
	            Log.i("Sample", "uri " + uri);
	            SharedPreferences.Editor editor=sp.edit();
				editor.putString("Message_tone",""+uri);
				editor.commit();
	        	}else if(Tone_Selected.equalsIgnoreCase("2")){
	        		Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
		            Log.i("Sample", "uri " + uri);
		            SharedPreferences.Editor editor=sp.edit();
					editor.putString("Group_tone",""+uri);
					editor.commit();
	        	}
//	            RingtoneManager.setActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION, uri);
	           
	           /* int icon = R.drawable.chat_icon;
	            long when = System.currentTimeMillis();
	            NotificationManager notificationManager = (NotificationManager)
	                    ctx.getSystemService(Context.NOTIFICATION_SERVICE);
	            Notification notification = new Notification(icon, "Hiiiiii", when);
	            
	            String title = ctx.getString(R.string.app_name);
	            
	            Intent notificationIntent = new Intent(ctx, Notification.class);
	            // set intent so it does not start a new activity
	            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
	                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
	            PendingIntent intent =
	                    PendingIntent.getActivity(ctx, 0, notificationIntent, 0);
	            notification.setLatestEventInfo(ctx, title, "Helloooooo", intent);
	            notification.flags |= Notification.FLAG_AUTO_CANCEL;
	            
	            // Play default notification sound
//	            notification.defaults |= Notification.DEFAULT_SOUND;
	            
	            notification.sound = uri;
	            //notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "your_sound_file_name.mp3");
        
	            // Vibrate if vibrate is enabled
	            notification.defaults |= Notification.DEFAULT_VIBRATE;
	            notificationManager.notify(0, notification);      */
	                 break;
	           }
	    super.onActivityResult(requestCode, resultCode, data);
	}

}
