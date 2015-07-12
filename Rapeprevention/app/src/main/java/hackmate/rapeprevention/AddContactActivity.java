package hackmate.rapeprevention;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackmate.rapeprevention.Models.ContactInfo;


public class AddContactActivity extends ActionBarActivity {

    @Bind(R.id.invite_photo1) ImageView addContact1;
    @Bind(R.id.invite_photo2) ImageView addContact2;
    @Bind(R.id.invite_photo3) ImageView addContact3;
    @Bind(R.id.invite_photo4) ImageView addContact4;
    @Bind(R.id.invite_photo5) ImageView addContact5;
    @Bind(R.id.invite_photo6) ImageView addContact6;

    ArrayList<ContactInfo> contactsInfo = new ArrayList<ContactInfo>();
    ArrayList<TextView> names = new ArrayList<TextView>();
    ArrayList<ImageView> photos = new ArrayList<ImageView>();
    int photo_id;

    static final int PICK_CONTACT_REQUEST = 1;  // The request code
    private static final int CONTACT_PICKER_RESULT = 1001;
    private static final String DEBUG_TAG = "debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(DEBUG_TAG, "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);

        names.add((TextView) findViewById(R.id.invite_name1));
        names.add((TextView) findViewById(R.id.invite_name2));
        names.add((TextView) findViewById(R.id.invite_name3));
        names.add((TextView) findViewById(R.id.invite_name4));
        names.add((TextView) findViewById(R.id.invite_name5));
        names.add((TextView) findViewById(R.id.invite_name6));

        photos.add((ImageView) findViewById(R.id.invite_photo1));
        photos.add((ImageView) findViewById(R.id.invite_photo2));
        photos.add((ImageView) findViewById(R.id.invite_photo3));
        photos.add((ImageView) findViewById(R.id.invite_photo4));
        photos.add((ImageView) findViewById(R.id.invite_photo5));
        photos.add((ImageView) findViewById(R.id.invite_photo6));

    }

    ImageView.OnClickListener add_contact = new ImageView.OnClickListener() {
        public void onClick(View v) {
            Log.w(DEBUG_TAG, "clicked");
            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                    Contacts.CONTENT_URI);
            startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.invite_photo1) void onGotoBtnClick1() {
        Log.w(DEBUG_TAG, "clicked");
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        photo_id = 0;
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    @OnClick(R.id.invite_photo2) void onGotoBtnClick2() {
        Log.w(DEBUG_TAG, "clicked");
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        photo_id = 1;
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    @OnClick(R.id.invite_photo3) void onGotoBtnClick3() {
        Log.w(DEBUG_TAG, "clicked");
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        photo_id = 2;
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    @OnClick(R.id.invite_photo4) void onGotoBtnClick4() {
        Log.w(DEBUG_TAG, "clicked");
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        photo_id = 3;
        contactPickerIntent.putExtra("photo_id", 4);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    @OnClick(R.id.invite_photo5) void onGotoBtnClick5() {
        Log.w(DEBUG_TAG, "clicked");
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        photo_id = 4;
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    @OnClick(R.id.invite_photo6) void onGotoBtnClick6() {
        Log.w(DEBUG_TAG, "clicked");
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        photo_id = 5;
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Log.w(DEBUG_TAG, "enter on acitivity result");
            Bundle b = getIntent().getExtras();

            Log.w(DEBUG_TAG, Integer.toString(photo_id));
            switch (requestCode) {
                case CONTACT_PICKER_RESULT:
                    Cursor cursor = null;
                    Cursor c = null;
                    String phone = "";
                    String name = "";
                    try {
                        Uri result = data.getData();
                        Log.v(DEBUG_TAG, "Got a contact result: "
                                + result.toString());

                        // get the contact id from the Uri
                        String id = result.getLastPathSegment();

                        // query for everything email
                        cursor = getContentResolver().query(Phone.CONTENT_URI,
                                null, Phone.CONTACT_ID + "=?", new String[] { id },
                                null);
                        c =  managedQuery(data.getData(), null, null, null, null);

                        if (c.moveToFirst()) {
                            Log.w(DEBUG_TAG, "display name");
                            name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                            Log.w(DEBUG_TAG, name);
                        }


                        int phoneIdx = cursor.getColumnIndex(Phone.DATA);

                        // let's just get the first email
                        if (cursor.moveToFirst()) {
                            phone = cursor.getString(phoneIdx);
                            Log.v(DEBUG_TAG, "Got phone: " + phone);
                        } else {
                            Log.w(DEBUG_TAG, "No results");
                        }

                        Cursor photoCursor=null;
                        Bitmap photoBm=null;
                        Uri photoUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(id).longValue());
                        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), photoUri);

                        photoBm = BitmapFactory.decodeStream(input);
                        ImageView myImageView = photos.get(photo_id-1);

                        myImageView.setImageBitmap(getCircleBitmap(photoBm));
                        Log.w(DEBUG_TAG, "picture!");

                        contactsInfo.add(new ContactInfo(name, myImageView, phone));

                    } catch (Exception e) {
                        Log.e(DEBUG_TAG, "Failed to get phone data", e);
                    } finally {

                        TextView nameEntry = names.get(photo_id-1);
                        Log.w(DEBUG_TAG, phone);
                        nameEntry.setText(name);
                        if (phone.length() == 0) {
                            Toast.makeText(this, "No phone number found for contact.",
                                    Toast.LENGTH_LONG).show();
                        }


                    }

                    break;
            }

        } else {
            Log.w(DEBUG_TAG, "Warning: activity result not ok");
        }
    }


    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth()*2,
                bitmap.getHeight()*2, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth()*2, bitmap.getHeight()*2);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

    private ArrayList<ContactInfo> getContactsInfo() {
        return contactsInfo;
    }


}
