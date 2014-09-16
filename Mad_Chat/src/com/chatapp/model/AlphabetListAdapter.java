package com.chatapp.model;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mad_chat.Account;
import com.example.mad_chat.Contacts;
import com.example.mad_chat.Home_page;
import com.example.mad_chat.Invite_Dialog;
import com.example.mad_chat.Phone_number_enter;
import com.example.mad_chat.R;

public class AlphabetListAdapter extends BaseAdapter {

    public static abstract class Row {}
    
    public static final class Section extends Row {
        public final String text;

        public Section(String text) {
            this.text = text;
        }
    }
    
    public static final class Item extends Row {
        public final String text;
        public final String Number;
        public final String Status;
        public static Context ctx;
        public Item(String text,String Number, String Status,Context ctx) {
            this.text = text;
            this.Number=Number;
            this.Status=Status;
            this.ctx=ctx;
        }
    }
   
    
    private List<Row> rows;
    
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public int getCount() {
        return rows.size();
    }

    @Override
    public Row getItem(int position) {
        return rows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    
    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof Section) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        
        if (getItemViewType(position) == 0) { // Item
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = (LinearLayout) inflater.inflate(R.layout.row_item, parent, false);  
            }
            
            final Item item = (Item) getItem(position);
            final TextView textView = (TextView) view.findViewById(R.id.textView1);
            final ImageView exist_Contact_Image=(ImageView)view.findViewById(R.id.exist_Contact_Image);
            textView.setText(item.text);
            if(item.Status.equalsIgnoreCase("1")){
            	exist_Contact_Image.setBackgroundResource(R.drawable.existcontact);
            }else{
            	exist_Contact_Image.setBackgroundResource(R.drawable.invitecontact);
            }
                        
            exist_Contact_Image.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 if(item.Status.equalsIgnoreCase("1")){
						 
							Log.d("Text1", textView.getText().toString()+"//"+item.Number);
			            }else{
			            	Intent mainIntent = new Intent(Item.ctx,Invite_Dialog.class);
							Item.ctx.startActivity(mainIntent); 
							((Activity) Item.ctx).overridePendingTransition(0, 0);
							
			            	Log.d("Text", textView.getText().toString()+"//"+item.Number);
			            }
				
					
				}
			});
        } else { // Section
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = (LinearLayout) inflater.inflate(R.layout.row_section, parent, false);  
            }
            
            Section section = (Section) getItem(position);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            
            textView.setText(section.text);
           
        }
        
        return view;
    }

	
}
