package com.example.mistra.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SecondActivity extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // On récupère le paramètre passé par ActivityMain
        Intent intent = getIntent();
        String valeur = intent.getStringExtra("INFO");

        this.tv = (TextView) findViewById(R.id.Param);
        this.tv.setText(valeur);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("SECOND_ACTIVITY", "On start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("SECOND_ACTIVITY","On stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("SECOND_ACTIVITY","On pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SECOND_ACTIVITY","On resume");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
