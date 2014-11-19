package com.example.mistra.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistra.adapter.EleveAdapter;
import com.example.mistra.beans.EleveBean;

import java.util.ArrayList;


public class ListViewEleveActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String LISTE_ELEVE = "LISTE_ELEVE";
    private ArrayList<EleveBean> listeleve;
    private EleveAdapter adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_eleve);

        // On fill la liste avec l'eleveAdaptater
        lv = (ListView) findViewById(R.id.listViewEleve);
        listeleve = getListEleve();

        adapter = new EleveAdapter(this,listeleve);
        lv.setAdapter(adapter);

        // On ajoute comme listener au bouton l'activité
        Button ajoutEleve = (Button) findViewById(R.id.AjoutEleveBouton);
        ajoutEleve.setOnClickListener(this);

        // On ajoute l'activité actuelle comme listener de la liste
        lv.setOnItemClickListener(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // On sauvegarde dans le Bundle l'arrayList d'élèves parcelée
        outState.putParcelableArrayList(LISTE_ELEVE, listeleve);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        if (savedInstanceState != null){
            // On passe par une variable intermediare, sinon le cast ne passe pas
            ArrayList<EleveBean> temp;
            // On récupère la liste d'élève à partir du bundle et on la déparcele.
            temp = savedInstanceState.getParcelableArrayList(LISTE_ELEVE);
            // On vide la liste existante et on la remplit avec la liste restauré si elle est non vide
            listeleve.clear();
            if(temp != null){
                listeleve.addAll(temp);
            }
        }
    }

    private ArrayList<EleveBean> getListEleve() {
        ArrayList<EleveBean> lv = new ArrayList<EleveBean>();
        lv.add(new EleveBean("SANTUS","Brice","HOMME"));
        lv.add(new EleveBean("PREIN","Francois","HOMME"));
        lv.add(new EleveBean("TILENDIER","Pierre","HOMME"));
        lv.add(new EleveBean("ALONSO","Sofia","FEMME"));
        lv.add(new EleveBean("STUART","Alice","FEMME"));

        return lv;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view_eleve, menu);
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
        if(v.getId() == R.id.AjoutEleveBouton){
            TextView tv_nom = (TextView)this.findViewById(R.id.nom_eleve_form);
            TextView tv_prenom = (TextView)this.findViewById(R.id.prenom_eleve_form);
            RadioGroup rg_sexe = (RadioGroup)this.findViewById(R.id.sexe_eleve_form);
            String radiovalue=  ((RadioButton)this.findViewById(rg_sexe.getCheckedRadioButtonId())).getText().toString();

            String nom = tv_nom.getText().toString();
            String prenom = tv_prenom.getText().toString();
            String sexe= "";

            if(radiovalue.equals("H")){
                sexe = "HOMME";
            }else{
                sexe = "FEMME";
            }

            // On ajoute l'élève à la liste de donnée
            this.listeleve.add(new EleveBean(nom,prenom,sexe));

            // On notify la vue
            this.adapter.notifyDataSetChanged();

            // On vide le formulaire
            tv_nom.setText("");
            tv_prenom.setText("");
        }
    }

    /**
     * Affichage d'un Toast (popup) lorsque l'on clique sur un élément de la liste
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CharSequence text = listeleve.get(position).getNom()+" "+listeleve.get(position).getPrenom();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, "Sélection de : "+text, duration);
        toast.show();
    }



}
