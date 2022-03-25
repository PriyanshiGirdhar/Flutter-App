package com.example.changelanguage.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.changelanguage.R;
import com.example.changelanguage.ui.login.LoginViewModel;
import com.example.changelanguage.ui.login.LoginViewModelFactory;
import com.example.changelanguage.databinding.ActivityLoginBinding;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

loadLocale();
           binding.button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   changelanguage();
               }
           });
    }

    private void changelanguage() {
        final String language[] ={"English","Bengali","Hindi","Urdu"};
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Choose Language");
        alert.setSingleChoiceItems(language, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    setLocale("");
                    recreate();
                }
                else if(i==1){
                    setLocale("bn");
                    recreate();
                }
                else if(i==2){
                    setLocale("hi");
                    recreate();
                }
                else if(i==3){
                    setLocale("ur");
                    recreate();
                }
            }
        });
        alert.create();
        alert.show();
    }

    private void setLocale(String lang) {
        Locale  locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences .Editor  editor=getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("applang",lang);
        editor.apply();
    }
    private void loadLocale() {

        SharedPreferences  editor=getSharedPreferences("settings",MODE_PRIVATE);
        String text;
        String lan=editor.getString("applang","");
        setLocale(lan);
        text=editor.getString("text","");

        binding.username.setText(text);

    }
}