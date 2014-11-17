package com.example.mistra.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button bouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On récupère le bouton de la vue
        this.bouton =  (Button) findViewById(R.id.BoutonFirstAct);
        // On lui affecte le listener
        this.bouton.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MAIN_ACTIVITY","On start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MAIN_ACTIVITY","On stop");
    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.i("MAIN_ACTIVITY","On pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MAIN_ACTIVITY","On resume");
    }

    @Override
    protected void onDestroy() {
        Log.i("MAIN_ACTIVITY","On destroy");
        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void nouvelleActivite(){
        // On déclenche la seconde activité
        final Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("INFO",getString(R.string.hello_world));
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        // On déclenche la méthode du boutton
        this.nouvelleActivite();
    }
}
