package com.example.fragmentrussian1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fragmentrussian1.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements RussianItemFragment.OnListFragmentInteractionListener {
    public RussianItemFragment russianItemFragment=RussianItemFragment.newInstance(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, russianItemFragment).commitNow();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.Rule item) {
        Toast.makeText(this, item.details, Toast.LENGTH_SHORT).show();
        if(item.getSign()!="+"&&item.id.length()!=1){
        Intent intent = new Intent(MainActivity.this,Exercise.class);
        startActivity(intent);
        }
    }
}
