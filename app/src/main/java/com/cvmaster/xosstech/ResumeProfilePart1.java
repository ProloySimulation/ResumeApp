package com.cvmaster.xosstech;

import android.net.Uri;

public class ResumeProfilePart1 {
    private static String imagePath;
    public static String name;
    public static String contact_number;
    public static String email;
    public static String present_address;
    private static String facebook_id;
    private static String linkedin_id;

    public static Uri uri;

    public static void setUri(Uri uri) {
        ResumeProfilePart1.uri = uri;
    }

    public static Uri getUri() {
        return uri;
    }

    public static void setImagePath(String imagePath) {
        ResumeProfilePart1.imagePath = imagePath;
    }

    public static void setName(String name) {
        ResumeProfilePart1.name = name;
    }

    public static void setFacebook(String facebook_id) {
        ResumeProfilePart1.facebook_id = facebook_id;
    }

    public static void setLinkedin(String linkedin_id) {
        ResumeProfilePart1.linkedin_id = linkedin_id;
    }

    public static void setContact_number(String contact_number) {
        ResumeProfilePart1.contact_number = contact_number;
    }

    public static void setEmail(String email) {
        ResumeProfilePart1.email = email;
    }

    public static void setPresent_address(String present_address) {
        ResumeProfilePart1.present_address = present_address;
    }

    public static String getImagePath() {
        return imagePath;
    }

    public static String getName() {
        return name;
    }

    public static String getContact_number() {
        return contact_number;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPresent_address() {
        return present_address;
    }

    public static String getfacebook_id() {
        return facebook_id;
    }

    public static String getlinkedin_id() {
        return linkedin_id;
    }
}
