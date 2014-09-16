package com.chatapp.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ParseException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AlphabetIndexer;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatapp.service.Delete_all_user_conversations_list;
import com.chatapp.service.Get_all_registered_user_through_contacts;
import com.example.mad_chat.Account;
import com.example.mad_chat.Chat_Room;
import com.example.mad_chat.Home_page;
import com.example.mad_chat.Phone_number_enter;
import com.example.mad_chat.R;

public class Get_Convercation_Adapter extends BaseAdapter {
	public static ArrayList<Get_convercation_model> Member_List_getArray;
	Context classreference;
	private LayoutInflater l_Inflater;
	int height, width;
	 private AlphabetIndexer mIndexer;
	 String check;
	SharedPreferences sp;
	int check_posion=0,check_minus=0;
	String timeString;
	/*public Get_Convercation_Adapter(Context context, ArrayList<Get_convercation_model> leads_getArray1,int w,int h, SharedPreferences preferences) {
		Member_List_getArray = leads_getArray1;
		l_Inflater = LayoutInflater.from(context);
		classreference = context;
		height=h;
		width=w;
		sp=preferences;
		this.check=check;
	}*/

	public Get_Convercation_Adapter(Context ctx,
			ArrayList<Get_convercation_model> contact_List_arrayList1, int w,
			int h, SharedPreferences sp2) {
		// TODO Auto-generated constructor stub
		Member_List_getArray = contact_List_arrayList1;
		l_Inflater = LayoutInflater.from(ctx);
		classreference = ctx;
		height=h;
		width=w;
		sp=sp2;
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
			convertView = l_Inflater.inflate(R.layout.convercation_item, null);
			holder = new ViewHolder();

			
			holder.member_list_email = (TextView) convertView
					.findViewById(R.id.conv_name);
			holder.conv_msg = (TextView) convertView
					.findViewById(R.id.conv_msg);
			holder.Conversation_Delete=(ImageView)convertView.findViewById(R.id.Conversation_Delete);
			holder.Conversation_Delete_lay=(LinearLayout)convertView.findViewById(R.id.Conversation_Delete_lay);
			holder.delete_row=(LinearLayout)convertView.findViewById(R.id.delete_row);
			holder.delete_text=(TextView)convertView.findViewById(R.id.delete_text);
//			Typeface font = Typeface.createFromAsset(classreference.getAssets(), "NesobriteLt-Regular.ttf");
//			Typeface font2 = Typeface.createFromAsset(classreference.getAssets(), "NesobriteRg-Bold.ttf");
//			holder.member_list_email.setTypeface(font);
			
			
			holder.leads_quate_lay=(LinearLayout)convertView.findViewById(R.id.conv_lay);
			
			LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,height/10);
//			params3.setMargins(width/15, 0, width/15, 0);
			holder.leads_quate_lay.setLayoutParams(params3);
			

			convertView.setTag(holder);
		} 
		else
			holder=(ViewHolder)convertView.getTag();
		  timeString="";
		try {

			if(sp.getString("Conversation_Delete", "").equalsIgnoreCase("1")){
				holder.Conversation_Delete.setVisibility(View.VISIBLE);
			}
			
				holder.member_list_email.setText(""+Member_List_getArray.get(position).getSendername());
				holder.conv_msg.setText(""+Member_List_getArray.get(position).getChat());
				String time1=""+Member_List_getArray.get(position).getTime().replace("T", " ");
				String time=time1.replace(".000Z", "");
				/*final String result = "" ;
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		            Date testDate = null;
		            try {
		                testDate = sdf.parse(time);
		            } catch (ParseException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }*/
		            
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
		          
		            
		            Log.d("numberOfDays",millisecond+"//"+time2+"//"+removeDefrenceTime+"//"+timeString);
		            holder.delete_row.setVisibility(View.VISIBLE);
		            holder.delete_text.setText(timeString);
		          
				
				holder.Conversation_Delete_lay.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						check_minus=1;
						Log.d("id", Member_List_getArray.get(position).getChat_Type());
					
						/*for(int ii=0;ii<Member_List_getArray.size();ii++){
							if(ii==check_posion){
								holder.delete_row.setVisibility(View.INVISIBLE);
							}
						}*/
						if(check_posion==0){
							check_posion=1;
							holder.delete_text.setText("Delete");
							holder.delete_text.setTextColor(Color.parseColor("#FFFFFF"));
							holder.delete_row.setBackgroundColor(Color.parseColor("#DD463A"));
							holder.delete_row.setVisibility(View.VISIBLE);
						}else{
							check_posion=0;
							holder.delete_text.setText(timeString);
							holder.delete_text.setTextColor(Color.parseColor("#000000"));
							holder.delete_row.setBackgroundColor(Color.parseColor("#FFFFFF"));
//							holder.delete_row.setVisibility(View.INVISIBLE);
						}
						
						
						    
					}
				});
				
				/*else{
					holder.delete_row.setVisibility(View.INVISIBLE);
				}*/
				
				holder.delete_row.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(check_minus==1){
							check_minus=0;
						new AlertDialog.Builder(classreference)
					    .setTitle("Alert")
					    .setMessage("Are you sure you want to delete this conversation?")
					    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int which) { 
					            // continue with delete
					        	 new Delete_all_user_conversations_list(classreference, sp.getString("accesstoken", ""),Member_List_getArray.get(position).getChatid()+"",Member_List_getArray.get(position).getSenderid()+"",Member_List_getArray.get(position).getChat_Type(),sp,holder.leads_quate_lay).execute("main");
					        	 holder.delete_row.setVisibility(View.INVISIBLE);
					        	 
					        }
					     })
					    .setNegativeButton("No", new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int which) { 
					            // do nothing
					        }
					     })
					    .setIcon(android.R.drawable.ic_dialog_alert)
					     .show();
						}
					}
				});
			
			holder.leads_quate_lay.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Chat_Room.Chat_List_arrayList=new ArrayList<Get_Chat_model>();
					SharedPreferences.Editor editor=sp.edit();
					editor.putString("Chatid",""+Member_List_getArray.get(position).getChatid());
					editor.putString("Chat_Type",""+Member_List_getArray.get(position).getChat_Type());
					editor.putString("To_id",""+Member_List_getArray.get(position).getSenderid());
					editor.putString("To_name",""+Member_List_getArray.get(position).getSendername());
//					editor.putString("To_number",""+Member_List_getArray.get(position).getPhone());
					
					editor.commit();
					
					Intent leads_quate_lay=new Intent(classreference,Chat_Room.class);
					classreference.startActivity(leads_quate_lay);
//					((Activity) classreference).finish();
				}
			});
			holder.conv_msg.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Chat_Room.Chat_List_arrayList=new ArrayList<Get_Chat_model>();
					SharedPreferences.Editor editor=sp.edit();
					editor.putString("Chatid",""+Member_List_getArray.get(position).getChatid());
					editor.putString("Chat_Type",""+Member_List_getArray.get(position).getChat_Type());
					editor.putString("To_id",""+Member_List_getArray.get(position).getSenderid());
					editor.putString("To_name",""+Member_List_getArray.get(position).getSendername());
//					editor.putString("To_number",""+Member_List_getArray.get(position).getPhone());
					
					editor.commit();
					
					Intent leads_quate_lay=new Intent(classreference,Chat_Room.class);
					classreference.startActivity(leads_quate_lay);
//					((Activity) classreference).finish();
				}
			});

			

			

		} catch (Exception e) {
			Log.d("Exception", e+"");
		}


		return convertView;
	}

	public static class ViewHolder {
		public TextView member_list_email,conv_msg,delete_text;
		
		public ImageView image,icon_image,Conversation_Delete;
		public LinearLayout leads_quate_lay,set,Conversation_Delete_lay,delete_row;
	}

	public void setIndexer(AlphabetIndexer Indexer) {
		// TODO Auto-generated method stub
		 mIndexer = Indexer;
	}

	private static SimpleDateFormat GMTTimeFormatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
		  {
		    GMTTimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		  }

}

