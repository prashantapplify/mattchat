package com.example.mad_chat;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Invite_Dialog extends Activity implements OnClickListener{

	LinearLayout approx_lay;
	int w,h;
	TextView Invite_mail,Invite_sms,Invite_cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	     WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
	     this.getWindow().setBackgroundDrawable(new BitmapDrawable());
	 wmlp.gravity = Gravity.BOTTOM ;
		setContentView(R.layout.invite_dialog);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		w = display.getWidth();
		h = display.getHeight();
		
		approx_lay=(LinearLayout)findViewById(R.id.approx_lay);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w-30, (h/3)-20);
		approx_lay.setLayoutParams(params);
		
		Invite_mail=(TextView)findViewById(R.id.Invite_mail);
		Invite_mail.setOnClickListener(this);
		Invite_sms=(TextView)findViewById(R.id.Invite_sms);
		Invite_sms.setOnClickListener(this);
		Invite_cancel=(TextView)findViewById(R.id.Invite_cancel);
		Invite_cancel.setOnClickListener(this);
				
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Invite_mail:
			Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
			  emailIntent.putExtra(Intent.EXTRA_EMAIL, "");
			    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test Subject");
//			    emailIntent.putExtra(Intent.EXTRA_TEXT, 
//			    		contactUs_edit.getText().toString()); 
			    emailIntent.setType("text/plain");
			    final PackageManager pm = this.getPackageManager();
			    final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
			    ResolveInfo best = null;
			    for(final ResolveInfo info : matches)
			        if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
			            best = info;
			    if (best != null)
			        emailIntent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
			    this.startActivity(emailIntent);
			    
			break;
			
		case R.id.Invite_sms:
			Uri uri = Uri.parse("smsto:");   
			Intent it = new Intent(Intent.ACTION_SENDTO, uri);   
			it.putExtra("sms_body", "The SMS text");   
			startActivity(it); 
			break;
			
		case R.id.Invite_cancel:
			finish();
			break;

		default:
			break;
		}
	}
	
	

}
