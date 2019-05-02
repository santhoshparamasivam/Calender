///*
// * Decompiled with CFR 0.0.
// *
// * Could not load the following classes:
// *  android.app.Activity
// *  android.app.DatePickerDialog
// *  android.app.DatePickerDialog$OnDateSetListener
// *  android.app.Dialog
// *  android.content.Context
// *  android.os.Bundle
// *  android.util.DisplayMetrics
// *  android.view.Display
// *  android.view.Menu
// *  android.view.MenuInflater
// *  android.view.MenuItem
// *  android.view.View
// *  android.view.Window
// *  android.view.WindowManager
// *  android.webkit.WebChromeClient
// *  android.webkit.WebSettings
// *  android.webkit.WebView
// *  android.webkit.WebViewClient
// *  android.widget.DatePicker
// *  android.widget.Toast
// *  java.lang.CharSequence
// *  java.lang.Object
// *  java.lang.String
// *  java.lang.System
// *  java.util.Calendar
// */
//package com.vidhai.calender;
//
//import android.app.Activity;
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.util.DisplayMetrics;
//import android.view.Display;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.DatePicker;
//import android.widget.Toast;
//import java.util.Calendar;
//
//public class TamilDailyCalendarActivity
//extends Activity {
//    static final int DATE_DIALOG_ID;
//    Calendar cal;
//    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){
//
//        public void onDateSet(DatePicker datePicker, int n, int n2, int n3) {
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            TamilDailyCalendarActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            TamilDailyCalendarActivity.access$0(TamilDailyCalendarActivity.this, n);
//            TamilDailyCalendarActivity.access$1(TamilDailyCalendarActivity.this, n2);
//            TamilDailyCalendarActivity tamilDailyCalendarActivity = TamilDailyCalendarActivity.this;
//            Object[] arrobject = new Object[]{1 + TamilDailyCalendarActivity.this.mMonth};
//            TamilDailyCalendarActivity.access$3(tamilDailyCalendarActivity, String.format((String)"%02d", (Object[])arrobject));
//            TamilDailyCalendarActivity.access$4(TamilDailyCalendarActivity.this, n3);
//            TamilDailyCalendarActivity.this.webview.loadData("<IMG HEIGHT=\"" + displayMetrics.heightPixels + "px\"" + " WIDTH=\"" + displayMetrics.widthPixels + "px\"" + " SRC=\"http://www.tamildailycalendar.com/" + TamilDailyCalendarActivity.this.mYear + "/" + TamilDailyCalendarActivity.this.mDay + TamilDailyCalendarActivity.this.sMonth + TamilDailyCalendarActivity.this.mYear + ".jpg\" />", "text/html", "UTF-8");
//        }
//    };
//    private int mDay;
//    private int mMonth;
//    private int mYear;
//    private String sMonth;
//    WebView webview;
//
//    static /* synthetic */ void access$0(TamilDailyCalendarActivity tamilDailyCalendarActivity, int n) {
//        tamilDailyCalendarActivity.mYear = n;
//    }
//
//    static /* synthetic */ void access$1(TamilDailyCalendarActivity tamilDailyCalendarActivity, int n) {
//        tamilDailyCalendarActivity.mMonth = n;
//    }
//
//    static /* synthetic */ void access$3(TamilDailyCalendarActivity tamilDailyCalendarActivity, String string2) {
//        tamilDailyCalendarActivity.sMonth = string2;
//    }
//
//    static /* synthetic */ void access$4(TamilDailyCalendarActivity tamilDailyCalendarActivity, int n) {
//        tamilDailyCalendarActivity.mDay = n;
//    }
//
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        this.webview = new WebView((Context)this);
//        this.cal = Calendar.getInstance();
//        this.mYear = this.cal.get(1);
//        this.mMonth = this.cal.get(2);
//        Object[] arrobject = new Object[]{1 + this.mMonth};
//        this.sMonth = String.format((String)"%02d", (Object[])arrobject);
//        this.mDay = this.cal.get(5);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        this.getWindow().requestFeature(2);
//        this.webview.setWebChromeClient(new WebChromeClient((Activity)this){
//            private final /* synthetic */ Activity val$activity;
//            {
//                this.val$activity = activity;
//            }
//
//            public void onProgressChanged(WebView webView, int n) {
//                this.val$activity.setProgress(n * 100);
//            }
//        });
//        this.webview.setWebViewClient(new WebViewClient((Activity)this){
//            private final /* synthetic */ Activity val$activity;
//            {
//                this.val$activity = activity;
//            }
//
//            public void onReceivedError(WebView webView, int n, String string2, String string3) {
//                Toast.makeText((Context)this.val$activity, (CharSequence)("Oh, no! " + string2), (int)0).show();
//            }
//        });
//        this.setContentView((View)this.webview);
//        this.webview.getSettings().setBuiltInZoomControls(true);
//        this.webview.loadData("<IMG HEIGHT=\"" + displayMetrics.heightPixels + "px\"" + " WIDTH=\"" + displayMetrics.widthPixels + "px\"" + " SRC=\"http://www.tamildailycalendar.com/" + this.mYear + "/" + this.mDay + this.sMonth + this.mYear + ".jpg\" />", "text/html", "UTF-8");
//    }
//
//    protected Dialog onCreateDialog(int n) {
//        switch (n) {
//            default: {
//                return null;
//            }
//            case 0:
//        }
//        return new DatePickerDialog((Context)this, this.mDateSetListener, this.mYear, this.mMonth, this.mDay);
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu2) {
//        this.getMenuInflater().inflate(main_menu, menu2);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//            default: {
//                return true;
//            }
//            case calendar: {
//                this.showDialog(0);
//                return true;
//            }
//            case today: {
//                DisplayMetrics displayMetrics = new DisplayMetrics();
//                this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//                this.mYear = this.cal.get(1);
//                this.mMonth = this.cal.get(2);
//                Object[] arrobject = new Object[]{1 + this.mMonth};
//                this.sMonth = String.format((String)"%02d", (Object[])arrobject);
//                this.mDay = this.cal.get(5);
//                this.webview.loadData("<IMG HEIGHT=\"" + displayMetrics.heightPixels + "px\"" + " WIDTH=\"" + displayMetrics.widthPixels + "px\"" + " SRC=\"http://www.tamildailycalendar.com/" + this.mYear + "/" + this.mDay + this.sMonth + this.mYear + ".jpg\" />", "text/html", "UTF-8");
//                return true;
//            }
//            case 2131099650:
//        }
//        System.exit((int)0);
//        return true;
//    }
//
//}
//
