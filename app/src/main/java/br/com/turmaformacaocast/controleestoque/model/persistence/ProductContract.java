package br.com.turmaformacaocast.controleestoque.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.turmaformacaocast.controleestoque.model.entities.Image;
import br.com.turmaformacaocast.controleestoque.model.entities.Product;

public final class ProductContract {

    public static final String TABLE = "product";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String QUANTITY = "quantity";
    public static final String MIN_QUANTITY = "min_quantity";
    public static final String PRICE = "price";
    public static final String IMAGE_ID = "image_id";

    public static final String[] COLUMNS = {ID, NAME, DESCRIPTION, QUANTITY, MIN_QUANTITY, PRICE, IMAGE_ID};

    public ProductContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, " );
        create.append(NAME + " TEXT NOT NULL, " );
        create.append(DESCRIPTION + " TEXT NOT NULL, " );
        create.append(QUANTITY + " INTEGER, ");
        create.append(MIN_QUANTITY + " INTEGER, ");
        create.append(PRICE + " REAL, ");
        create.append(IMAGE_ID + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Product product) {
        ContentValues values = new ContentValues();
        values.put(ProductContract.ID, product.getId());
        values.put(ProductContract.NAME, product.getNome());
        values.put(ProductContract.DESCRIPTION, product.getDescricao());
        values.put(ProductContract.QUANTITY, product.getQuantidade());
        values.put(ProductContract.MIN_QUANTITY, product.getQuantidadeMinEstoque());
        values.put(ProductContract.PRICE, product.getValorUnitario());
        if(product.getImagem() != null)
            values.put(ProductContract.IMAGE_ID, product.getImagem().getId());

        return values;
    }

    public static Product getProduct(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Product product = new Product();
            product.setId(cursor.getInt(cursor.getColumnIndex(ProductContract.ID)));
            product.setNome(cursor.getString(cursor.getColumnIndex(ProductContract.NAME)));
            product.setDescricao(cursor.getString(cursor.getColumnIndex(ProductContract.DESCRIPTION)));

            product.setQuantidade(cursor.getInt(cursor.getColumnIndex(ProductContract.QUANTITY)));
            product.setQuantidadeMinEstoque(cursor.getInt(cursor.getColumnIndex(ProductContract.MIN_QUANTITY)));
            product.setValorUnitario(cursor.getDouble(cursor.getColumnIndex(ProductContract.PRICE)));

            if(product.getImagem() != null) {
                Image image = new Image();
                product.setImagem(image);
            }

            return product;
        }
        return null;
    }

    public static List<Product> getProducts(Cursor cursor) {
        ArrayList<Product> products = new ArrayList<>();
        while (cursor.moveToNext()) {
            products.add(getProduct(cursor));
        }
        return products;
    }
}
