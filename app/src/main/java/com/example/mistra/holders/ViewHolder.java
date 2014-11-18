package com.example.mistra.holders;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.mistra.beans.EleveBean;

/**
 * Created by MISTRA on 18/11/2014.
 */
public class ViewHolder {
    public TextView ec_tv_nom, ec_tv_prenom;
    public ImageView ec_iv;
    public EleveBean eleve;


    public void setEleve(EleveBean eleve) {
        this.eleve = eleve;
    }
}