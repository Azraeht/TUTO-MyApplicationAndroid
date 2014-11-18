package com.example.mistra.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mistra.beans.EleveBean;
import com.example.mistra.holders.ViewHolder;

import java.util.List;

/**
 * Created by MISTRA on 18/11/2014.
 */
public class EleveAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<EleveBean> elevelist;

    public EleveAdapter(Context context, List<EleveBean> elevelist){
        this.elevelist = elevelist;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return elevelist.size();
    }

    @Override
    public EleveBean getItem(int position) {
        return elevelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowview = convertView;
        ViewHolder viewholder;

        if(rowview == null){
            // Création de la cellule Eleve
            rowview = this.inflater.inflate(R.layout.eleve_listview,null);

            viewholder = new ViewHolder();
            viewholder.ec_tv_nom = (TextView) rowview.findViewById(R.id.NomEleve);
            viewholder.ec_tv_prenom = (TextView) rowview.findViewById(R.id.PrenomEleve);
            viewholder.ec_iv = (ImageView) rowview.findViewById(R.id.EleveImage);

            // On stocke le viewHolder dans le rowview
            rowview.setTag(viewholder);
        }else{
            // Recyclage de la cellule
            viewholder = (ViewHolder) rowview.getTag();
        }

        // On récupère l'élève à la position donnée en paramètre
        // On affecte
        EleveBean eleve = this.getItem(position);
        viewholder.ec_tv_nom.setText(eleve.getNom());
        viewholder.ec_tv_prenom.setText(eleve.getPrenom());
        if (eleve.getSexe().equals("HOMME")){
            viewholder.ec_tv_nom.setTextColor(Color.BLACK);
        }else{
            viewholder.ec_tv_nom.setTextColor(Color.CYAN);
        }
        viewholder.setEleve(eleve);

        return rowview;
    }
}
