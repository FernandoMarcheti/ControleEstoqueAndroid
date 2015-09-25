package br.com.turmaformacaocast.controleestoque.model.services;


import java.util.List;

import br.com.turmaformacaocast.controleestoque.model.entities.Product;
import br.com.turmaformacaocast.controleestoque.model.persistence.ProductRepository;

public final class ProductBusinessService {

    public ProductBusinessService() {
        super();
    }

    public static List<Product> getAll() {
        return ProductRepository.getAll();
    }

    public static void save(Product product) {
        ProductRepository.save(product);
    }

    public static void delete(Product selectedProduct) {
        ProductRepository.delete(selectedProduct.getId());
    }

}
