package com.example.mistra.application;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mistra.activity.R;
import com.example.mistra.manager.PopupsManager;

/**
 * Created by MISTRA on 20/11/2014.
 */
public abstract class CommonActivity extends Activity{
    TextView TitrePage;
    private Handler handler;
    private int progressCount = 0;
    private Dialog progressDialog;

    private static final int MSG_START_PROGRESS = 0;
    private static final int MSG_STOP_PROGRESS = 1;

    @Override
     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.common_layout);
        this.handler = initHandler();
        progressDialog = PopupsManager.createProgressPopup(this, getString(R.string.loading));
    }

    protected Handler initHandler(){
        return new Handler() {
            @Override
            public void handleMessage(final Message msg) {
                switch (msg.what) {
                    case MSG_START_PROGRESS:
                        if (!progressDialog.isShowing()) {
                            progressDialog.show();
                        } break;
                    case MSG_STOP_PROGRESS:
                        if (progressDialog.isShowing()) {
                            progressDialog.cancel();
                        } break;
                }
            }
        };
    }

    /**
     * Afficher l'icone de progression. A utiliser typiquement lorsque des appels réseau sont en cours. Le nombre d'appel est comptabilisé et il faudra un même
     * nombre d'appel à stopProgress pour retirer l'icone
     */
    public void startProgress() {
        progressCount++;
        handler.sendEmptyMessage(MSG_START_PROGRESS);
    }

    /**
     * Enlever l'icone de progression. cf startProgress : l'icone ne sera retiré que s'il y a un même nombre d'appel qu'à startProgress
     */
    public void stopProgress() {
        if (progressCount == 0) {
            Log.e("Debug", "nombre d'appels de stop/start progress incorrect ...");
            return;
        }
        progressCount--;
        if (progressCount == 0) {
            handler.sendEmptyMessage(MSG_STOP_PROGRESS);
        }
    }

    @Override
    public void setContentView(final int layoutResID) {
        final View v = getLayoutInflater().inflate(layoutResID, null);
        final FrameLayout container = (FrameLayout) findViewById(R.id.common_frame_layout);
        container.removeAllViews();
        container.addView(v);
    }
    public void updateTitle(String new_title){
        TitrePage = (TextView)findViewById(R.id.commonLayoutTitle);
        TitrePage.setText(new_title);
    }

}
