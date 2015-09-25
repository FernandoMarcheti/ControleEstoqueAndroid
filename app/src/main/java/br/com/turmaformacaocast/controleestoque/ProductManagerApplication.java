package br.com.turmaformacaocast.controleestoque;


import android.app.Application;

import br.com.turmaformacaocast.controleestoque.util.ApplicationUtil;

public class ProductManagerApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}
