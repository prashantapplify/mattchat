package com.chatapp.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AlphabetIndexer;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatapp.model.Get_Convercation_Adapter.ViewHolder;
import com.example.mad_chat.Chat_Room;
import com.example.mad_chat.Group_inviteList;
import com.example.mad_chat.R;

public class Get_GroupInviteList_Adapter extends BaseAdapter {
	public static ArrayList<Get_ConteactList_Model> Member_List_getArray;
	Context classreference;
	private LayoutInflater l_Inflater;
	int height, width;
	 private AlphabetIndexer mIndexer;
	 int check=0;
	SharedPreferences sp;
	 List<String> Group_Id =new ArrayList<String>();
	 StringBuilder strBuilder = new StringBuilder();
	 EditText groupinvite_edittext;
	/*public Get_Convercation_Adapter(Context context, ArrayList<Get_convercation_model> leads_getArray1,int w,int h, SharedPreferences preferences) {
		Member_List_getArray = leads_getArray1;
		l_Inflater = LayoutInflater.from(context);
		classreference = context;
		height=h;
		width=w;
		sp=preferences;
		this.check=check;
	}*/

	public Get_GroupInviteList_Adapter(Context context, ArrayList<Get_ConteactList_Model> leads_getArray1,int w,int h, SharedPreferences preferences,List<String> Group_Id,EditText groupinvite_edittext) {
		// TODO Auto-generated constructor stub
		Member_List_getArray = leads_getArray1;
		l_Inflater = LayoutInflater.from(context);
		classreference = context ;
		height=h;
		width=w;
		sp=preferences;
		this.Group_Id=Group_Id;
		this.groupinvite_edittext=groupinvite_edittext;
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
		final ViewHolder holder ;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.groupinvitelist_item, null);
			holder = new ViewHolder();

			
			holder.groupinvite_text = (TextView) convertView
					.findViewById(R.id.groupinvite_text);
			holder.groupinvite_text1 = (TextView) convertView
					.findViewById(R.id.groupinvite_text1);
			holder.groupinvite_image = (CheckBox) convertView
					.findViewById(R.id.groupinvite_image);
//			Typeface font = Typeface.createFromAsset(classreference.getAssets(), "NesobriteLt-Regular.ttf");
//			Typeface font2 = Typeface.createFromAsset(classreference.getAssets(), "NesobriteRg-Bold.ttf");
//			holder.member_list_email.setTypeface(font);
			
			
			holder.leads_quate_lay=(LinearLayout)convertView.findViewById(R.id.groupInvite_lay);
			
			LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,height/10);
//			params3.setMargins(width/12, 0, width/12, 0);
			holder.leads_quate_lay.setLayoutParams(params3);

			convertView.setTag(holder);
		} 
		else
			holder=(ViewHolder)convertView.getTag();

		try {

			
				holder.groupinvite_text.setText(""+Member_List_getArray.get(position).getName());
				holder.groupinvite_text1.setText(""+Member_List_getArray.get(position).getSignupname());
//				holder.groupinvite_image.setText(""+Member_List_getArray.get(position).getChat());
				
			holder.leads_quate_lay.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(!holder.groupinvite_image.isChecked()){
						holder.groupinvite_image.setChecked(true);
						check=1;
					holder.groupinvite_image.setButtonDrawable(R.drawable.check_icon);
					Log.d("To_id", Member_List_getArray.get(position).getUserid());
					
					Group_inviteList.Group_Id.add(Member_List_getArray.get(position).getUserid());
					Group_inviteList.Group_name.add(Member_List_getArray.get(position).getName());
					String show_text=Group_inviteList.Group_name.toString().replace("[", "");
					String str1=show_text.replace("]", "");
					
					if(str1.length()==0){
						groupinvite_edittext.setText("Who would you like to message?");
					}else{
						groupinvite_edittext.setText(str1);
					}
					
					}else{
						check=0;
						holder.groupinvite_image.setChecked(false);
					
						for(int i=0;i<Group_inviteList.Group_Id.size();i++){
							if(Group_inviteList.Group_Id.get(i).equalsIgnoreCase(Member_List_getArray.get(position).getUserid())){
								Log.d("delete id", Group_inviteList.Group_Id.get(i));
								Group_inviteList.Group_Id.remove(i);
								Group_inviteList.Group_name.remove(i);
								String show_text=Group_inviteList.Group_name.toString().replace("[", "");
								String str1=show_text.replace("]", "");
								
								if(str1.length()==0){
									groupinvite_edittext.setText("Who would you like to message?");
								}else{
									groupinvite_edittext.setText(str1);
								}
							}
						}
						
						holder.groupinvite_image.setButtonDrawable(R.drawable.circle_icon);
							
					}
//					Group_inviteList.Group_Id = strBuilder.toString();
					/*Chat_Room.Chat_List_arrayList=new ArrayList<Get_Chat_model>();
					SharedPreferences.Editor editor=sp.edit();
					editor.putString("Chat_Type",""+Member_List_getArray.get(position).getChat_Type());
					editor.putString("To_id",""+Member_List_getArray.get(position).getSenderid());
					editor.putString("To_name",""+Member_List_getArray.get(position).getSendername());
//					editor.putString("To_number",""+Member_List_getArray.get(position).getPhone());
					
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
		public TextView groupinvite_text,groupinvite_text1;
		
		public CheckBox groupinvite_image;
		public LinearLayout leads_quate_lay,set;
	}

	public void setIndexer(AlphabetIndexer Indexer) {
		// TODO Auto-generated method stub
		 mIndexer = Indexer;
	}

	

}


