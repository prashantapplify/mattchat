package com.example.mad_chat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

public class New_Group extends Activity implements OnClickListener{

	ImageView newGroup_back,newGroup_next;
	EditText newGroup_name;
	SharedPreferences sp;
//	ColorDrawable colorDrawable = new ColorDrawable( Color.WHITE );
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setBackgroundDrawable( colorDrawable );
		setContentView(R.layout.new_group);
		
		
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		
		newGroup_back=(ImageView)findViewById(R.id.newGroup_back);
		newGroup_back.setOnClickListener(this);
		newGroup_next=(ImageView)findViewById(R.id.newGroup_next);
		newGroup_next.setOnClickListener(this);
		newGroup_name=(EditText)findViewById(R.id.newGroup_name);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.newGroup_back:
			Intent mainIntent = new Intent(New_Group.this,New_contacts.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;
			
		case R.id.newGroup_next:
			if(newGroup_name.getText().toString().trim().length()>0){
			SharedPreferences.Editor editer4 = sp.edit();
			editer4.putString("newGroup_name", newGroup_name.getText().toString().trim());
			
			editer4.commit();
			Intent mainIntent2 = new Intent(New_Group.this,Group_inviteList.class);
			startActivity(mainIntent2); 
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			}
			break;

		default:
			break;
		}
	}
	 public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			Intent mainIntent = new Intent(New_Group.this,New_contacts.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			
	    }
	

}
