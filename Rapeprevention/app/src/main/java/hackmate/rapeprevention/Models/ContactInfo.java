package hackmate.rapeprevention.Models;

import android.widget.ImageView;

/**
 * Created by zli3 on 7/11/15.
 */
public class ContactInfo {

    String name;
    ImageView photo;
    String number;

    public ContactInfo(String name, ImageView photo, String number) {
        this.name = name;
        this.photo = photo;
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public ImageView getPhoto() {
        return this.photo;
    }
}
