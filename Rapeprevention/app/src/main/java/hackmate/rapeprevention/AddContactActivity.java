package hackmate.rapeprevention;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class AddContactActivity extends ActionBarActivity {

    @Bind(R.id.add_contact) ImageButton addContact;

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
//        startActivity(new Intent(this, ReactionActivity.class));
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
//        startActivityForResult(intent, PICK_CONTACT_REQUEST);
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }
//
//
//    private void pickContact() {
//        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
//        pickContactIntent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
//
//        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CONTACT_PICKER_RESULT:
                    Cursor cursor = null;
                    String email = "";
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

                        int emailIdx = cursor.getColumnIndex(Phone.DATA);

                        // let's just get the first email
                        if (cursor.moveToFirst()) {
                            email = cursor.getString(emailIdx);
                            Log.v(DEBUG_TAG, "Got email: " + email);
                        } else {
                            Log.w(DEBUG_TAG, "No results");
                        }
                    } catch (Exception e) {
                        Log.e(DEBUG_TAG, "Failed to get email data", e);
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                        EditText emailEntry = (EditText) findViewById(R.id.invite_email);
                        emailEntry.setText(email);
                        if (email.length() == 0) {
                            Toast.makeText(this, "No email found for contact.",
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
