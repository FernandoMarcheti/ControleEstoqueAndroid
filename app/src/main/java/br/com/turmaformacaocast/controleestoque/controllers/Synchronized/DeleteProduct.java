package br.com.turmaformacaocast.controleestoque.controllers.Synchronized;


import android.os.AsyncTask;

import br.com.turmaformacaocast.controleestoque.model.entities.Product;
import br.com.turmaformacaocast.controleestoque.model.services.ProductBusinessService;

public class DeleteProduct extends AsyncTask<Product, Void, Void>{
    @Override
    protected Void doInBackground(Product... params) {
        ProductBusinessService.delete(params[0]);
        return null;
    }
}
