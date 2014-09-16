package com.chatapp.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

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

import com.example.mad_chat.R;

public class Get_Chat_Adapter extends BaseAdapter {
	public static ArrayList<Get_Chat_model> Member_List_getArray;
	Context classreference;
	private LayoutInflater l_Inflater;
	int height, width;
	 private AlphabetIndexer mIndexer;
	 String status;
	SharedPreferences sp;
	InputMethodManager imm;
	int i=0;
	LinearLayout hide_menu;
	String timeString;
	public Get_Chat_Adapter(Context context, ArrayList<Get_Chat_model> leads_getArray1,int w,int h, SharedPreferences preferences,String status, InputMethodManager imm, LinearLayout hide_menu) {
		Member_List_getArray = leads_getArray1;
		l_Inflater = LayoutInflater.from(context);
		classreference = context;
		height=h;
		width=w;
		sp=preferences;
		this.status=status;
		this.imm=imm;
		this.hide_menu=hide_menu;
	}

	public int getCount() {
	
		Log.d("leads_getArray", Member_List_getArray.size()+"");
		return Member_List_getArray.size();
	}

	public Object getItem(int position) {
		
		Log.d("getItem", Member_List_getArray.get(position)+"");
		return Member_List_getArray.get(position);
//		return Member_List_getArray.size();
	}

	public long getItemId(int position) {
		
		Log.d("getItemId", position+"");
		return position;
	}
	public static class ViewHolder {
		public TextView member_list_email,from_text,from_time,me_time;
		
		public ImageView image,icon_image,me_topcercal,me_topcercal1,to_topcercal,to_topcercal1;
		public LinearLayout leads_quate_lay,set,to_circle;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			Log.d("position",position+"");
			
			convertView = l_Inflater.inflate(R.layout.chat_list_item, null);
			
			holder = new ViewHolder();

			
			holder.member_list_email = (TextView) convertView
					.findViewById(R.id.me_chat);
			holder.from_text=(TextView)convertView.findViewById(R.id.from_text);
			holder.from_time=(TextView)convertView.findViewById(R.id.from_time);
			holder.me_time=(TextView)convertView.findViewById(R.id.me_time);
			/*holder.me_topcercal=(ImageView) convertView.findViewById(R.id.me_topcercal);
			holder.me_topcercal1=(ImageView)convertView.findViewById(R.id.me_topcercal1);
			holder.to_topcercal=(ImageView)convertView.findViewById(R.id.to_topcercal);
			holder.to_topcercal1=(ImageView)convertView.findViewById(R.id.to_topcercal1);*/
			
//			Typeface font = Typeface.createFromAsset(classreference.getAssets(), "NesobriteLt-Regular.ttf");
//			Typeface font2 = Typeface.createFromAsset(classreference.getAssets(), "NesobriteRg-Bold.ttf");
//			holder.member_list_email.setTypeface(font);
			
			
			holder.leads_quate_lay=(LinearLayout)convertView.findViewById(R.id.chatList_From_lay);
			holder.set=(LinearLayout)convertView.findViewById(R.id.chatList_To_lay);
//			holder.to_circle=(LinearLayout)convertView.findViewById(R.id.to_circle);
			LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
			params3.setMargins(width/32, width/32, width/32, width/32);
			
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					(width/2)+40,LayoutParams.WRAP_CONTENT);
			
			holder.member_list_email.setLayoutParams(params);
			holder.from_text.setLayoutParams(params);
			
			holder.leads_quate_lay.setLayoutParams(params3);
			holder.set.setLayoutParams(params3);  

			convertView.setTag(holder);
		} 
		else
			holder=(ViewHolder)convertView.getTag();
		
		try {

			/*if(position>0){
				Log.d("1", "1");
//				holder.me_topcercal.setVisibility(View.GONE);
//				holder.me_topcercal1.setVisibility(View.GONE);
//				holder.to_topcercal.setVisibility(View.GONE);
//				holder.to_topcercal1.setVisibility(View.GONE);
			}
			if(position==0){
				if(Member_List_getArray.get(position).getSend_id().equalsIgnoreCase(sp.getString("userid", ""))){
//					Log.d("position", position+"//"+Member_List_getArray.get(position).getSend_id()+"//"+sp.getString("userid", ""));
					Log.d("2", "2");
					holder.me_topcercal.setBackgroundResource(R.drawable.top_circle_l);
					holder.me_topcercal1.setBackgroundResource(R.drawable.top_circle_r);
//					holder.to_topcercal.setVisibility(View.GONE);
//					holder.to_topcercal1.setVisibility(View.GONE);
				}else{
//					Log.d("position", position+"//"+Member_List_getArray.get(position).getSend_id()+"//"+sp.getString("userid", ""));
					Log.d("3", "3");
//					holder.me_topcercal.setVisibility(View.GONE);
//					holder.me_topcercal1.setVisibility(View.GONE);
					holder.to_topcercal.setBackgroundResource(R.drawable.top_circle_l);
					holder.to_topcercal1.setBackgroundResource(R.drawable.top_circle_r);
					
				}
			}*/
//			if(status.equalsIgnoreCase("all_msg")){
			/*if(position==0){
				
				holder.to_circle.setVisibility(View.VISIBLE);
				
			}else{
				holder.to_circle.setVisibility(View.GONE);
			}*/
			if(Member_List_getArray.get(position).getSend_id().equalsIgnoreCase(sp.getString("userid", ""))){
				holder.set.setVisibility(View.GONE);
				holder.leads_quate_lay.setVisibility(View.VISIBLE);
				holder.member_list_email.setText(""+Member_List_getArray.get(position).getMsg());
				
				String time1=""+Member_List_getArray.get(position).getSend_time().replace("T", " ");
				String time=time1.replace(".000Z", "");
				timeString="";
		            
				 long millisecond = GMTTimeFormatter.parse(time).getTime();
		            
		            long time2= System.currentTimeMillis();
		            long removeDefrenceTime=time2-millisecond;
		            
		            int totalSeconds = (int) removeDefrenceTime / 1000;
		            int hours = totalSeconds / 3600;
		            int remainder = totalSeconds % 3600;
		            int minutes = remainder / 60;
		            int seconds = remainder % 60;
		            int days= totalSeconds / (24*60*60);

		         
		            if (hours == 0) {
		              timeString = minutes + ":" + seconds;
		            } else {
		              timeString = hours + ":" + minutes + ":" + seconds;
		            }
		           if(hours==0 && minutes==0){
		        	   if(seconds<60){
			            	timeString="few second ago";
			            }
		           }else if(hours==0 && minutes<60){
		        	   if(minutes<60){
			            	timeString=minutes+" minutes ago";
			            }
		           }else if(hours>0 && hours<24){
		        	   timeString=hours+"hours ago";
		           }
		           else if(days>0){
		        	   timeString=days+"days ago";
		           }
		           Log.d("timeString", timeString);
		           holder.me_time.setText(timeString);
			}else{
				holder.leads_quate_lay.setVisibility(View.GONE);
				holder.set.setVisibility(View.VISIBLE);
				holder.from_text.setText(""+Member_List_getArray.get(position).getMsg());
				
				String time1=""+Member_List_getArray.get(position).getSend_time().replace("T", " ");
				String time=time1.replace(".000Z", "");
				timeString="";
		            
				 long millisecond = GMTTimeFormatter.parse(time).getTime();
		            
		            long time2= System.currentTimeMillis();
		            long removeDefrenceTime=time2-millisecond;
		            
		            int totalSeconds = (int) removeDefrenceTime / 1000;
		            int hours = totalSeconds / 3600;
		            int remainder = totalSeconds % 3600;
		            int minutes = remainder / 60;
		            int seconds = remainder % 60;
		            int days= totalSeconds / (24*60*60);

		         
		            if (hours == 0) {
		              timeString = minutes + ":" + seconds;
		            } else {
		              timeString = hours + ":" + minutes + ":" + seconds;
		            }
		           if(hours==0 && minutes==0){
		        	   if(seconds<60){
			            	timeString="few second ago";
			            }
		           }else if(hours==0 && minutes<60){
		        	   if(minutes<60){
			            	timeString=minutes+" minutes ago";
			            }
		           }else if(hours>0 && hours<24){
		        	   timeString=hours+"hours ago";
		           }
		           else if(days>0){
		        	   timeString=days+"days ago";
		           }
		           Log.d("timeString_me", timeString);
		           holder.from_time.setText(timeString);
				
			}
			/*}else if(status.equalsIgnoreCase("send")){
				holder.set.setVisibility(View.GONE);
				holder.member_list_email.setText(""+Member_List_getArray.get(position).getMsg());
			}
			else if(status.equalsIgnoreCase("unread")){
				holder.leads_quate_lay.setVisibility(View.GONE);
				holder.from_text.setText(""+Member_List_getArray.get(position).getMsg());
			}*/
				
			holder.set.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					imm.hideSoftInputFromWindow(holder.leads_quate_lay.getWindowToken(), 0);
					hide_menu.setVisibility(View.GONE);
				}
			});
			holder.leads_quate_lay.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					imm.hideSoftInputFromWindow(holder.leads_quate_lay.getWindowToken(), 0);
					hide_menu.setVisibility(View.GONE);
				/*	
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

	private static SimpleDateFormat GMTTimeFormatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
		  {
		    GMTTimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		  }

	

	

}


