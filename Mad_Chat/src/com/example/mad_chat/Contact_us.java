package com.example.mad_chat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.teamhood.util.ImageHelper;


public class Contact_us extends Activity implements OnClickListener{

	ImageView contactUs_back,contactUs_image1,contactUs_image2,contactUs_image3;
	EditText contactUs_edit;
	LinearLayout contactUs_sendFeedback,contactUs_mid_lay;
	Context ctx;
	SharedPreferences sp;
	InputMethodManager imm;
	Bitmap bitmap = null;
	private static final int PICK_IMAGE = 1;
	private static final int PICK_IMAGE1 = 1;
	private static final int PICK_IMAGE2 = 1;
	String filePath="",filePath1="",filePath2="";
	private int h,w;
	ImageHelper yeshelp = new ImageHelper();
	int click_image=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_us);
		
		ctx=Contact_us.this;
		sp = this.getSharedPreferences("ChatApp", MODE_PRIVATE);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		w = display.getWidth();    
		h = display.getHeight();
		
		Typeface font = Typeface.createFromAsset(getAssets(), "helveticaltstd_bold.otf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "helveticaltstd_roman.otf");
		Typeface font3 = Typeface.createFromAsset(getAssets(), "helveticaltstd_light.otf");
		contactUs_back=(ImageView)findViewById(R.id.contactUs_back);
		contactUs_back.setOnClickListener(this);
		contactUs_edit=(EditText)findViewById(R.id.contactUs_edit);
		contactUs_image1=(ImageView)findViewById(R.id.contactUs_image1);
		contactUs_image1.setOnClickListener(this);
		contactUs_image2=(ImageView)findViewById(R.id.contactUs_image2);
		contactUs_image2.setOnClickListener(this);
		contactUs_image3=(ImageView)findViewById(R.id.contactUs_image3);
		contactUs_image3.setOnClickListener(this);
		contactUs_sendFeedback=(LinearLayout)findViewById(R.id.contactUs_sendFeedback);
		contactUs_sendFeedback.setOnClickListener(this);
		contactUs_mid_lay=(LinearLayout)findViewById(R.id.contactUs_mid_lay);
		contactUs_mid_lay.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.contactUs_back:
			if(sp.getString("Home_page", "").equals("Home")){
//				Intent mainIntent = new Intent(Contact_us.this,Home_page.class);
//				startActivity(mainIntent); 
//				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
				}
			else{
//				Intent mainIntent = new Intent(Contact_us.this,Contacts.class);
//				startActivity(mainIntent); 
//				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
			}
			break;
			
		case R.id.contactUs_image1:
			try {
				click_image=1;
				bitmap = null;
				Intent gintent = new Intent();
				gintent.setType("image/*");
				gintent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(
						Intent.createChooser(gintent, "Select Picture"),
						PICK_IMAGE);

			} catch (Exception e) {
				bitmap = null;
				Toast.makeText(getApplicationContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();

			}
			break;
			
		case R.id.contactUs_image2:
			try {
				click_image=2;
				bitmap = null;
				Intent gintent = new Intent();
				gintent.setType("image/*");
				gintent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(
						Intent.createChooser(gintent, "Select Picture"),
						PICK_IMAGE1);

			} catch (Exception e) {
				bitmap = null;
				Toast.makeText(getApplicationContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();

			}
			break;
			
		case R.id.contactUs_image3:
			try {
				click_image=3;
				bitmap = null;
				Intent gintent = new Intent();
				gintent.setType("image/*");
				gintent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(
						Intent.createChooser(gintent, "Select Picture"),
						PICK_IMAGE2);

			} catch (Exception e) {
				bitmap = null;
				Toast.makeText(getApplicationContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();

			}
			break;
			
		case R.id.contactUs_sendFeedback:
			if(contactUs_edit.getText().toString().trim().length()>0){
			Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
			  emailIntent.putExtra(Intent.EXTRA_EMAIL, "");
			    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test Subject");
			    emailIntent.putExtra(Intent.EXTRA_TEXT, 
			    		contactUs_edit.getText().toString()); 
			    emailIntent.setType("text/plain");
			    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+filePath));
			    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+filePath1));
			    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+filePath2));
			    ArrayList<String> fileList = new ArrayList<String>();
	            fileList.add(filePath);
	            fileList.add(filePath1);
	            fileList.add(filePath2);
	            ArrayList<Uri> uris = new ArrayList<Uri>();
	            //convert from paths to Android friendly Parcelable Uri's

	            for (int i=0;i<fileList.size();i++)
	            {
	                File fileIn = new File(fileList.get(i));
	                Uri u = Uri.fromFile(fileIn);
	                uris.add(u);
	            }
	            emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
			    final PackageManager pm = this.getPackageManager();
			    final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
			    ResolveInfo best = null;
			    for(final ResolveInfo info : matches)
			        if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
			            best = info;
			    if (best != null)
			        emailIntent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
			    this.startActivity(emailIntent);
			}else{
				Toast.makeText(getApplicationContext(), "Please describe your problem.",
						Toast.LENGTH_LONG).show();
			}
			break;
			
		case R.id.contactUs_mid_lay:
			imm.hideSoftInputFromWindow(contactUs_mid_lay.getWindowToken(), 0);
			break;

		default:
			break;
		}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Uri selectedImageUri = null;
		switch (requestCode) {
		case PICK_IMAGE:
			if(click_image==1){
				click_image=0;
				filePath = null;
			if (resultCode == Activity.RESULT_OK) {
				selectedImageUri = data.getData();
				filePath = getPath(selectedImageUri);
				if (filePath != null) {
					decodeFile(filePath);

//					Bitmap p = yeshelp.getRoundedShape(bitmap,w,h);
					contactUs_image1.setImageBitmap(bitmap);
				} else {
					bitmap = null;
				}
			}
			}else if(click_image==2){
				click_image=0;
				filePath1 = null;
				if (resultCode == Activity.RESULT_OK) {
					selectedImageUri = data.getData();
					filePath1 = getPath(selectedImageUri);
					if (filePath1 != null) {
						decodeFile(filePath1);

//						Bitmap p = yeshelp.getRoundedShape(bitmap,w,h);
						contactUs_image2.setImageBitmap(bitmap);
					} else {
						bitmap = null;
					}
				}
			}
			else if(click_image==3){
				click_image=0;
				filePath2 = null;
				if (resultCode == Activity.RESULT_OK) {
					selectedImageUri = data.getData();
					filePath2 = getPath(selectedImageUri);
					if (filePath2 != null) {
						decodeFile(filePath2);

//						Bitmap p = yeshelp.getRoundedShape(bitmap,w,h);
						contactUs_image3.setImageBitmap(bitmap);
					} else {
						bitmap = null;
					}
				}
			}
			break;
		
		
		}
	}
	@SuppressWarnings("deprecation")
	public String getPath(Uri uri) {
		Cursor cursor = null;
		int column_index = 0;
		try {
			String[] projection = { MediaStore.Images.Media.DATA };
			cursor = managedQuery(uri, projection, null, null, null);
			column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return cursor.getString(column_index);
	}

	public void decodeFile(String filePath) {
		// Decode image size
		try {
			ExifInterface exif = new ExifInterface(filePath);
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);

			int angle = 0;

			if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
				angle = 90;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
				angle = 180;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
				angle = 270;
			}

			final	Matrix mat = new Matrix();
			mat.postRotate(angle);

			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, o);

			// The new size we want to scale to
			

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp < w*2 && height_tmp < h*2)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			int scale1 = 1;
			Bitmap check=null;
			try{
				int www=(int) (w/1.5);
				int hhh=(int) (h/3.5);
				// Find the correct scale value. It should be the power of 2.
				int width_tmp1 = o.outWidth, height_tmp1 = o.outHeight;
				while (true) {
					if (width_tmp1 < www*2 && height_tmp1 < hhh*2)
					{
						break;
					}
					/*else if(width_tmp1>1700 || height_tmp1>2000)
					{
						width_tmp1 /= 5;
						height_tmp1 /= 5;
						scale1 *= 5;	
					}*/
					else{
					width_tmp1 /= 2;
					height_tmp1 /= 2;
					scale1 *= 2;
					}
				}
			}
			catch(Exception e)
			{
				
			}
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale1;

			final BitmapFactory.Options o3 = new BitmapFactory.Options();
			o3.inSampleSize = scale;
			try {

				bitmap = BitmapFactory.decodeFile(filePath, o2);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
						bitmap.getHeight(), mat, true);
				
				

			} catch (Exception e) {
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}

	}

}
