package com.swinburne.timur.assignment5task1.address;

import android.provider.BaseColumns;

public final class AddressContract {
    private AddressContract() {}

    public static class AddressEntry implements BaseColumns {
        public static final String TABLE_NAME = "addresses";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_NUMBER = "number";
        public static final String COLUMN_NAME_EMAIL = "email";
    }
}
