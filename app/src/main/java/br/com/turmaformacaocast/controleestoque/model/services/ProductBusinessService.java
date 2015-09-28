package br.com.turmaformacaocast.controleestoque.model.services;


import java.util.List;

import br.com.turmaformacaocast.controleestoque.controllers.http.ProductService;
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

    public static void saveWebProducts(List<Product> products) {
        for (Product product : products) {
            Product idProduct = ProductBusinessService.getTaskByWebId(product.getWebId());
            if (idProduct != null) {
                if(idProduct.getDate() < product.getDate()) {
                    product.setId(idProduct.getId());
                    ProductBusinessService.save(product);
                }
            } else {
                ProductBusinessService.save(product);
            }

        }
    }

    private static Product getTaskByWebId(Long webId) {
        return ProductRepository.getProductByWebId(webId);
    }

    public static void synchronizedTasks() {
        saveWebProducts(ProductService.getProducts());
    }
}
