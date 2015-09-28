package br.com.turmaformacaocast.controleestoque.controllers.Synchronized;


import android.os.AsyncTask;

import java.util.List;

import br.com.turmaformacaocast.controleestoque.model.entities.Product;
import br.com.turmaformacaocast.controleestoque.model.services.ProductBusinessService;

public class FindProductsTask extends AsyncTask<Void, Void, List<Product>> {

    @Override
    protected List<Product> doInBackground(Void... voids) {
        return ProductBusinessService.getAll();
    }
}
