package com.example.mistra.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mistra.activity.R;
import com.example.mistra.activity.TwoPaneActivity;
import com.example.mistra.adapter.EleveAdapter;
import com.example.mistra.beans.EleveBean;

import java.util.ArrayList;

/**
 * Created by MISTRA on 19/11/2014.
 */
public class ListFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String LISTE_ELEVE = "LISTE_ELEVE";
    private Button addEleve;
    private ListView lv;
    private ArrayList<EleveBean> eleveList;
    private EleveAdapter eleveAdapter;
    private View rootView;
    private ListListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list,container,false);
        lv = (ListView) rootView.findViewById(R.id.fr_listViewEleve);

        // On ajoute le listener du bouton d'ajout d'élève
        addEleve = (Button) rootView.findViewById(R.id.fr_AjoutEleveBouton);
        addEleve.setOnClickListener(this);

        // On ajoute l'activité actuelle comme listener de la liste
        lv.setOnItemClickListener(this);

        // On charge la liste des élèves
        if(eleveList == null) {
            eleveList = new ArrayList<EleveBean>();
            eleveList = defaultEleveList();
        }

        eleveAdapter = new EleveAdapter(getActivity(),eleveList);
        lv.setAdapter(eleveAdapter);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // On sauvegarde dans le Bundle l'arrayList d'élèves parcelée
        outState.putParcelableArrayList(LISTE_ELEVE, eleveList);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null){
            // On passe par une variable intermediare, sinon le cast ne passe pas
            ArrayList<EleveBean> temp;
            // On récupère la liste d'élève à partir du bundle et on la déparcele.
            temp = savedInstanceState.getParcelableArrayList(LISTE_ELEVE);
            // On vide la liste existante et on la remplit avec la liste restauré si elle est non vide
            eleveList.clear();
            if(temp != null){
                eleveList.addAll(temp);
            }
        }
    }

    private ArrayList<EleveBean> defaultEleveList() {
        ArrayList<EleveBean> lv = new ArrayList<EleveBean>();
        lv.add(new EleveBean("SANTUS","Brice","HOMME"));
        lv.add(new EleveBean("PREIN","Francois","HOMME"));
        lv.add(new EleveBean("TILENDIER","Pierre","HOMME"));
        lv.add(new EleveBean("ALONSO","Sofia","FEMME"));
        lv.add(new EleveBean("STUART","Alice","FEMME"));

        return lv;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fr_AjoutEleveBouton){
            TextView tv_nom = (TextView)rootView.findViewById(R.id.fr_nom_eleve_form);
            TextView tv_prenom = (TextView)rootView.findViewById(R.id.fr_prenom_eleve_form);
            RadioGroup rg_sexe = (RadioGroup)rootView.findViewById(R.id.fr_sexe_eleve_form);
            String radiovalue=  ((RadioButton)rootView.findViewById(rg_sexe.getCheckedRadioButtonId())).getText().toString();

            String nom = tv_nom.getText().toString();
            String prenom = tv_prenom.getText().toString();
            String sexe= "";

            if(radiovalue.equals("H")){
                sexe = "HOMME";
            }else{
                sexe = "FEMME";
            }

            // On ajoute l'élève à la liste de donnée
            this.eleveList.add(new EleveBean(nom,prenom,sexe));

            // On notify la vue
            this.eleveAdapter.notifyDataSetChanged();

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
        if(this.listener != null){
            this.clicEleveList(position);
        }
    }

    /**
     * Méthode d'ajout d'un listener pour les clic sur les élève
     * @param listener
     */
    public void setListListener(ListListener listener) {
        this.listener = listener;
    }
    private void clicEleveList(int position){
        // On transmet le clic à l'Activité
        this.listener.clicEleve(this.eleveList.get(position));
    }
}
