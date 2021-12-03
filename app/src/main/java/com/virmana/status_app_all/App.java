package com.virmana.status_app_all;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.facebook.ads.AdSettings;
import com.virmana.status_app_all.Provider.PrefManager;
import com.virmana.status_app_all.Provider.ThemeHelper;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import timber.log.Timber;

/**
 * Created by Tamim on 28/09/2017.
 */



public class App extends MultiDexApplication {
    private static App instance;

    @Override
    public void onCreate()
    {
        MultiDex.install(this);
        super.onCreate();
        instance = this;
        if (BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }
        AdSettings.addTestDevice("72b166fa-3eb4-4bfa-a840-8cc437e8c60f");
        Timber.i("Creating our Application");
        PrefManager prf= new PrefManager(getApplicationContext());

        if (prf.getString("theme").equals("dark")) {
            ThemeHelper.applyTheme("dark");
        } else if (prf.getString("theme").equals("light")){

            ThemeHelper.applyTheme("light");

        }else{
            ThemeHelper.applyTheme("default");

        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }

    public static App getInstance ()
    {
        return instance;
    }

    public static boolean hasNetwork ()
    {
        return instance.checkIfHasNetwork();
    }

    public boolean checkIfHasNetwork()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
