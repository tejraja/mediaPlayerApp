// Import necessary packages for the Music Player activity
package com.example.musicplayer;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

// Define the MainActivity2 class extending AppCompatActivity
public class MainActivity2 extends AppCompatActivity {

    // Declare UI elements and variables
    ImageButton btnPlay, btnFwd, btnBwd;
    ImageView img;
    SeekBar seekbar;
    Handler mHandler = new Handler();
    Runnable mRunnable;
    MediaPlayer mediaPlayer;

    // Method called when the activity is created
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout defined in activity_main2.xml
        setContentView(R.layout.activity_main2);

        // Find references to UI elements in the layout
        img = findViewById(R.id.img);
        btnPlay = findViewById(R.id.play);
        btnFwd = findViewById(R.id.fwd);
        btnBwd = findViewById(R.id.bwd);

        // Set initial images for buttons and image view
        btnPlay.setImageResource(R.drawable.play);
        btnFwd.setImageResource(R.drawable.forward);
        btnBwd.setImageResource(R.drawable.backward);
        img.setImageResource(R.drawable.music);

        // Create a MediaPlayer instance and set the audio source
        mediaPlayer = MediaPlayer.create(this, R.raw.song3);

        // Find reference to the SeekBar in the layout
        seekbar = findViewById(R.id.seekBar);

        // Set click listener for the Play/Pause button
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the MediaPlayer is not playing, start playing and update UI
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    getAudioStats();
                    btnPlay.setImageResource(R.drawable.pause);
                    updateSeekBar();
                }
                // If MediaPlayer is playing, pause and update UI
                else {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                }
            }
        });

        // Set touch listener for the SeekBar to handle seeking
        seekbar.setOnTouchListener((v, event) -> {
            SeekBar s = (SeekBar) v;
            int position = (mediaPlayer.getDuration() / 100) * s.getProgress();
            mediaPlayer.seekTo(position);
            return false;
        });

        // Set click listener for the Forward button to skip 10 seconds
        btnFwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.getDuration() > mediaPlayer.getCurrentPosition() + 10000) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 10000);
                    updateSeekBar();
                }
            }
        });

        // Set click listener for the Backward button to rewind 10 seconds
        btnBwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.getCurrentPosition() > 10000) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000);
                    updateSeekBar();
                    getAudioStats();
                }
            }
        });
    }

    // Method to update the SeekBar progress
    private void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            seekbar.setProgress((int) ((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration() * 100));
            mHandler.postDelayed(updater, 1000);
        }
    }

    // Method to get audio duration and remaining time
    protected void getAudioStats() {
        int duration = mediaPlayer.getDuration() / 1000;
        int due = (mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition()) / 1000;
    }

    // Runnable for updating the SeekBar progress
    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
        }
    };

    // Method to stop playing the audio
    private void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(this, "Stop Playing", Toast.LENGTH_SHORT).show();
            if (mHandler != null) {
                mHandler.removeCallbacks(mRunnable);
            }
        }
    }
}
