package MuhammadIlhamAkbar.jfood_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.HashMap;

/**
 * <h1>JFood-Android Program based on Object Oriented Programming<h1>
 * This SessionManager Class is used to make session
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class SessionManager {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int PRIVATE_MODE = 0;

    private static final String SHARED_PREF_NAME = "UserLoginSession";
    private static final String LOGIN_STATUS = "isLogedIn";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String NAME = "name";

    public SessionManager (Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession (int userId, String name, String email){
        editor.putInt(ID, userId);
        editor.putString(EMAIL, email);
        editor.putString(NAME, name);
        editor.putBoolean(LOGIN_STATUS, true);
        editor.apply();
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(ID, String.valueOf(sharedPreferences.getInt(ID,0)));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        return user;
    }

    public int getUserId(){
        int userId = sharedPreferences.getInt(ID, 0);
        return userId;
    }

    public String getUserEmail() {
        String userEmail = sharedPreferences.getString(EMAIL, null);
        return userEmail;
    }

    public String getUserName() {
        String userName = sharedPreferences.getString(NAME, null);
        return userName;
    }

    public boolean isLoggedIn() {
        boolean loggedIn = sharedPreferences.getBoolean(LOGIN_STATUS, false);
        return loggedIn;
    }

    public void checkLogin() {
        if (getUserEmail() == null || getUserId() > 0) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
    }

}
