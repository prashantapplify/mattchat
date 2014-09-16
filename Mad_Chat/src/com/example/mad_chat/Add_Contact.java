package com.example.mad_chat;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Contact extends Activity implements OnClickListener
{
	
	 ArrayList < ContentProviderOperation > ops = new ArrayList < ContentProviderOperation > ();

	 EditText addContact_FirstName,addContact_lastName,addContact_addPhoneno;
	 ImageView addContact_puls,addContact_minus,addContact_back;
	 TextView addContact_addPhone,addContact_done;
	 LinearLayout addContact_minus_lay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_contact);
		
		addContact_FirstName=(EditText)findViewById(R.id.addContact_FirstName);
		addContact_lastName=(EditText)findViewById(R.id.addContact_lastName);
		addContact_puls=(ImageView)findViewById(R.id.addContact_puls);
		addContact_puls.setOnClickListener(this);
		addContact_addPhone=(TextView)findViewById(R.id.addContact_addPhone);
		addContact_addPhone.setOnClickListener(this);
		addContact_minus=(ImageView)findViewById(R.id.addContact_minus);
		addContact_minus.setOnClickListener(this);
		addContact_addPhoneno=(EditText)findViewById(R.id.addContact_addPhoneno);
		addContact_done=(TextView)findViewById(R.id.addContact_done);
		addContact_done.setOnClickListener(this);
		addContact_minus_lay=(LinearLayout)findViewById(R.id.addContact_minus_lay);
		addContact_minus_lay.setVisibility(View.GONE);
		addContact_back=(ImageView)findViewById(R.id.addContact_back);
		addContact_back.setOnClickListener(this);
		
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.addContact_puls:
			addContact_minus_lay.setVisibility(View.VISIBLE);
			break;

		case R.id.addContact_addPhone:
			addContact_minus_lay.setVisibility(View.VISIBLE);
			break;
			
		case R.id.addContact_minus:
			addContact_minus_lay.setVisibility(View.GONE);
			break;
			
		case R.id.addContact_done:
			addContact();
			break;
			
		case R.id.addContact_back:
			Intent mainIntent = new Intent(Add_Contact.this,Contacts.class);
			startActivity(mainIntent); 
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;
		default:
			break;
		}
	}
	private void addContact() {
		// TODO Auto-generated method stub
		ops.add(ContentProviderOperation.newInsert(
				 ContactsContract.RawContacts.CONTENT_URI)
				     .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
				     .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
				     .build());

				 //------------------------------------------------------ Names
				 if (addContact_FirstName != null) {
				     ops.add(ContentProviderOperation.newInsert(
				     ContactsContract.Data.CONTENT_URI)
				         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				         .withValue(ContactsContract.Data.MIMETYPE,
				     ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
				         .withValue(
				     ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
				     addContact_FirstName.getText().toString().trim()).build());
				 }
				 //------------------------------------------------------ Last Name
				 if (addContact_lastName != null) {
				     ops.add(ContentProviderOperation.newInsert(
				     ContactsContract.Data.CONTENT_URI)
				         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				         .withValue(ContactsContract.Data.MIMETYPE,
				     ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
				         .withValue(
				     ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME,
				     addContact_lastName.getText().toString().trim()).build());
				 }

				 //------------------------------------------------------ Mobile Number                     
				 if (addContact_addPhoneno != null) {
				     ops.add(ContentProviderOperation.
				     newInsert(ContactsContract.Data.CONTENT_URI)
				         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				         .withValue(ContactsContract.Data.MIMETYPE,
				     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
				         .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, addContact_addPhoneno.getText().toString().trim())
				         .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
				     ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
				         .build());
				 }

				 /*//------------------------------------------------------ Home Numbers
				 if (HomeNumber != null) {
				     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
				         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				         .withValue(ContactsContract.Data.MIMETYPE,
				     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
				         .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, HomeNumber)
				         .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
				     ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
				         .build());
				 }

				 //------------------------------------------------------ Work Numbers
				 if (WorkNumber != null) {
				     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
				         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				         .withValue(ContactsContract.Data.MIMETYPE,
				     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
				         .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, WorkNumber)
				         .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
				     ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
				         .build());
				 }

				 //------------------------------------------------------ Email
				 if (emailID != null) {
				     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
				         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				         .withValue(ContactsContract.Data.MIMETYPE,
				     ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
				         .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
				         .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
				         .build());
				 }

				 //------------------------------------------------------ Organization
				 if (!company.equals("") && !jobTitle.equals("")) {
				     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
				         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
				         .withValue(ContactsContract.Data.MIMETYPE,
				     ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
				         .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
				         .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
				         .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, jobTitle)
				         .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
				         .build());
				 }*/

				 // Asking the Contact provider to create a new contact                 
				 try {
				     getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
				     Intent mainIntent = new Intent(Add_Contact.this,Contacts.class);
						startActivity(mainIntent); 
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
						finish();
				 } catch (Exception e) {
				     e.printStackTrace();
				     Toast.makeText(Add_Contact.this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
				 } 
	}
	

}
