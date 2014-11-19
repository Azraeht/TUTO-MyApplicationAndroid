package com.example.mistra.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mistra.activity.R;
import com.example.mistra.adapter.EleveAdapter;
import com.example.mistra.beans.EleveBean;

import java.util.ArrayList;

/**
 * Created by MISTRA on 19/11/2014.
 */
public class DetailFragment extends Fragment{
    private TextView tv_nom;
    private TextView tv_prenom;
    private EleveBean eleve;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail,container,false);
        return rootView;
    }

    public void setEleve(EleveBean eleveBean) {
        this.eleve = eleveBean;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Si l'élève existe, on rafraichi le texte, sinon on laisse les valeurs par défaut
        if(eleve != null){
            refreshText();
        }
    }

    /**
     * Méthode de mise à jour du texte de la vue
     */
    public void refreshText(){
        tv_nom = (TextView) getView().findViewById(R.id.fr_det_nom);
        tv_nom.setText(eleve.getNom());

        tv_prenom = (TextView) getView().findViewById(R.id.fr_det_prenom);
        tv_prenom.setText(eleve.getPrenom());
    }
}
