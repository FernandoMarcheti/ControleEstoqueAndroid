package br.com.turmaformacaocast.controleestoque.controllers.adapters;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import android.text.format.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.turmaformacaocast.controleestoque.R;
import br.com.turmaformacaocast.controleestoque.model.entities.Product;

public class ProductsListAdapter extends BaseAdapter{

    private Activity context;
    private List<Product> products;

    public ProductsListAdapter(Activity context) {
        this.context = context;
        this.products = new ArrayList<>();
    }

    public void setValues(List<Product> values){
        products.clear();
        products.addAll(values);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);
        View productsItemView = context.getLayoutInflater().inflate(R.layout.list_item_products, parent, false);

        TextView textViewName = (TextView) productsItemView.findViewById(R.id.textViewName);
        textViewName.setText(product.getNome());

        TextView textViewDescription = (TextView) productsItemView.findViewById(R.id.textViewDescription);
        textViewDescription.setText(product.getDescricao());

        TextView textViewQuantity = (TextView) productsItemView.findViewById(R.id.textViewQuantity);
        textViewQuantity.setText(product.getQuantidade().toString());

        TextView textViewMinQuantity = (TextView) productsItemView.findViewById(R.id.textViewMinQuantity);
        textViewMinQuantity.setText(product.getQuantidadeMinEstoque().toString());

        TextView textViewPrice = (TextView) productsItemView.findViewById(R.id.textViewPrice);
        textViewPrice.setText(product.getValorUnitario().toString());

        TextView textViewDate = (TextView) productsItemView.findViewById(R.id.textViewDate);
        String date = product.getDate().toString();
        Long dateFormat = Long.parseLong(date);
        textViewDate.setText(DateFormat.format("dd/MM/yyyy", new Date(dateFormat)));

        return productsItemView;
    }
}
