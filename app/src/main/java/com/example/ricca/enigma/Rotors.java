package com.example.ricca.enigma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class Rotors extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final byte[] rotorI=new    byte[] {4,9,10,2,7,1,-3,9,13,16,3,8,2,9,10,-8,7,3,0,-4,-20,-13,-21,-6,-22,-16,           20,21,22,3,-4,-2,-1,8,13,16,-9,-7,-10,-3,-2,4,-9,6,0,-8,-3,-13,-9,-7,-10,-16,         17};
    private static final byte[] rotorII=new   byte[] {0,8,1,7,14,3,11,13,15,-8,1,-4,10,6,-2,-13,0,-11,7,-6,-5,3,-17,-2,-10,-21,        0,8,13,-1,21,17,11,4,-3,-8,-7,-1,2,6,10,5,0	,-11,-14,-6,-13,2,-10,-15,-3,-7,          5};
    private static final byte[] rotorIII=new  byte[] {1,2,3,4,5,6,-4,8,9,10,13,10,13,0,10,-11,-8,5,-12,-19,-10,-9,-2,-5,-8,-11,        19,-1,4,-2,11,-3,12,-4,8,-5,10,-6,9,0,11,-8,8,-9,5,-10,2,-10,-5,-13,-10,-13,          22};
    private static final byte[] rotorIV=new   byte[] {4,17,12,18,11,20,3,-7,16,7,10,-3,5,-6,9,-4,-3,-12,1,-13,-10,-18,-20,-11,-2,-24,  7,24,20,18,-4,12,13,6,3,-3,10,4,11,3,-12,-11,-7,-5,-17,-1,-10,-18,2,-9,-9,-12,         10};
    private static final byte[] rotorV=new    byte[] {21,24,-1,14,2,3,13,17,12,6,8,-8,1,-6,-3,8,-16,5,-6,-10,-4,-7,-17,-19,-22,-15,    16,1,22,8,19,17,-2,6,-3,10,15,3,6,-1,7,-6,4,-14,-8,-13,-12,-21,-5,-8,-17,-24,         26};
    //private static final byte[] rotorVI=new   byte[] {9,14,4,18,10,15,6,-2,16,7,-9,-7,1,-6,11,2,-13,-7,-18,-1,3,-10,-14,-21,-5,-3,     18,9,21,13,7,2,-4,6,14,-9,7,10,-6,-1,-10,-14,-7,-2,1,5,-15,-18,3,-3,-16,-11};
    //private static final byte[] rotorVII=new  byte[] {13,24,7,4,2,12,-4,16,4,15,8,11,-11,1,6,-10,-16,-9,3,-8,-5,-17,-12,-7,-21,-6,     16,11,4,21,17,10,-2,-4,9,-7,12,8,-4,-13,-1,5,7,-12,-8,6,-6,-3,-11,-16,-15,-24};
    //private static final byte[] rotorVIII=new byte[] {5,9,14,4,15,6,17,7,-6,-8,-1,7,3,-10,11,2,-16,-5,-14,3,-7,-13,-2,1,-18,-4,        16,8,6,10,14,-5,18,-4,13,1,-9,-6,5,7,-7,-3,-14,-2,-7,-15,2,4,-3,-17,-1,-11};

    private static final byte[] reflectorA=new byte[] {4,8,10,22,-4,6,18,16,13,-8,12,-6,-10,4,2,5,-2,-4,1,-1,-5,-13,-12,-16,-18,-22};
    private static final byte[] reflectorB=new byte[] {24,16,18,4,12,13,5,-4,7,14,3,-5,2,-3,-2,-7,-12,-16,-13,6,-18,1,-1,-14,-24,-6};
    private static final byte[] reflectorC=new byte[] {5,20,13,6,4,-5,8,17,-4,-6,7,14,11,9,-8,-13,3,-7,2,-3,-2,-20,-9,-11,-17,-14};

    private static byte[] selected_rotor_1=new byte[53];
    private static byte[] selected_rotor_2=new byte[53];
    private static byte[] selected_rotor_3=new byte[53];
    public static byte[] selected_reflector_1=new byte[26];

    public static Spinner spinner1;
    public static Spinner spinner2;
    public static Spinner spinner3;
    public static Spinner spinner4;
    public static Spinner spinner5;
    public static Spinner spinner6;
    public static Spinner spinner7;
    public static Spinner spinner8;

    public static String ENIGMAPREFERENCES="enigmaprefs";
    public static String rotor_one="rotor_one_key";
    public static String rotor_two="rotor_two_key";
    public static String rotor_three="rotor_three_key";
    public static String reflector="rotor_four_key";
    public static String letter_one="letter_one_key";
    public static String letter_two="letter_two_key";
    public static String letter_three="letter_three_key";
    public static String letter_four="letter_four_key";

    public static SharedPreferences enigma_preferences;

    byte rotor1;
    byte rotor2;
    byte rotor3;
    byte reflector1;
    byte letter1;
    byte letter2;
    byte letter3;
    byte letter4;

    //private EditText valori;         //Per verificare di aver selezionato il rotore in modo corretto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotors);
        //valori=(EditText) findViewById(R.id.selected_item);    //Per verificare di aver selezionato il rotore in modo corretto

        //Set up dello spinner del primo rotore
        spinner1=findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.rotors_list, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);


        //Set up dello spinner del secondo rotore
        spinner2=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.rotors_list, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        //Set up dello spinner del terzo rotore
        spinner3=findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource(this,R.array.rotors_list, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);


        //Set up dello spinner del primo riflettore
        spinner4=findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter4=ArrayAdapter.createFromResource(this,R.array.reflector_list, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        //Set up dello spinner della prima lettera
        spinner5=findViewById(R.id.letter_selector_1);
        ArrayAdapter<CharSequence> adapter5=ArrayAdapter.createFromResource(this,R.array.letters_list, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        //Set up dello spinner della seconda lettera
        spinner6=findViewById(R.id.letter_selector_2);
        ArrayAdapter<CharSequence> adapter6=ArrayAdapter.createFromResource(this,R.array.letters_list, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);

        //Set up dello spinner della terza lettera
        spinner7=findViewById(R.id.letter_selector_3);
        ArrayAdapter<CharSequence> adapter7=ArrayAdapter.createFromResource(this,R.array.letters_list, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);

        //Set up dello spinner della lettera del rotore
        spinner8=findViewById(R.id.reflector_selection_4);
        ArrayAdapter<CharSequence> adapter8=ArrayAdapter.createFromResource(this,R.array.letters_list, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter8);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);
        spinner5.setOnItemSelectedListener(this);
        spinner6.setOnItemSelectedListener(this);
        spinner7.setOnItemSelectedListener(this);
        spinner8.setOnItemSelectedListener(this);

    }

    //Quando è selezionato un rotore, si copia l'array (variabile privata) in un array pubblico, per poter essere utilizzato in altre activity
    //Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -->  per risolvere il warning di incompatibilità con vacchie API

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();     //stringa per il confronto con il nome del rotore
        //Casistica per identificare quale rotore è stato impostato e per salvare la nuova configurazione in una variabile
        switch (parent.getId()) {
            case R.id.spinner1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor I"))
                    {System.arraycopy(rotorI, 0, selected_rotor_1, 0, 53); rotor1=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor II"))
                    {System.arraycopy(rotorII, 0, selected_rotor_1, 0, 53); rotor1=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor III"))
                    {System.arraycopy(rotorIII, 0, selected_rotor_1, 0, 53); rotor1=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor IV"))
                    {System.arraycopy(rotorIV, 0, selected_rotor_1, 0, 53); rotor1=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor V"))
                    {System.arraycopy(rotorV, 0, selected_rotor_1, 0, 53); rotor1=(byte)position;}}
                    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor VI"))
                        System.arraycopy(rotorVI, 0, selected_rotor_1, 0, 52); }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor VII"))
                        System.arraycopy(rotorVII, 0, selected_rotor_1, 0, 52); }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor VIII"))
                        System.arraycopy(rotorVIII, 0, selected_rotor_1, 0, 52); }*/
                break;
            case R.id.spinner2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor I"))
                    {System.arraycopy(rotorV, 0, selected_rotor_2, 0, 53); rotor2=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor II"))
                    {System.arraycopy(rotorII, 0, selected_rotor_2, 0, 53); rotor2=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor III"))
                    {System.arraycopy(rotorIII, 0, selected_rotor_2, 0, 53); rotor2=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor IV"))
                    {System.arraycopy(rotorIV, 0, selected_rotor_2, 0, 53); rotor2=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor V"))
                    {System.arraycopy(rotorV, 0, selected_rotor_2, 0, 53); rotor2=(byte)position;}}
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor VI"))
                        System.arraycopy(rotorVI, 0, selected_rotor_2, 0, 52); }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor VII"))
                        System.arraycopy(rotorVII, 0, selected_rotor_2, 0, 52); }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor VIII"))
                        System.arraycopy(rotorVIII, 0, selected_rotor_2, 0, 52); }*/
                break;
            case R.id.spinner3:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor I"))
                    {System.arraycopy(rotorI, 0, selected_rotor_3, 0, 53); rotor3=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor II"))
                    {System.arraycopy(rotorII, 0, selected_rotor_3, 0, 53); rotor3=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor III"))
                    {System.arraycopy(rotorIII, 0, selected_rotor_3, 0, 53); rotor3=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor IV"))
                    {System.arraycopy(rotorIV, 0, selected_rotor_3, 0, 53); rotor3=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor V"))
                    {System.arraycopy(rotorV, 0, selected_rotor_3, 0, 53); rotor3=(byte)position;}}
               /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor VI"))
                        System.arraycopy(rotorVI, 0, selected_rotor_3, 0, 52); }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor VII"))
                        System.arraycopy(rotorVII, 0, selected_rotor_3, 0, 52); }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Rotor VIII"))
                        System.arraycopy(rotorVIII, 0, selected_rotor_3, 0, 52); } */
                break;
            case R.id.spinner4:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if(Objects.equals(text, "Reflector A"))
                    {System.arraycopy(reflectorA,0, selected_reflector_1,0,26); reflector1=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Reflector B"))
                    {System.arraycopy(reflectorB,0, selected_reflector_1,0,26); reflector1=(byte)position;}}
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Objects.equals(text, "Reflector C"))
                    {System.arraycopy(reflectorC,0, selected_reflector_1,0,26); reflector1=(byte)position;}}
                break;


            case R.id.letter_selector_1:
                letter1=(byte) position;
                break;
            case R.id.letter_selector_2:
                letter2=(byte) position;
                break;
            case R.id.letter_selector_3:
                letter3=(byte) position;
                break;
            case R.id.reflector_selection_4:
                letter4=(byte) position;
                break;
        }
        //valori.setText(Arrays.toString(selected_reflector_1));    //Per verificare di aver selezionato il rotore in modo corretto
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public void save(View view){
        Intent intent=new Intent(this ,Crypt_decrypt.class);
        System.arraycopy(selected_rotor_1,0,Crypt_decrypt.selected_rotor_1,0,53);
        System.arraycopy(selected_rotor_2,0,Crypt_decrypt.selected_rotor_2,0,53);
        System.arraycopy(selected_rotor_3,0,Crypt_decrypt.selected_rotor_3,0,53);
        System.arraycopy(selected_reflector_1,0,Crypt_decrypt.selected_reflector_1,0,26);
        Crypt_decrypt.selected_letter_1= letter1;
        Crypt_decrypt.selected_letter_2= letter2;
        Crypt_decrypt.selected_letter_3= letter3;
        Crypt_decrypt.selected_letter_4= letter4;
        startActivity(intent);
        Toast toast=Toast.makeText(getApplicationContext(),R.string.save_toast,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void reset(View view){
        enigma_preferences=getSharedPreferences(ENIGMAPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=enigma_preferences.edit();
        editor.putInt("rotor_one_key",0);
        editor.putInt("rotor_two_key",0);
        editor.putInt("rotor_three_key",0);
        editor.putInt("rotor_four_key",0);
        editor.putInt("letter_one_key",0);
        editor.putInt("letter_two_key",0);
        editor.putInt("letter_three_key",0);
        editor.putInt("letter_four_key",0);
        editor.commit();
        spinner1.setSelection(enigma_preferences.getInt("rotor_one_key",0));
        spinner2.setSelection(enigma_preferences.getInt("rotor_two_key",0));
        spinner3.setSelection(enigma_preferences.getInt("rotor_three_key",0));
        spinner4.setSelection(enigma_preferences.getInt("rotor_four_key",0));
        spinner5.setSelection(enigma_preferences.getInt("letter_one_key",0));
        spinner6.setSelection(enigma_preferences.getInt("letter_two_key",0));
        spinner7.setSelection(enigma_preferences.getInt("letter_three_key",0));
        spinner8.setSelection(enigma_preferences.getInt("letter_four_key",0));

        Toast toast=Toast.makeText(getApplicationContext(),R.string.reset_toast,Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onPause(){
        super.onPause();

        enigma_preferences=getSharedPreferences(ENIGMAPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=enigma_preferences.edit();
        editor.putInt("rotor_one_key",rotor1);
        editor.putInt("rotor_two_key",rotor2);
        editor.putInt("rotor_three_key",rotor3);
        editor.putInt("rotor_four_key",reflector1);
        editor.putInt("letter_one_key",letter1);
        editor.putInt("letter_two_key",letter2);
        editor.putInt("letter_three_key",letter3);
        editor.putInt("letter_four_key",letter4);

        editor.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        enigma_preferences=getSharedPreferences(ENIGMAPREFERENCES,Context.MODE_PRIVATE);
        spinner1.setSelection(enigma_preferences.getInt("rotor_one_key",0));
        spinner2.setSelection(enigma_preferences.getInt("rotor_two_key",0));
        spinner3.setSelection(enigma_preferences.getInt("rotor_three_key",0));
        spinner4.setSelection(enigma_preferences.getInt("rotor_four_key",0));
        spinner5.setSelection(enigma_preferences.getInt("letter_one_key",0));
        spinner6.setSelection(enigma_preferences.getInt("letter_two_key",0));
        spinner7.setSelection(enigma_preferences.getInt("letter_three_key",0));
        spinner8.setSelection(enigma_preferences.getInt("letter_four_key",0));
    }

}
