package br.com.turmaformacaocast.controleestoque.model.persistence;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.turmaformacaocast.controleestoque.util.ApplicationUtil;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "productmanagerdb";
    private static final int DATABASE_VERSION = 1;

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getInstance() {
        return new DataBaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL((ProductContract.getCreateTableScript()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
