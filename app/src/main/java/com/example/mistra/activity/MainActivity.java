package com.example.mistra.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mistra.application.CommonActivity;
import com.squareup.otto.Subscribe;

import com.example.mistra.application.MyApplication;
import com.example.mistra.service.LocService;


public class MainActivity extends CommonActivity implements View.OnClickListener {

    private Button listactivity;
    private Button musicActivity;
    private Button hwactivity;
    private Button tpactivity;

    private Button startservloc;
    private Button stopservloc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // On récupère le bouton de la vue dédié à l'activity Music
        this.musicActivity = (Button) findViewById(R.id.MusicActivity);
        // On lui affecte le listener
        this.musicActivity.setOnClickListener(this);

        // On récupère le bouton de la vue
        this.listactivity = (Button) findViewById(R.id.BoutonFirstAct);
        // On lui affecte le listener
        this.listactivity.setOnClickListener(this);

        // On récupère le bouton de la vue dédié à l'activity Music
        this.hwactivity = (Button) findViewById(R.id.HWActivity);
        // On lui affecte le listener
        this.hwactivity.setOnClickListener(this);

        // On récupère le bouton de la vue dédié à l'activity Music
        this.tpactivity = (Button) findViewById(R.id.TPActivity);
        // On lui affecte le listener
        this.tpactivity.setOnClickListener(this);

        // On récupère le bouton de la vue dédié à l'activity Music
        this.startservloc = (Button) findViewById(R.id.start_serv_loc);
        // On lui affecte le listener
        this.startservloc.setOnClickListener(this);

        // On récupère le bouton de la vue dédié à l'activity Music
        this.stopservloc = (Button) findViewById(R.id.stop_serv_loc);
        // On lui affecte le listener
        this.stopservloc.setOnClickListener(this);

        this.updateTitle("Main Activity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MAIN_ACTIVITY", "On start");
        MyApplication.getInstance().getEventBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MAIN_ACTIVITY", "On stop");
        MyApplication.getInstance().getEventBus().unregister(this);
    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.i("MAIN_ACTIVITY", "On pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MAIN_ACTIVITY", "On resume");
    }

    @Override
    protected void onDestroy() {
        Log.i("MAIN_ACTIVITY", "On destroy");
        super.onDestroy();

    }
    @Subscribe
    public void afficherLoc(String Loc){
        // Affichage
        Log.i("LOC_SERV_UPDATE",Loc);
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, Loc, duration);
        toast.show();
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



    @Override
    public void onClick(View v) {
        // On déclenche la méthode du boutton en fonction de l'id du bouton récupéré
        if (v.getId() == R.id.BoutonFirstAct) {
            // Accès à la vue avec passage de paramètres
            this.lancerListViewActivity();
        } else if (v.getId() == R.id.MusicActivity) {
            // Accès à l'activité musique
            this.lancerMusicActivity();
        } else if (v.getId() == R.id.HWActivity){
            // Accès à l'activité HelloWorld
            this.lancerHelloWorldActivity();
        } else if (v.getId() == R.id.TPActivity){
            // Accès à l'activié TwoPane
            this.lancerTwoPaneActivity();
        }else if(v.getId() == R.id.start_serv_loc){
            startProgress();
            startService(new Intent(this, LocService.class));

        }else if(v.getId() == R.id.stop_serv_loc){
            stopProgress();
            this.stopService(new Intent(this, LocService.class));
        }

    }

    /**
     * Méthode de lancement de l'activité HelloWorld
     */
    private void lancerHelloWorldActivity() {
        // On crée un intent et on lance l'activité correspondante
        final Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("INFO", getString(R.string.hello_world));
        startActivity(intent);
    }
    /**
     * Méthode de lancement de l'activité Music
     */
    private void lancerMusicActivity() {
        // On crée un intent et on lance l'activité correspondante
        final Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }

    /**
     * Méthode de lancement de l'activité ListView
     */
    public void lancerListViewActivity() {
        // On déclenche la seconde activité
        final Intent intent = new Intent(this, ListViewEleveActivity.class);
        startActivity(intent);

    }

    /**
     * Méthode de lancement de l'activité HelloWorld
     */
    private void lancerTwoPaneActivity() {
        // On crée un intent et on lance l'activité correspondante
        final Intent intent = new Intent(this, TwoPaneActivity.class);
        startActivity(intent);
    }
}