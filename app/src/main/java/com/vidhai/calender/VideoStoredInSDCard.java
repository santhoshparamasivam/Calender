package com.vidhai.calender;
import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class VideoStoredInSDCard extends Activity {
    private Cursor videocursor;
    private int video_column_index;
    ListView videolist;
    private RewardedVideoAd mRewardedVideoAd;
    int count;
    String[] thumbColumns = { MediaStore.Video.Thumbnails.DATA,
            MediaStore.Video.Thumbnails.VIDEO_ID };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        isStoragePermissionGranted();

        MobileAds.initialize(this, ("ca-app-pub-3643602219143275~4684936349"));
    }

    public void isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        else {
            Log.v(TAG,"Permission is granted");
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            init_phone_video_grid();
        }
    }
    private void RewardVideo() {
            mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
            mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {

                @Override
                public void onRewarded(RewardItem rewardItem) {
                    Toast.makeText(VideoStoredInSDCard.this, "onRewarded! currency: " + rewardItem.getType() + "  amount: " +
                            rewardItem.getAmount(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRewardedVideoAdLeftApplication() {
                    Toast.makeText(VideoStoredInSDCard.this, "onRewardedVideoAdLeftApplication",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRewardedVideoAdClosed() {
                    Toast.makeText(VideoStoredInSDCard.this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRewardedVideoAdFailedToLoad(int errorCode) {
                    Toast.makeText(VideoStoredInSDCard.this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRewardedVideoCompleted() {
                    Toast.makeText(VideoStoredInSDCard.this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRewardedVideoAdLoaded() {
                    Toast.makeText(VideoStoredInSDCard.this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRewardedVideoAdOpened() {
                    Toast.makeText(VideoStoredInSDCard.this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRewardedVideoStarted() {
                    Toast.makeText(VideoStoredInSDCard.this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
                }
            });

            loadRewardedVideoAd();
        }

        private void loadRewardedVideoAd() {
            mRewardedVideoAd.loadAd(("ca-app-pub-3643602219143275/5051111313"),
                    new AdRequest.Builder().build());


            showRewardedVideo();
        }

        private void showRewardedVideo() {
            // make sure the ad is loaded completely before showing it
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
            }
        }



    @SuppressWarnings("deprecation")
    private void init_phone_video_grid() {
        System.gc();
        String[] proj = { MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION };
        videocursor = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                proj, null, null, null);
        count = videocursor.getCount();
        videolist = (ListView) findViewById(R.id.PhoneVideoList);
        videolist.setAdapter(new VideoAdapter(getApplicationContext()));
        videolist.setOnItemClickListener(videogridlistener);
    }

    private OnItemClickListener videogridlistener = new OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position,
                                long id) {
            System.gc();
            video_column_index = videocursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            videocursor.moveToPosition(position);
            String filename = videocursor.getString(video_column_index);
            Intent intent = new Intent(VideoStoredInSDCard.this,
                    ViewVideo.class);
            intent.putExtra("videofilename", filename);
            startActivity(intent);
        }
    };

    public class VideoAdapter extends BaseAdapter {
        private Context vContext;

        public VideoAdapter(Context c) {
            vContext = c;
        }

        public int getCount() {
            return count;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            System.gc();
            ViewHolder holder;
            String id = null;
            convertView = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(vContext).inflate(
                        R.layout.listitem, parent, false);
                holder = new ViewHolder();
                holder.txtTitle = (TextView) convertView
                        .findViewById(R.id.txtTitle);
                holder.txtSize = (TextView) convertView
                        .findViewById(R.id.txtSize);
                holder.thumbImage = (ImageView) convertView
                        .findViewById(R.id.imgIcon);

                video_column_index = videocursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                videocursor.moveToPosition(position);
                id = videocursor.getString(video_column_index);
                video_column_index = videocursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);

                videocursor.moveToPosition(position);
                // id += " Size(KB):" +
                // videocursor.getString(video_column_index);
                holder.txtTitle.setText(id);
String s=videocursor.getString(video_column_index);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(s));

                if (seconds<=60){
                    holder.txtSize.setText(seconds+"sec");
                }else if(seconds>=60){
                    long minutes = TimeUnit.SECONDS.toMinutes(seconds);

                    Log.e("seconds",seconds+"");
                    Log.e("minutes",minutes+"");
                    holder.txtSize.setText(minutes+"mins"+seconds);
                }

                String[] proj = { MediaStore.Video.Media._ID,
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DATA };
                @SuppressWarnings("deprecation")
                Cursor cursor = managedQuery(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, proj,
                        MediaStore.Video.Media.DISPLAY_NAME + "=?",
                        new String[] { id }, null);
                cursor.moveToFirst();
                long ids = cursor.getLong(cursor
                        .getColumnIndex(MediaStore.Video.Media._ID));

                ContentResolver crThumb = getContentResolver();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 1;
                Bitmap curThumb = MediaStore.Video.Thumbnails.getThumbnail(
                        crThumb, ids, MediaStore.Video.Thumbnails.MICRO_KIND,
                        options);
                holder.thumbImage.setImageBitmap(curThumb);
                curThumb = null;

            }
            return convertView;
        }
    }

    static class ViewHolder {

        TextView txtTitle;
        TextView txtSize;
        ImageView thumbImage;
    }


}