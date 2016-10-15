package com.example.asheransari.booknew;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by asher.ansari on 10/13/2016.
 */
public class Utils {
    private Utils()
    {

    }

    public static void hideKeyboard(Activity activity, IBinder windowToken)
    {
        final InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean hasActiveNetwork(Context context)
    {
        final ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = manager.getActiveNetworkInfo();
        boolean result = info != null && info.isConnectedOrConnecting();
        Log.e(Utils.class.getName(), "Result = "+result);
        Log.e(Utils.class.getName(), "Result = "+result);
        Log.e(Utils.class.getName(), "Result = "+result);
        Log.e(Utils.class.getName(), "Result = "+result);
        Log.e(Utils.class.getName(), "Result = "+result);
        return  result;

    }

    public static void showToast(Context c, String message)
    {
        Toast.makeText(c, message, Toast.LENGTH_LONG).show();
    }
}
