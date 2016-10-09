package com.swinburne.timur.assignment3task2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcel class for Profiles
 */
public class ProfileParcel implements Parcelable {
    private String name;
    private String profession;
    private String contact;
    private String character;

    public ProfileParcel(String name, String profession, String contact, String character) {
        this.name = name;
        this.profession = profession;
        this.contact = contact;
        this.character = character;
    }

    protected ProfileParcel(Parcel in) {
        String[] data = new String[4];

        in.readStringArray(data);
        this.name = data[0];
        this.profession = data[1];
        this.contact = data[2];
        this.character = data[3];
    }

    // Getter methods
    public String getName() { return this.name; }
    public String getProfession() { return this.profession; }
    public String getContact() { return this.contact; }
    public String getCharacter() { return this.character; }


    /**
     * Default Parcel creator method, auto-generated stub
     */
    public static final Creator<ProfileParcel> CREATOR = new Creator<ProfileParcel>() {
        @Override
        public ProfileParcel createFromParcel(Parcel in) {
            return new ProfileParcel(in);
        }

        @Override
        public ProfileParcel[] newArray(int size) {
            return new ProfileParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Parcel generator code
     *
     * @param dest Destination flattened parcel
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.name,
                this.profession,
                this.contact,
                this.character
        });
    }
}
