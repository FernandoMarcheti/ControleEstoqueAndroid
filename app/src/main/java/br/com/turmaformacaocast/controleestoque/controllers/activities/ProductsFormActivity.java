package br.com.turmaformacaocast.controleestoque.controllers.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.turmaformacaocast.controleestoque.R;
import br.com.turmaformacaocast.controleestoque.model.entities.Product;
import br.com.turmaformacaocast.controleestoque.model.services.ProductBusinessService;
import br.com.turmaformacaocast.controleestoque.util.FormHelper;

public class ProductsFormActivity extends AppCompatActivity {

    public static final String PARAM_PRODUCT = "PARAM_PRODUCT";
    private EditText editTextName;
    private EditText editTextQuantity;
    private EditText editTextMinQuantity;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private Button buttonSave;
    private Button buttonAddImage;
    private Product product;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        initProduct();
        bindFields();
        bindButtonSave();
    }

    private void initProduct() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.product = (Product) extras.getParcelable(PARAM_PRODUCT);
        }
        this.product = this.product == null ? new Product() : this.product;
    }

    @Override
    protected void onResume() {
        bindFields();
        super.onResume();
    }

    private void bindButtonSave() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requiredMessage = ProductsFormActivity.this.getString(R.string.msg_required);
                if (validarCamposObrigatorios(requiredMessage)) {
                    bindProduct();
                    new SetProducts().execute();
                    Toast.makeText(ProductsFormActivity.this, R.string.msg_save_success, Toast.LENGTH_LONG).show();
                    ProductsFormActivity.this.finish();
                }
            }
        });
    }

    private boolean validarCamposObrigatorios(String requiredMessage) {
        return (!FormHelper.checkRequireFields(requiredMessage, editTextName) &&
                !FormHelper.checkRequireFields(requiredMessage, editTextDescription) &&
                !FormHelper.checkRequireFields(requiredMessage, editTextQuantity) &&
                !FormHelper.checkRequireFields(requiredMessage, editTextMinQuantity) &&
                !FormHelper.checkRequireFields(requiredMessage, editTextPrice));
    }

    private void bindFields() {
        bindEditTextName();
        bindEditTextDescription();
        bindEditTextQuantity();
        bindEditTextMinQuantity();
        bindEditTextPrice();
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(product.getNome() == null ? "" : product.getNome());
    }

    private void bindEditTextDescription() {
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextDescription.setText(product.getDescricao() == null ? "" : product.getDescricao());
    }

    private void bindEditTextQuantity() {
        editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);
        editTextQuantity.setText(product.getQuantidade() == null ? "" : Integer.toString(product.getQuantidade()));
    }

    private void bindEditTextMinQuantity() {
        editTextMinQuantity = (EditText) findViewById(R.id.editTextMinQuantity);
        editTextMinQuantity.setText(product.getQuantidadeMinEstoque() == null ? "" : Integer.toString(product.getQuantidadeMinEstoque()));
    }

    private void bindEditTextPrice() {
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextPrice.setText(product.getValorUnitario() == null ? "" : Double.toString(product.getValorUnitario()));
    }

    private void bindProduct() {
        product.setNome(editTextName.getText().toString());
        product.setDescricao(editTextDescription.getText().toString());
        product.setQuantidade(Integer.valueOf(editTextQuantity.getText().toString()));
        product.setQuantidadeMinEstoque(Integer.valueOf(editTextMinQuantity.getText().toString()));
        product.setValorUnitario(Double.valueOf(editTextPrice.getText().toString()));
    }

    private class SetProducts extends AsyncTask<Void, Void, Void>{

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ProductsFormActivity.this);
            progressDialog.setMessage(getString(R.string.Loading));
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ProductBusinessService.save(product);
            return null;
        }

        @Override
        protected void onPostExecute(Void product) {
            progressDialog.dismiss();
        }
    }
}
