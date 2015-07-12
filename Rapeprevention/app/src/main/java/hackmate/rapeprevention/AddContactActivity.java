package hackmate.rapeprevention;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class AddContactActivity extends ActionBarActivity {

    @Bind(R.id.add_contact) ImageButton addContact;
    @Bind(R.id.invite_photo) ImageView photo;

    static final int PICK_CONTACT_REQUEST = 1;  // The request code
    private static final int CONTACT_PICKER_RESULT = 1001;
    private static final String DEBUG_TAG = "debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(DEBUG_TAG, "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);
    }

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

    @OnClick(R.id.add_contact) void onGotoBtnClick3() {
        Log.w(DEBUG_TAG, "clicked");
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
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
//                        int nameIdx = cursor


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
                        ImageView myImageView = (ImageView) findViewById(R.id.invite_photo);
                        myImageView.setImageBitmap(photoBm);
                        Log.w(DEBUG_TAG, "picture!");


                    } catch (Exception e) {
                        Log.e(DEBUG_TAG, "Failed to get phone data", e);
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (c != null) {
                            c.close();
                        }
                        EditText phoneEntry = (EditText) findViewById(R.id.invite_phone);
                        EditText nameEntry = (EditText) findViewById(R.id.invite_name);
                        Log.w(DEBUG_TAG, phone);
                        phoneEntry.setText(phone);
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


}
