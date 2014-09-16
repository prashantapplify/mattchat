package com.example.mad_chat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class More extends Activity{

	private TabHostProvider tabProvider;
	private TabView tabView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		tabProvider = new TabbarView(this);  
		tabView = tabProvider.getTabHost("main");
		tabView.setCurrentView(R.layout.more);  
		setContentView(tabView.render(2));
	}
	
	

}
