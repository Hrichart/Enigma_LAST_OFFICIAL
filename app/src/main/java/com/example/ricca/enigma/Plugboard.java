package com.example.ricca.enigma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Plugboard extends AppCompatActivity {

    private RadioButton radio1;
    private RadioButton radio2;
    private byte r1=0;
    private byte r2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugboard);
        radio1=findViewById(R.id.radio_plug1);
        radio2=findViewById(R.id.radio_plug2);

    }

    public void openPlugssetting(View view){
        switch (view.getId()){
            case R.id.bttA2: //chiama funzione per cifrare
                Plug_heart(1, view); break;
            case R.id.bttB2:
                Plug_heart(2, view); break;
            case R.id.bttC2:
                Plug_heart(3, view); break;
            case R.id.bttD2:
                Plug_heart(4, view); break;
            case R.id.bttE2:
                Plug_heart(5, view); break;
            case R.id.bttF2:
                Plug_heart(6, view); break;
            case R.id.bttG2:
                Plug_heart(7, view); break;
            case R.id.bttH2:
                Plug_heart(8, view); break;
            case R.id.bttI2:
                Plug_heart(9, view); break;
            case R.id.bttJ2:
                Plug_heart(10, view); break;
            case R.id.bttK2:
                Plug_heart(11, view); break;
            case R.id.bttL2:
                Plug_heart(12, view); break;
            case R.id.bttM2:
                Plug_heart(13, view); break;
            case R.id.bttN2:
                Plug_heart(14, view); break;
            case R.id.bttO2:
                Plug_heart(15, view); break;
            case R.id.bttP2:
                Plug_heart(16, view); break;
            case R.id.bttQ2:
                Plug_heart(17, view); break;
            case R.id.bttR2:
                Plug_heart(18, view); break;
            case R.id.bttS2:
                Plug_heart(19, view); break;
            case R.id.bttT2:
                Plug_heart(20, view); break;
            case R.id.bttU2:
                Plug_heart(21, view); break;
            case R.id.bttV2:
                Plug_heart(22, view); break;
            case R.id.bttW2:
                Plug_heart(23, view); break;
            case R.id.bttX2:
                Plug_heart(24, view); break;
            case R.id.bttY2:
                Plug_heart(25, view); break;
            case R.id.bttZ2:
                Plug_heart(26, view); break;
        }
    }

    public void Plug_heart(int index, View view){
        Crypt_decrypt.crypt_preferences=getSharedPreferences(Crypt_decrypt.CRYPTPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=Crypt_decrypt.crypt_preferences.edit();
        if(radio1.isChecked()){
            if(r1==0){
                editor.putInt("first_pair1_key",index);
                view.setBackgroundColor(Color.YELLOW);
                r1=1;
            }
            else if(r1==1 && index!=Crypt_decrypt.crypt_preferences.getInt("first_pair1_key",0)){
                editor.putInt("first_pair2_key",index);
                view.setBackgroundColor(Color.YELLOW);
                r1=3;
            }
        }
        if(radio2.isChecked()){
            if(r2==0 && index!=Crypt_decrypt.crypt_preferences.getInt("first_pair1_key",0) && index!=Crypt_decrypt.crypt_preferences.getInt("first_pair2_key",0)){
                editor.putInt("second_pair1_key",index);
                view.setBackgroundColor(Color.RED);
                r2=1;
            }
            else if(r2==1 && index!=Crypt_decrypt.crypt_preferences.getInt("first_pair1_key",0) && index!=Crypt_decrypt.crypt_preferences.getInt("first_pair2_key",0) && index!=Crypt_decrypt.crypt_preferences.getInt("second_pair1_key",0)){
                editor.putInt("second_pair2_key",index);
                view.setBackgroundColor(Color.RED);
                r2=3;
            }
        }
        editor.apply();
    }

    public void save(View view){
        Intent intent=new Intent(this,Crypt_decrypt.class);
        startActivity(intent);
    }
}
