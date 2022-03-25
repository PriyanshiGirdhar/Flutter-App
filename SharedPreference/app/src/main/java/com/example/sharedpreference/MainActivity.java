package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedpreference.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.textView.setText(binding.etext.getText().toString());
            }
        });
 binding.button.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         savetext();
     }
 });
 loadagain();
    }

    private void savetext() {
        SharedPreferences preferences=getSharedPreferences("sharedprefeneces",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("text",binding.textView.getText().toString());
        editor.putBoolean("switch",binding.switch1.isChecked());
        editor.apply();
        Toast.makeText(this, "Data is saved", Toast.LENGTH_SHORT).show();
    }
    private void loadagain() {
        String text;
        Boolean onoff;
        SharedPreferences preferences=getSharedPreferences("sharedprefeneces",MODE_PRIVATE);
        text=preferences.getString("text","");
        onoff=preferences.getBoolean("switch",false);
        binding.textView.setText(text);
        binding.switch1.setChecked(onoff);
    }
}
