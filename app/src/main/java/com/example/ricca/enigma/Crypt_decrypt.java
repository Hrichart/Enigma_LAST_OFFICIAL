package com.example.ricca.enigma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton;


public class Crypt_decrypt extends AppCompatActivity {

    public static boolean first_access=false;
    private Switch cr_decr_switch;
    private static final char[] letters=new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public static byte[] selected_rotor_1=new byte[53];
    public static byte[] selected_rotor_2=new byte[53];
    public static byte[] selected_rotor_3=new byte[53];
    public static byte[] selected_reflector_1=new byte[26];
    public static byte selected_letter_1;
    public static byte selected_letter_2;
    public static byte selected_letter_3;
    public static byte selected_letter_4;
    private static int offset1;
    private static int offset2;
    private static int offset3;
    private static int offset4;

    TextView original_text2=findViewById(R.id.original_text_2);
    TextView crypted_text1=findViewById(R.id.crypted_text_1);
    TextView crypted_text2=findViewById(R.id.crypted_text_2);
    TextView decrypted_text1=findViewById(R.id.decrypted_text_1);
    TextView decrypted_text2=findViewById(R.id.decrypted_text_2);

    byte i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypt);
        cr_decr_switch=findViewById(R.id.cr_decr_sw);
        offset1=selected_letter_1;
        offset2=selected_letter_2;
        offset3=selected_letter_3;
        offset4=selected_letter_4;
        cr_decr_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonvView, boolean isChecked) {
                original_text2.setText(null);
                crypted_text2.setText(null);
                decrypted_text2.setText(null);
                i=0;
                if(isChecked){
                    crypted_text1.setVisibility(View.VISIBLE);
                    crypted_text2.setVisibility(View.VISIBLE);
                    decrypted_text1.setVisibility(View.INVISIBLE);
                    decrypted_text2.setVisibility(View.INVISIBLE);
                }
                else{
                    crypted_text1.setVisibility(View.VISIBLE);
                    crypted_text2.setVisibility(View.VISIBLE);
                    decrypted_text1.setVisibility(View.INVISIBLE);
                    decrypted_text2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    //Per andare alla home
    public void openHome(View view){
        original_text2.setText(null);
        crypted_text2.setText(null);
        decrypted_text2.setText(null);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    //Per selezionare i rotori
    public void openRotors(View view){
        original_text2.setText(null);
        crypted_text2.setText(null);
        decrypted_text2.setText(null);
        Intent intent=new Intent(this,Rotors.class);
        startActivity(intent);
    }
    //Per selezionare le spine
    public void openPlugboard(View view){
        original_text2.setText(null);
        crypted_text2.setText(null);
        decrypted_text2.setText(null);
        Intent intent=new Intent(this,Plugboard.class);
        startActivity(intent);
    }
    //Per usare la tastiera
    public void openEnigma(View view){
        switch (view.getId()){
            case R.id.bttA: //chiama funzione per cifrare
                Enigma_heart(1); break;
            case R.id.bttB:
                Enigma_heart(2); break;
            case R.id.bttC:
                Enigma_heart(3); break;
            case R.id.bttD:
                Enigma_heart(4); break;
            case R.id.bttE:
                Enigma_heart(5); break;
            case R.id.bttF:
                Enigma_heart(6); break;
            case R.id.bttG:
                Enigma_heart(7); break;
            case R.id.bttH:
                Enigma_heart(8); break;
            case R.id.bttI:
                Enigma_heart(9); break;
            case R.id.bttJ:
                Enigma_heart(10); break;
            case R.id.bttK:
                Enigma_heart(11); break;
            case R.id.bttL:
                Enigma_heart(12); break;
            case R.id.bttM:
                Enigma_heart(13); break;
            case R.id.bttN:
                Enigma_heart(14); break;
            case R.id.bttO:
                Enigma_heart(15); break;
            case R.id.bttP:
                Enigma_heart(16); break;
            case R.id.bttQ:
                Enigma_heart(17); break;
            case R.id.bttR:
                Enigma_heart(18); break;
            case R.id.bttS:
                Enigma_heart(19); break;
            case R.id.bttT:
                Enigma_heart(20); break;
            case R.id.bttU:
                Enigma_heart(21); break;
            case R.id.bttV:
                Enigma_heart(22); break;
            case R.id.bttW:
                Enigma_heart(23); break;
            case R.id.bttX:
                Enigma_heart(24); break;
            case R.id.bttY:
                Enigma_heart(25); break;
            case R.id.bttZ:
                Enigma_heart(26); break;
        }
    }

    public void Enigma_heart(int index) {
        char result;
        int starting=index;

        offset1=offset1+1;      //Rotazione del rotore (che avviene virtualmente prima di premere il tasto)
        if(offset1==selected_rotor_1[52]) offset2=offset2+1;    //Controllo per l'incremento del secondo e del terzo rotore
        if(offset2==selected_rotor_2[52]) offset3=offset3+1;

        if(offset1==27) offset1=1;      //Controllo dell'indice quando si arriva alla fine degli elementi del rotore per farlo ripartire dall'inizio
        if(offset2==27) offset2=1;
        if(offset3==27) offset3=1;

        index=index+offset1;        //Incremento dell'indice dovuto alla rotazione rotore
        if(index>26) index=index-26; if(index<=0) index=index+26;       //controllo per mantenere l'indice all'interno del range di elementi del rotore
        index=index+selected_rotor_1[index-1];                          //incremento effettivo dovuto al rotore
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index-offset1;                                            //ripristino della posizione assolta sottraendo l'offset dovuto alla rotazione del rotore
        if(index>26) index=index-26; if(index<=0) index=index+26;

        index=index+offset2;
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index+selected_rotor_2[index-1];
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index-offset2;
        if(index>26) index=index-26; if(index<=0) index=index+26;

        index=index+offset3;
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index+selected_rotor_3[index-1];
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index-offset3;
        if(index>26) index=index-26; if(index<=0) index=index+26;

        index=index+offset4;
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index+selected_reflector_1[index-1];
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index-offset4;
        if(index>26) index=index-26; if(index<=0) index=index+26;

        index=index+offset3;
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index+selected_rotor_3[26+index-1];
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index-offset3;
        if(index>26) index=index-26; if(index<=0) index=index+26;

        index=index+offset2;
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index+selected_rotor_2[26+index-1];
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index-offset2;
        if(index>26) index=index-26; if(index<=0) index=index+26;

        index=index+offset1;
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index+selected_rotor_1[26+index-1];
        if(index>26) index=index-26; if(index<=0) index=index+26;
        index=index-offset1;
        if(index>26) index=index-26; if(index<=0) index=index+26;

        if(cr_decr_switch.isChecked())
        {
            if(i<100){
                result=letters[index-1];
                i= (byte) (i+1);
                original_text2.setText(String.format("%s%s",original_text2, String.valueOf(letters[starting-1])));
                decrypted_text2.setText(String.format("%s%s", decrypted_text2, String.valueOf(result)));
            }
            else{
                Toast toast=Toast.makeText(getApplicationContext(),R.string.max_reached,Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else
        {
            if(i<125&&(i%4==0)){
                result=' ';
                i= (byte) (i+1);
                crypted_text2.setText(String.format("%s%s", crypted_text2, String.valueOf(result)));
                result=letters[index-1];
                i= (byte) (i+1);
                original_text2.setText(String.format("%s%s",original_text2, String.valueOf(letters[starting-1])));
                crypted_text2.setText(String.format("%s%s", crypted_text2, String.valueOf(result)));
            }
            else if(i>=125){
                Toast toast=Toast.makeText(getApplicationContext(),R.string.max_reached,Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                result=letters[index-1];
                i= (byte) (i+1);
                original_text2.setText(String.format("%s%s",original_text2, String.valueOf(letters[starting-1])));
                crypted_text2.setText(String.format("%s%s", crypted_text2, String.valueOf(result)));
            }
        }
    }
    //Per resettare i rotori
    public void reset(View view){
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
        Rotors.spinner1.setSelection(Rotors.enigma_preferences.getInt("rotor_one_key",0));
        Rotors.spinner2.setSelection(Rotors.enigma_preferences.getInt("rotor_two_key",0));
        Rotors.spinner3.setSelection(Rotors.enigma_preferences.getInt("rotor_three_key",0));
        Rotors.spinner4.setSelection(Rotors.enigma_preferences.getInt("rotor_four_key",0));
        Rotors.spinner5.setSelection(Rotors.enigma_preferences.getInt("letter_one_key",0));
        Rotors.spinner6.setSelection(Rotors.enigma_preferences.getInt("letter_two_key",0));
        Rotors.spinner7.setSelection(Rotors.enigma_preferences.getInt("letter_three_key",0));
        Rotors.spinner8.setSelection(Rotors.enigma_preferences.getInt("letter_four_key",0));

        Toast toast=Toast.makeText(getApplicationContext(),R.string.reset_toast,Toast.LENGTH_SHORT);
        toast.show();
    }
}
