package br.com.turmaformacaocast.controleestoque.controllers.http;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.turmaformacaocast.controleestoque.model.entities.Product;

public final class ProductService {

    private static final String URL = "http://10.11.21.193:4000/api/v1/products";

    public ProductService() {
        super();
    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList();
        try {
            java.net.URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.i("getProducts", "Código de retorno da requisição de produtos " + responseCode);

            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class);
                products = objectMapper.readValue(inputStream, collectionType);

                conn.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public static void save(Product product) {
        try {
            java.net.URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = conn.getOutputStream();
            new ObjectMapper().writeValue(outputStream, product);

            outputStream.flush();
            outputStream.close();
            conn.disconnect();

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Erro code " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
