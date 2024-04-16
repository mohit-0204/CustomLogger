package com.example.customlogger;// AppItem.java
import android.graphics.drawable.Drawable;

public class AppItem {
    private String appName;
    private String packageName;
    private Drawable appIcon;

    public AppItem(String appName, String packageName, Drawable appIcon) {
        this.appName = appName;
        this.packageName = packageName;
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }
}
