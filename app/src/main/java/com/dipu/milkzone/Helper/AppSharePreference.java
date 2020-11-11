package com.dipu.milkzone.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharePreference {

    private static final String FILE_NAME = "Milk_raw_data";
    private static final String KEY_PATIENT_APPOINTMENT_ID = "p_appointment_id";

    private static final String KEY_PATIENT_APPOINTMENT_DATE = "p_appointment_date";
    private static final String KEY_USER_ID = "u_id";
    private static final String KEY_USER_NAME = "u_name";
    private static final String KEY_ADMIN_ID = "a_id";
    private static final String KEY_ADMIN_NAME = "a_name";

    private static final String KEY_DOCTOR_APPOINTMENT_PATIENT_ID = "d_appoint_patient_id";
    private static final String KEY_DOCTOR_APPOINTMENT_ID = "d_appointment_id";
    private static final String KEY_DOCTOR_APPOINTMENT_DATE = "d_appointment_date";
    private static final String KEY_DOCTOR_APPOINTMENT_INTime = "d_appointment_inTime";
    private static final String KEY_DOCTOR_APPOINTMENT_INTime_AM_PM = "d_appointment_inTime_am_pm";

    private static final String KEY_FCM_TOKEN = "key_fcm_token";

    public static void saveDoctorAppointIntimeAMPM(Context context, String value) {
        saveString(context, KEY_DOCTOR_APPOINTMENT_INTime_AM_PM, value);

    }

    public static String getDoctorAppointIntimeAMPM(Context context) {
        return getString(context, KEY_DOCTOR_APPOINTMENT_INTime_AM_PM);

    }

    public static void saveDoctorAppointmentIntime(Context context, String value) {
        saveString(context, KEY_DOCTOR_APPOINTMENT_INTime, value);

    }

    public static String getDoctorAppointmentIntime(Context context) {
        return getString(context, KEY_DOCTOR_APPOINTMENT_INTime);

    }

    public static void saveDoctorAppointmentDate(Context context, String value) {
        saveString(context, KEY_DOCTOR_APPOINTMENT_DATE, value);

    }

    public static String getDoctorAppointmentDate(Context context) {
        return getString(context, KEY_DOCTOR_APPOINTMENT_DATE);

    }

    public static void saveDoctorAppointmentCode(Context context, String value) {
        saveString(context, KEY_DOCTOR_APPOINTMENT_ID, value);

    }

    public static String getDoctorAppointmentCode(Context context) {
        return getString(context, KEY_DOCTOR_APPOINTMENT_ID);

    }


    public static void saveDoctorAppointmentPatientId(Context context, String value) {
        saveString(context, KEY_DOCTOR_APPOINTMENT_PATIENT_ID, value);

    }

    public static String getDoctorAppointmentPatientId(Context context) {
        return getString(context, KEY_DOCTOR_APPOINTMENT_PATIENT_ID);

    }


    public static void clearAllData(Context context) {
        saveUserName(context, "");
//        saveFireBaseToken(context,"");
        saveAppointmentDate(context, "");
        saveAppointmentID(context, "");
        saveUserID(context, "");
        saveAdminID(context, "");
        saveDoctorAppointmentPatientId(context, "");
        saveDoctorAppointmentCode(context, "");
        saveDoctorAppointmentDate(context, "");
        saveDoctorAppointmentIntime(context, "");
        saveDoctorAppointIntimeAMPM(context, "");

    }


    public static void saveUserName(Context context, String value) {
        saveString(context, KEY_USER_NAME, value);

    }

    public static String getUserName(Context context) {
        return getString(context, KEY_USER_NAME);

    }

    public static void saveFireBaseToken(Context context, String value) {
        saveString(context, KEY_FCM_TOKEN, value);

    }

    public static String getFireBaseToken(Context context) {
        return getString(context, KEY_FCM_TOKEN);

    }

    public static void saveAppointmentDate(Context context, String value) {
        saveString(context, KEY_PATIENT_APPOINTMENT_DATE, value);

    }

    public static String getAppointmentDate(Context context) {
        return getString(context, KEY_PATIENT_APPOINTMENT_DATE);

    }

    public static void saveAppointmentID(Context context, String value) {
        saveString(context, KEY_PATIENT_APPOINTMENT_ID, value);

    }

    public static String getAppointmentID(Context context) {
        return getString(context, KEY_PATIENT_APPOINTMENT_ID);

    }

    public static void saveUserID(Context context, String value) {
        saveString(context, KEY_USER_ID, value);

    }

    public static void saveAdminID(Context context, String value) {
        saveString(context, KEY_ADMIN_ID, value);

    }

    public static String getUserID(Context context) {
        return getString(context, KEY_USER_ID);

    }

    public static String getAdminID(Context context) {
        return getString(context, KEY_ADMIN_ID);

    }

    private static String getString(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");

    }

    private static void saveString(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

  /*  SharedPreferences preferences = getSharedPreferences(Key.APP_PREFERENCE, MODE_PRIVATE);
    boolean isFirstRun = preferences.getBoolean(Key.KEY_IS_FIRST_TIME, false);*/
}
