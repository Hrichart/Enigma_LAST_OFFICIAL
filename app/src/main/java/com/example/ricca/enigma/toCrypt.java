package com.example.ricca.enigma;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class toCrypt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_crypt);

        TextView original_text2=findViewById(R.id.original_text_2);
        TextView crypted_text1=findViewById(R.id.crypted_text_1);
        TextView crypted_text2=findViewById(R.id.crypted_text_2);
        TextView decrypted_text1=findViewById(R.id.decrypted_text_1);
        TextView decrypted_text2=findViewById(R.id.decrypted_text_2);


        Crypt_decrypt.crypt_preferences=getSharedPreferences(Crypt_decrypt.CRYPTPREFERENCES,Context.MODE_PRIVATE);

        original_text2.setText(Crypt_decrypt.crypt_preferences.getString("original_text_key",""));
        crypted_text1.setVisibility(Crypt_decrypt.crypt_preferences.getInt("crypted_text1_key",0));
        crypted_text2.setVisibility(Crypt_decrypt.crypt_preferences.getInt("crypted_text1_key",0));
        crypted_text2.setText(Crypt_decrypt.crypt_preferences.getString("crypted_text2_key",""));
        decrypted_text1.setVisibility(Crypt_decrypt.crypt_preferences.getInt("decrypted_text1_key",4));
        decrypted_text2.setVisibility(Crypt_decrypt.crypt_preferences.getInt("decrypted_text1_key",4));
        decrypted_text2.setText(Crypt_decrypt.crypt_preferences.getString("decrypted_text2_key",""));

    }

    public void openCrypt(View view)
    {
        Intent intent=new Intent(this,Crypt_decrypt.class);
        startActivity(intent);
    }
    public void openCopy(View view){
        Crypt_decrypt.crypt_preferences=getSharedPreferences(Crypt_decrypt.CRYPTPREFERENCES,Context.MODE_PRIVATE);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip;
        if(Crypt_decrypt.crypt_preferences.getInt("crypted_text1_key",0)==0)
            clip = ClipData.newPlainText("Text",Crypt_decrypt.crypt_preferences.getString("crypted_text2_key",""));
        else
            clip = ClipData.newPlainText("Text",Crypt_decrypt.crypt_preferences.getString("decrypted_text2_key",""));

        assert clipboard != null;
        clipboard.setPrimaryClip(clip);
        Toast toast=Toast.makeText(getApplicationContext(),R.string.copy,Toast.LENGTH_SHORT);
        toast.show();
    }
}
