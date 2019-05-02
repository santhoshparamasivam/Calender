package com.vidhai.calender;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VideoViews extends AppCompatActivity {
    private EditText videoPathEditor = null;
String video;
    private Button browseVideoFileButton = null;

    private Button playVideoButton = null;

    private Button stopVideoButton = null;

    private Button pauseVideoButton = null;

    private Button continueVideoButton = null;

    private Button replayVideoButton = null;

    private VideoView playVideoView = null;

    private ProgressBar videoProgressBar = null;


    private static final int REQUEST_CODE_SELECT_VIDEO_FILE = 1;

    private static final int REQUEST_CODE_READ_EXTERNAL_PERMISSION = 2;

    private static final int UPDATE_VIDEO_PROGRESS_BAR = 3;

    private Uri videoFileUri = null;

    private Handler videoProgressHandler = null;

    private int currVideoPosition = 0;

    private boolean isVideoPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

//        String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/FEGzkFvur7I\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

//        WebView displayYoutubeVideo = (WebView) findViewById(R.id.videoView);
//        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        });
//        WebSettings webSettings = displayYoutubeVideo.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
//        VideoView videoView =(VideoView)findViewById(R.id.videoView);
//
//
//        MediaController mediaController= new MediaController(this);
//        mediaController.setAnchorView(videoView);
//
//
//        Uri uri= Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/media/1.mp4");
//
//
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        videoView.start();
        initVideoControls();
Intent s=getIntent();
        video=s.getStringExtra("videofilename");
videoPathEditor.setText(video);
        playVideoButton.setEnabled(true);
        videoPathEditor.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                int action = keyEvent.getAction();
                if(action == KeyEvent.ACTION_UP) {
                    String text = videoPathEditor.getText().toString();
                    if (text.length() > 0) {
                        playVideoButton.setEnabled(true);
                        pauseVideoButton.setEnabled(false);
                        replayVideoButton.setEnabled(false);
                    } else {
                        playVideoButton.setEnabled(false);
                        pauseVideoButton.setEnabled(false);
                        replayVideoButton.setEnabled(false);
                    }
                }
                return false;
            }
        });

        browseVideoFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int readExternalStoragePermission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);

                if(readExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
                {
                    String requirePermission[] = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(VideoViews.this, requirePermission, REQUEST_CODE_READ_EXTERNAL_PERMISSION);
                }else {
                    selectVideoFile();
                }
            }
        });

        playVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoFilePath = videoPathEditor.getText().toString();

                if(!TextUtils.isEmpty(videoFilePath))
                {
                    if(!videoFilePath.trim().toLowerCase().startsWith("http")) {

                        playVideoView.setVideoURI(videoFileUri);
                    }else
                    {
                        Uri webVideoFileUri = Uri.parse(videoFilePath.trim());

                        playVideoView.setVideoURI(webVideoFileUri);
                    }

                    playVideoView.setVisibility(View.VISIBLE);

                    videoProgressBar.setVisibility(ProgressBar.VISIBLE);

                    currVideoPosition = 0;

                    playVideoView.start();

                    playVideoButton.setEnabled(false);

                    stopVideoButton.setEnabled(true);

                    pauseVideoButton.setEnabled(true);

                    continueVideoButton.setEnabled(false);

                    replayVideoButton.setEnabled(true);
                }

            }
        });

        stopVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playVideoView.stopPlayback();

                playVideoView.seekTo(0);

                playVideoButton.setEnabled(true);

                stopVideoButton.setEnabled(false);

                pauseVideoButton.setEnabled(false);

                continueVideoButton.setEnabled(false);

                replayVideoButton.setEnabled(false);
            }
        });


        pauseVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                playVideoView.pause();

                isVideoPaused = true;


                currVideoPosition = playVideoView.getCurrentPosition();

                playVideoButton.setEnabled(false);

                stopVideoButton.setEnabled(true);

                pauseVideoButton.setEnabled(false);

                continueVideoButton.setEnabled(true);

                replayVideoButton.setEnabled(true);
            }
        });


        continueVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playVideoView.seekTo(currVideoPosition);

                playVideoButton.setEnabled(false);

                pauseVideoButton.setEnabled(true);

                stopVideoButton.setEnabled(true);

                continueVideoButton.setEnabled(false);

                replayVideoButton.setEnabled(true);
            }
        });

        replayVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playVideoView.resume();

                currVideoPosition = 0;

                playVideoButton.setEnabled(false);

                pauseVideoButton.setEnabled(true);

                stopVideoButton.setEnabled(true);

                continueVideoButton.setEnabled(false);

                replayVideoButton.setEnabled(true);
            }
        });
    }

    private void initVideoControls()
    {

            videoPathEditor = (EditText) findViewById(R.id.play_video_file_path_editor);
            browseVideoFileButton = (Button)findViewById(R.id.browse_video_file_button);
            playVideoButton = (Button)findViewById(R.id.play_video_start_button);
            stopVideoButton = (Button)findViewById(R.id.play_video_stop_button);
            pauseVideoButton = (Button)findViewById(R.id.play_video_pause_button);
            continueVideoButton = (Button)findViewById(R.id.play_video_continue_button);
            replayVideoButton = (Button)findViewById(R.id.play_video_replay_button);
            playVideoView = (VideoView)findViewById(R.id.play_video_view);
            videoProgressBar = (ProgressBar) findViewById(R.id.play_video_progressbar);
            videoProgressHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {

                    if(msg.what == UPDATE_VIDEO_PROGRESS_BAR)
                    {
                        int currVideoPosition = playVideoView.getCurrentPosition();

                        int videoDuration = playVideoView.getDuration();

                        int progressPercent = currVideoPosition * 100 / videoDuration;

                        videoProgressBar.setProgress(progressPercent);
                    }
                }
            };

            Thread updateProgressThread = new Thread()
            {
                @Override
                public void run() {

                    try {
                        while (true) {
                            Message msg = new Message();
                            msg.what = UPDATE_VIDEO_PROGRESS_BAR;

                            videoProgressHandler.sendMessage(msg);

                            Thread.sleep(2000);
                        }
                    }catch (InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            };
            updateProgressThread.start();
        }


    private void selectVideoFile()
    {

        Intent selectVideoIntent = new Intent(Intent.ACTION_GET_CONTENT);
            selectVideoIntent.setType("video/*");
        startActivityForResult(selectVideoIntent, REQUEST_CODE_SELECT_VIDEO_FILE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_SELECT_VIDEO_FILE)
        {

            if(resultCode==RESULT_OK)
            {
                videoFileUri = data.getData();

                String videoFileName = videoFileUri.getLastPathSegment();

                videoPathEditor.setText("You select video file is " + videoFileName);

                playVideoButton.setEnabled(true);

                pauseVideoButton.setEnabled(false);

                replayVideoButton.setEnabled(false);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE_READ_EXTERNAL_PERMISSION)
        {
            if(grantResults.length > 0)
            {
                int grantResult = grantResults[0];
                if(grantResult == PackageManager.PERMISSION_GRANTED)
                {
                    selectVideoFile();
                }else
                {
                    Toast.makeText(getApplicationContext(), "You denied read external storage permission.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void setContinueVideoAfterSeekComplete()
    {
        playVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(MediaPlayer mediaPlayer) {
                        if(isVideoPaused)
                        {
                            playVideoView.start();
                            isVideoPaused = false;
                        }
                    }
                });
            }
        });
    }
}