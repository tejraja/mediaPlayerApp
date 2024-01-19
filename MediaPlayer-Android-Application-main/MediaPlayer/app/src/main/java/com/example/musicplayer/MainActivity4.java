// Import necessary packages for the Video Player activity
package com.example.musicplayer;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import java.util.ArrayList;

// Define the MainActivity4 class extending AppCompatActivity
public class MainActivity4 extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    // Declare UI elements and variables
    VideoView vw;
    ArrayList<Integer> videolist = new ArrayList<>();
    int currvideo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout defined in activity_main4.xml
        setContentView(R.layout.activity_main4);

        // Find reference to VideoView in the layout
        vw = findViewById(R.id.vidvw);
        vw.setMediaController(new MediaController(this));
        vw.setOnCompletionListener(this);

        // Add video resources to the videolist
        videolist.add(R.raw.video_one);
        videolist.add(R.raw.video_two);
        videolist.add(R.raw.video_three);
        // Set the first video in the list to play
        setVideo(videolist.get(0));
    }

    // Method to set the video source and start playing
    public void setVideo(int id) {
        String uriPath = "android.resource://" + getPackageName() + "/" + id;
        Uri uri = Uri.parse(uriPath);
        vw.setVideoURI(uri);
        vw.start();
    }

    // Method called when video playback is completed
    public void onCompletion(MediaPlayer mediaPlayer) {
        // Create an AlertDialog to ask if the user wants to replay or play the next video
        AlertDialog.Builder obj = new AlertDialog.Builder(this);
        obj.setTitle("Playback Finished!");
        obj.setIcon(R.mipmap.ic_launcher);
        MyListener m = new MyListener();
        obj.setPositiveButton("Replay", m);
        obj.setNegativeButton("Next", m);
        obj.setMessage("Want to replay or play next video?");
        obj.show();
    }

    // Inner class to handle AlertDialog button clicks
    class MyListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            // If "Replay" is clicked, replay the current video
            if (which == DialogInterface.BUTTON_POSITIVE) {
                vw.seekTo(0);
                vw.start();
            }
            // If "Next" is clicked, play the next video in the list
            else {
                ++currvideo;
                if (currvideo == videolist.size())
                    currvideo = 0;
                setVideo(videolist.get(currvideo));
            }
        }
    }
}
