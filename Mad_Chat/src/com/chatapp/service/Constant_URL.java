package com.chatapp.service;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constant_URL {
	
	public static String register_user="http://54.86.25.38:1338/register_user";
	public static String login_through_accesstoken="http://54.86.25.38:1338/login_through_accesstoken";
	public static String update_user_profile="http://54.86.25.38:1338/update_user_profile";
	public static String get_all_registered_user_through_contacts="http://54.86.25.38:1338/get_all_registered_user_through_contacts";
	public static String get_all_messages="http://54.86.25.38:1338/get_all_messages";
	public static String send_new_message="http://54.86.25.38:1338/send_new_message";
	public static String get_unread_messages="http://54.86.25.38:1338/get_unread_messages";
	public static String get_all_user_conversations_list="http://54.86.25.38:1338/get_all_user_conversations_list";
	public static String Create_new_group="http://54.86.25.38:1338/create_new_group";
	public static String send_new_message_to_group="http://54.86.25.38:1338/send_new_message_to_group";
	public static String get_unread_messages_of_group="http://54.86.25.38:1338/get_unread_messages_of_group";
	public static String get_all_messages_of_group="http://54.86.25.38:1338/get_all_messages_of_group";
	public static String delete_all_user_conversations="http://54.86.25.38:1338/delete_user_conversation";
	public static String delete_user_conversation_list="http://54.86.25.38:1338/delete_user_conversation_list";
	
	public static boolean isNetworkAvailable(Context context) {
		// TODO Auto-generated method stub
		boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;

	    try {
	    	ConnectivityManager cm = (ConnectivityManager) ((Activity)context).getSystemService(Context.CONNECTIVITY_SERVICE);
	 	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	 	    for (NetworkInfo ni : netInfo) {
	 	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	 	            if (ni.isConnected())
	 	                haveConnectedWifi = true;
	 	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	 	            if (ni.isConnected())
	 	                haveConnectedMobile = true;
	 	    }
//	 	    Log.i("network connectivity",haveConnectedWifi+"/"+haveConnectedMobile);
		} catch (Exception e) {
			// TODO: handle exception
		}   
	    return haveConnectedWifi || haveConnectedMobile;
	}
}
