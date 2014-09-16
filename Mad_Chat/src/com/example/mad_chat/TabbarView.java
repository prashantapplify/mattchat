package com.example.mad_chat;

import android.app.Activity;
import android.content.Intent;

public class TabbarView extends TabHostProvider 
{
	private Tab firstTab;
	private Tab secondTab;
	private Tab thirdTab;  
	
	private TabView tabView;
	

	
	public TabbarView(Activity context) {
		super(context);
		
		
		// TODO Auto-generated constructor stub
	}

	public TabView getTabHost(String category) {
		tabView = new TabView(context);
		tabView.setBackgroundID(R.drawable.topbar);
		
		try {
//			System.out.println("=======");
			firstTab = new Tab(context, "One");
//			firstTab.setIntent(new Intent(context,Mission.class));
			firstTab.setIntent(new Intent(context,Home_page.class));
			firstTab.setIcon(R.drawable.chat_icon);
			firstTab.setIconSelected(R.drawable.chat_icon_select);
					
			secondTab = new Tab(context, "Two");
			secondTab.setIntent(new Intent(context,Contacts.class));
			secondTab.setIcon(R.drawable.contact_icon);
			secondTab.setIconSelected(R.drawable.contact_icon_select); 
				
			thirdTab = new Tab(context, "Three");  
			
			thirdTab.setIntent(new Intent(context,More.class));
			thirdTab.setIcon(R.drawable.more_icon);
			thirdTab.setIconSelected(R.drawable.more_icon_select);
			
			
			
			
			/*fiveTab = new Tab(context, "Five");
			
			fiveTab.setIntent(new Intent(context,Log_Home.class));
			fiveTab.setIcon(R.drawable.log_inactive);
			fiveTab.setIconSelected(R.drawable.log_active);*/
			
			
			
			tabView.addTab(firstTab);
			tabView.addTab(secondTab);
			tabView.addTab(thirdTab);

			
			tabView.setCurrentView(R.layout.home_page);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return tabView;
	}
	
}
