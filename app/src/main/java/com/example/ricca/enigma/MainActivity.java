package com.example.ricca.enigma;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final VideoView videoview=findViewById(R.id.videoView);
        final TextView welcome_text=findViewById(R.id.welcome);
        final Button start_cr=findViewById(R.id.start);
        if(Crypt_decrypt.first_access){
            welcome_text.setVisibility(View.VISIBLE);
            start_cr.setVisibility(View.VISIBLE);
            videoview.setVisibility(View.INVISIBLE);}
        if(!Crypt_decrypt.first_access) {
            videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.enigma_entry));
            videoview.start();
            Crypt_decrypt.first_access = true;
        }
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                welcome_text.setVisibility(View.VISIBLE);
                start_cr.setVisibility(View.VISIBLE);
                videoview.setVisibility(View.INVISIBLE);
            }
        });

    }

    //Per aprire l'activity per cifrare
   public void openCrypt(View view){
       Rotors.enigma_preferences=getSharedPreferences(Rotors.ENIGMAPREFERENCES,Context.MODE_PRIVATE);
       SharedPreferences.Editor editor=Rotors.enigma_preferences.edit();
       editor.putInt("rotor_one_key",0);
       editor.putInt("rotor_two_key",0);
       editor.putInt("rotor_three_key",0);
       editor.putInt("rotor_four_key",0);
       editor.putInt("letter_one_key",0);
       editor.putInt("letter_two_key",0);
       editor.putInt("letter_three_key",0);
       editor.putInt("letter_four_key",0);
       editor.commit();
       Crypt_decrypt.crypt_preferences=getSharedPreferences(Crypt_decrypt.CRYPTPREFERENCES,Context.MODE_PRIVATE);
       SharedPreferences.Editor editor2=Crypt_decrypt.crypt_preferences.edit();
       editor2.putString("original_text_key","");
       editor2.putString("crypted_text2_key","");
       editor2.putString("decrypted_text2_key","");
       editor2.commit();
       Intent intent = new Intent(this, Crypt_decrypt.class);
       startActivity(intent);
   }
   //Per aprire l'activity per decifrare
    //public void openDecrypt(View view){
       // Intent intent = new Intent(this, Decrypt.class);
       // startActivity(intent);
   // }
}
