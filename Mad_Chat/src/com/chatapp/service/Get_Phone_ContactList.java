package com.chatapp.service;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AlphabetIndexer;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatapp.model.Get_phone_ContactList_Model;
import com.example.mad_chat.R;

public class Get_Phone_ContactList  extends BaseAdapter {
	public static ArrayList<Get_phone_ContactList_Model> Member_List_getArray;
	Context classreference;
	private LayoutInflater l_Inflater;
	int height, width;
	 private AlphabetIndexer mIndexer;
	 String check,last_contact="";
	SharedPreferences sp;
	InputMethodManager imm;
	public Get_Phone_ContactList(Context context, ArrayList<Get_phone_ContactList_Model> leads_getArray1,int w,int h, SharedPreferences preferences,String check,InputMethodManager imm) {
		Member_List_getArray = leads_getArray1;
		l_Inflater = LayoutInflater.from(context);
		classreference = context;
		height=h;
		width=w;
		sp=preferences;
		this.check=check;
		this.imm=imm;
	}

	public int getCount() {
		Log.d("leads_getArray", Member_List_getArray.size()+"");
		return Member_List_getArray.size();
	}

	public Object getItem(int position) {
		
		return Member_List_getArray.get(position);
	}

	public long getItemId(int position) {
		
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.contacts_list_item, null);
			holder = new ViewHolder();

			
			holder.member_list_email = (TextView) convertView.findViewById(R.id.member_list_email);
			holder.member_list_sinupname = (TextView) convertView.findViewById(R.id.member_list_sinupname);
			
//			Typeface font = Typeface.createFromAsset(classreference.getAssets(), "NesobriteLt-Regular.ttf");
//			Typeface font2 = Typeface.createFromAsset(classreference.getAssets(), "NesobriteRg-Bold.ttf");
//			holder.member_list_email.setTypeface(font);
			
			
			holder.leads_quate_lay=(LinearLayout)convertView.findViewById(R.id.member_list_lay);
			LinearLayout.LayoutParams params3;
			if(!check.equalsIgnoreCase("")){
			 params3 = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,height/8);
			 holder.leads_quate_lay.setLayoutParams(params3);
			}else{
				 params3 = new LinearLayout.LayoutParams(
							LayoutParams.MATCH_PARENT,height/12);
					 holder.leads_quate_lay.setLayoutParams(params3);
			}
			
			

			convertView.setTag(holder);
		} 
		else
			holder=(ViewHolder)convertView.getTag();

		try {

//			Log.d("position", Member_List_getArray.get(position).getName().substring(0, 1));
			if(!check.equalsIgnoreCase("")){
				holder.member_list_email.setText(""+Member_List_getArray.get(position).getName());
				holder.member_list_sinupname.setVisibility(View.GONE);
			}else{
				
				if(!Member_List_getArray.get(position).getName().substring(0, 1).equalsIgnoreCase(last_contact) || last_contact==""){
					last_contact=Member_List_getArray.get(position).getName().substring(0, 1).toUpperCase();
//					holder.contact_header_text.setText(last_contact);
//					holder.contact_header_text.setVisibility(View.GONE);
					
					holder.member_list_email.setText(""+Member_List_getArray.get(position).getName());
					holder.member_list_sinupname.setVisibility(View.GONE);
				}
				else{
//					holder.contact_header_text.setVisibility(View.GONE);
					holder.member_list_email.setText(""+Member_List_getArray.get(position).getName());
					holder.member_list_sinupname.setVisibility(View.GONE);
				}
			
			}
			holder.leads_quate_lay.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					imm.hideSoftInputFromWindow(holder.leads_quate_lay.getWindowToken(), 0);
					/*Chat_Room.Chat_List_arrayList=new ArrayList<Get_Chat_model>();
					SharedPreferences.Editor editor=sp.edit();
					
					editor.putString("To_id",""+Member_List_getArray.get(position).getUserid());
					editor.putString("To_name",""+Member_List_getArray.get(position).getName());
					editor.putString("To_number",""+Member_List_getArray.get(position).getPhone());
					
					editor.commit();
					
					Intent leads_quate_lay=new Intent(classreference,Chat_Room.class);
					classreference.startActivity(leads_quate_lay);*/
//					((Activity) classreference).finish();
				}
			});

			

			

		} catch (Exception e) {
			Log.d("Exception", e+"");
		}


		return convertView;
	}

	public static class ViewHolder {
		public TextView member_list_email,member_list_sinupname,contact_header_text;
		
		public ImageView image,icon_image;
		public LinearLayout leads_quate_lay,set;
	}

	public void setIndexer(AlphabetIndexer Indexer) {
		// TODO Auto-generated method stub
		 mIndexer = Indexer;
	}

	

}

