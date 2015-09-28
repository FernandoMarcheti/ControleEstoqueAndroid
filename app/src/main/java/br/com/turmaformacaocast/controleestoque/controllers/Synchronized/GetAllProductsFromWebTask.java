package br.com.turmaformacaocast.controleestoque.controllers.Synchronized;

import android.os.AsyncTask;

import br.com.turmaformacaocast.controleestoque.model.services.ProductBusinessService;

public class GetAllProductsFromWebTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        ProductBusinessService.synchronizedTasks();
        return null;
    }
}
