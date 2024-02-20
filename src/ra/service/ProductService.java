package ra.service;

import ra.model.Catalog;
import ra.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductService implements IGenericService<Product, String> {
    private static List<Product> products = new ArrayList<>();

    public ProductService() {

    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        for (int i = 0; i < products.size(); i++) {
            Product product1 = products.get(i);
            if (Objects.equals(product.getProductId(), product1.getProductId())) {
                products.set(i, product);
                return; // Khi đã tìm thấy và thay thế catalog, ta có thể thoát vòng lặp
            }
        }
        products.add(product);
    }

    @Override
    public Product findById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String productId) {
        Product productToRemove = null;
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            products.remove(productToRemove);
        }
    }
}

