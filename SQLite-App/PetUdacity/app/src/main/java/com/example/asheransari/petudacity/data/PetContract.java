package com.example.asheransari.petudacity.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by asher.ansari on 10/28/2016.
 */
public final class PetContract {
    public PetContract() {
    }


    public static final String CONTENT_AUTHORITY = "com.example.asheransari.petudacity";
    public static final String PATH_PETS = "pets";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
//         yeha pe hamari ek base URI ban gi hai magar is me abjhi table ka name nhe bataya hia
//         to ab ek complete Uri banate hai,,,

    public static final class PetEntry implements BaseColumns {
        public static final Uri CONTENT_URL = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);
        //        yeha pe hamari URI copmplete ho gai hia,,
        public final static String TABLE_NAME = "pets";
        public static String _ID = BaseColumns._ID;
        public final static String COLUMN_PET_NAME = "name";
        public final static String COLUMN_PET_BREED = "breeed";
        public final static String COLUMN_PET_GENDER = "gender";
        public final static String COLUMN_PET_WEIGHT = "weight";

        public final static int GENDER_UNKNOWN = 0;
        public final static int GENDER_MALE = 1;
        public final static int GENDER_FEMALE = 2;

        public static boolean isValidGender(int gender) {
            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
                return true;
            }
            return false;
        }


    }


}
