package br.com.turmaformacaocast.controleestoque.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.turmaformacaocast.controleestoque.model.entities.Product;

public final class ProductRepository {

    public ProductRepository() {
        super();
    }

    public static List<Product> getAll() {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ProductContract.TABLE, ProductContract.COLUMNS, null, null, null, null, null);
        List<Product> values = ProductContract.getProducts(cursor);

        db.close();
        databaseHelper.close();
        return values;
    }

    public static void save(Product product) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = ProductContract.getContentValues(product);
        if (product.getId() == null) {
            db.insert(ProductContract.TABLE, null, values);
        } else {
            String where = ProductContract.ID + " = ? ";
            String[] params = {product.getId().toString()};
            db.update(ProductContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static void delete(int id) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = ProductContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(ProductContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static Product getProductByWebId(Long webId) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = ProductContract.ID + " = ? ";
        String[] params = {String.valueOf(webId)};

        Cursor cursor = db.query(ProductContract.TABLE, ProductContract.COLUMNS, where, params, null, null, null);
        Product product = ProductContract.getProduct(cursor);

        db.close();
        databaseHelper.close();
        return product;
    }
}
