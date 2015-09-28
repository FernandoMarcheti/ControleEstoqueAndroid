package br.com.turmaformacaocast.controleestoque.controllers.Synchronized;

import android.os.AsyncTask;

import br.com.turmaformacaocast.controleestoque.controllers.http.ProductService;
import br.com.turmaformacaocast.controleestoque.model.entities.Product;
import br.com.turmaformacaocast.controleestoque.model.services.ProductBusinessService;

public class SaveProducts extends AsyncTask<Product, Void, Void> {

    @Override
    protected Void doInBackground(Product... params) {
        ProductService.save(params[0]);
        return null;
    }
}
