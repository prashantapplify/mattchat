package com.example.mad_chat;

import java.util.ArrayList;
import java.util.List;

import com.chatapp.model.Get_Convercation_Adapter;
import com.chatapp.model.Get_GroupInviteList_Adapter;
import com.chatapp.service.Create_new_group;
import com.chatapp.service.Get_all_user_conversations_list;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Group_inviteList extends Activity implements OnClickListener{

	TextView groupInvite_headerText,groupInvite_done;
	SharedPreferences sp;
	ImageView groupInvite_back;
	ProgressDialog bar;
	Context ctx;
	int h,w;
	ListView groupInvit_List;
	public static List<String> Group_Id =new ArrayList<String>();
	public static List<String> Group_name =new ArrayList<String>();
	EditText groupinvite_edittext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_invitelist);
		
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		ctx=Group_inviteList.this;
		Display display = getWindowManager().getDefaultDisplay();
		w=display.getWidth();
		h=display.getHeight();
		groupInvite_headerText=(TextView)findViewById(R.id.groupInvite_headerText);
		groupInvite_headerText.setText(sp.getString("newGroup_name", ""));
		groupInvite_back=(ImageView)findViewById(R.id.groupInvite_back);
		groupInvite_back.setOnClickListener(this);
		groupInvite_done=(TextView)findViewById(R.id.groupInvite_done);
		groupInvite_done.setOnClickListener(this);
		groupInvit_List=(ListView)findViewById(R.id.groupInvit_List);
		groupinvite_edittext=(EditText)findViewById(R.id.groupinvite_edittext);
		
		groupInvit_List.setAdapter(new Get_GroupInviteList_Adapter(ctx, Contacts.Contact_List_arrayList1,w,h,sp,Group_Id,groupinvite_edittext));
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.groupInvite_back:
			Intent mainIntent = new Intent(Group_inviteList.this,New_Group.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;

		case R.id.groupInvite_done:
			Log.d("Group_Id", Group_Id+"");
			String str=Group_Id.toString().replace("[", "");
			String str1=str.replace("]", "");
			Log.d("str1", str1);
			new Create_new_group(ctx, bar,sp.getString("accesstoken",""),sp.getString("newGroup_name", ""),str1,sp,h,w).execute("main");
			
			
			break;
		default:
			break;
		}
	}
	 public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			Intent mainIntent = new Intent(Group_inviteList.this,New_Group.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			
	    }
	

}
