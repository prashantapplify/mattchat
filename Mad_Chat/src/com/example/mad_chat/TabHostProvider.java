package com.example.mad_chat;

import android.app.Activity;

public abstract class TabHostProvider {
	public Activity context;

	public TabHostProvider(Activity context) {
		this.context = context;
	}       
	public abstract TabView getTabHost(String category);
 }