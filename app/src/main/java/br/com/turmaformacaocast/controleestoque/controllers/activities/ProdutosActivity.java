package br.com.turmaformacaocast.controleestoque.controllers.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.turmaformacaocast.controleestoque.R;
import br.com.turmaformacaocast.controleestoque.controllers.adapters.ProductsListAdapter;
import br.com.turmaformacaocast.controleestoque.model.entities.Product;
import br.com.turmaformacaocast.controleestoque.model.services.ProductBusinessService;


public class ProdutosActivity extends AppCompatActivity {

    private ListView listViewProducts;
    private Product selectedProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        bindProductList();
    }

    private void bindProductList() {
        listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        registerForContextMenu(listViewProducts);
        listViewProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ProductsListAdapter adapter = (ProductsListAdapter) listViewProducts.getAdapter();
                selectedProduct = adapter.getItem(position);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        updateProducts();
        super.onResume();
    }

    private void updateProducts() {
        ProductsListAdapter adapter = (ProductsListAdapter) listViewProducts.getAdapter();
        if(adapter == null){
            adapter = new ProductsListAdapter(this);
            listViewProducts.setAdapter(adapter);
        }
        adapter.setValues(ProductBusinessService.getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_produtos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_add:
                onMenuAddClick();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClick() {
        Intent goToTaskFormActivity = new Intent(ProdutosActivity.this, ProductsFormActivity.class);
        startActivity(goToTaskFormActivity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_products_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                onMenuDeleteClick();
                break;
            case R.id.menu_edit:
                onMenuEditClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuEditClick() {
        Intent goToTaskForm = new Intent(ProdutosActivity.this, ProductsFormActivity.class);
        goToTaskForm.putExtra(ProductsFormActivity.PARAM_PRODUCT, selectedProduct);
        startActivity(goToTaskForm);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(ProdutosActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_confirm_delete)
                .setPositiveButton(R.string.lbl_yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ProductBusinessService.delete(selectedProduct);
                                selectedProduct = null;
                                String message = getString(R.string.msg_delete_success);
                                updateProducts();
                                Toast.makeText(ProdutosActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }
}
