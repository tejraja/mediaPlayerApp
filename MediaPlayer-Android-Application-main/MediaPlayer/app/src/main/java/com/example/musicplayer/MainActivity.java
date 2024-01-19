// Import necessary packages for the main activity
package com.example.musicplayer;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Define the main activity class extending AppCompatActivity
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout defined in activity_main.xml
        setContentView(R.layout.activity_main);

        // Initialize buttons by finding their references in the layout
        Button btn1 = findViewById(R.id.button2);  // Music button
        Button abu = findViewById(R.id.button);    // About Us button
        Button btn2 = findViewById(R.id.button3);  // Video button

        // Set click listeners for each button to handle button clicks
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmain();  // Call the openmain method when the Music button is clicked
            }
        });

        abu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openabout();  // Call the openabout method when the About Us button is clicked
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmainv();  // Call the openmainv method when the Video button is clicked
            }
        });
    }

    // Method to open the MainActivity2 (Music Player) activity
    public void openmain() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    // Method to open the MainActivity3 (About Us) activity
    public void openabout() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    // Method to open the MainActivity4 (Video Player) activity
    public void openmainv() {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }
}
